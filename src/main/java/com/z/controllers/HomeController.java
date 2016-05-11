/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.controllers;

import com.z.criteria.Criteria;
import com.z.criteria.CriteriaDistance;
import com.z.models.Curso;
import com.z.models.Establecimientos;
import com.z.services.dao.EstablecimientosDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mariano
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    private EstablecimientosDAO establecimientosDAO;
    
    @RequestMapping(method = RequestMethod.GET,params={"distancia"})
    public ModelAndView main(@RequestParam double distancia){
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("distanciaKM",distancia);
        return mav;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView("main");
        return mav;
    }
    @RequestMapping(value = "establecimientos/getAll",method = RequestMethod.GET)
    public @ResponseBody List<Establecimientos> getEst(){
        return establecimientosDAO.list();
    }
    @RequestMapping(value = "establecimientos/getByLocDep/{localidad}/{departamento}",method = RequestMethod.GET)
    public @ResponseBody List<Establecimientos> getEstbylocdep(
            @PathVariable("localidad") String localidad,
            @PathVariable("departamento") String departamento){
        return establecimientosDAO.listByLocalidadAndDepartamento(localidad, departamento);
    }
    @RequestMapping(value = "establecimientos/getByLocDep/{localidad}/{departamento}/{lat}/{lng}/{distanciaKM}/{regimen}",method = RequestMethod.GET)
    public @ResponseBody List<Establecimientos> getEstbylocdep(
            @PathVariable("localidad") String localidad,
            @PathVariable("departamento") String departamento,
            @PathVariable("lat") String lat,
            @PathVariable("lng") String lng,
            @PathVariable("distanciaKM") double distanciaKM,
            @PathVariable("regimen") String regimen){
        Criteria criteria = new CriteriaDistance(Float.parseFloat(lat), Float.parseFloat(lng), distanciaKM);
        if(regimen.equals("Todos")){
            return criteria.meetCriteria(establecimientosDAO.listByLocalidadAndDepartamento(localidad, departamento));
        }else{
            return criteria.meetCriteria(establecimientosDAO.listByLocalidadAndDepartamento(localidad, departamento,regimen));
        }
        
    }
    @RequestMapping(value = "establecimientos/getByCueAnexo/{cueanexo}",method = RequestMethod.GET)
    public @ResponseBody Establecimientos getEstablecimiento(@PathVariable("cueanexo") int cueanexo){
        return establecimientosDAO.get(cueanexo);
    }
    @RequestMapping(value = "establecimientos/getCursosByCueAnexo/{cueanexo}",method = RequestMethod.GET)
    public @ResponseBody List<Curso> getEstablecimientoCursos(@PathVariable("cueanexo") int cueanexo){
        return establecimientosDAO.getCursosByCueAnexo(cueanexo);
    }
     @RequestMapping(value = "establecimientos/comentarios/{cueanexo}",method = RequestMethod.GET)
    public  ModelAndView comentarios(@PathVariable("cueanexo") int cueanexo){
        ModelAndView mav = new ModelAndView("comentarios");
        mav.addObject("cuanexo", cueanexo);
        return mav;
    }
    @RequestMapping(value = "establecimientos/Count/{localidad}/{departamento}/{lat}/{lng}/{distanciaKM}/{regimen}",method = RequestMethod.GET)
    public @ResponseBody HashMap<String,Integer> getEstbylocdepCount(
            @PathVariable("localidad") String localidad,
            @PathVariable("departamento") String departamento,
            @PathVariable("lat") String lat,
            @PathVariable("lng") String lng,
            @PathVariable("distanciaKM") double distanciaKM,
            @PathVariable("regimen") String regimen){
        Criteria criteria = new CriteriaDistance(Float.parseFloat(lat), Float.parseFloat(lng), distanciaKM);
        List<Establecimientos> establecimientos;
        if(regimen.equals("Todos")){
            establecimientos= criteria.meetCriteria(establecimientosDAO.listByLocalidadAndDepartamento(localidad, departamento));
        }else{
            establecimientos = criteria.meetCriteria(establecimientosDAO.listByLocalidadAndDepartamento(localidad, departamento,regimen));
        }
        HashMap<String,Integer> regimens = new HashMap();
        regimens.put("Publico", 0);
        regimens.put("Privado Subvencionado", 0);
        regimens.put("Privado No Subvencionado", 0);
        for (Establecimientos establecimiento : establecimientos) {
            if(regimens.containsKey(establecimiento.getRegimen())){
                regimens.put(establecimiento.getRegimen(), regimens.get(establecimiento.getRegimen())+1);
            }
        }
        regimens.put("Total", establecimientos.size());
        return regimens;
    }
    /*@RequestMapping(value = "establecimientos/getby",method = RequestMethod.GET)
    public @ResponseBody List<Establecimientos> getEstablecimientos(
            @RequestParam (value="lat",required = true) final String lat,
            @RequestParam (value="lng",required = true) final String lng,
            @RequestParam (value="distanciaKM",required = true) final double distanciaKM,
            @RequestParam (value="regimen",required = true) final String regimen){
        System.out.println("lat: "+lat+", lng: "+lng);
        Criteria criteria = new CriteriaDistance(Float.parseFloat(lat), Float.parseFloat(lng), distanciaKM);
        if(regimen.equals("Todos")){
            return criteria.meetCriteria(establecimientosDAO.list());
        }else{
            return criteria.meetCriteria(establecimientosDAO.listByRegimen(regimen));
        }
    }
    @RequestMapping(value="getCursoByCueAnexo/{cueanexo}",method = RequestMethod.GET)
    public @ResponseBody List<Curso> getCursosByCueAnexo(
            @PathVariable("cueanexo") int cueanexo){
        System.out.println("Cue-Anexo: "+cueanexo);
        return getOrientaciones(cueanexo);
    }
    
    
    public List<Curso> getOrientaciones(int cueanexo){
        List<Curso> cur = new ArrayList();
        for (Curso curso : establecimientosDAO.getCursosByCueAnexo(cueanexo)) {
            boolean flag = false;
            for (Curso cur1 : cur) {
                if(cur1.getOrientacion().equals(curso.getOrientacion())){flag=true;}
            }
            if(!flag){cur.add(curso);}
        }
        return cur;
    }*/
}

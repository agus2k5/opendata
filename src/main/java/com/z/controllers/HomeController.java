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
    @RequestMapping(value = "establecimientos/get/{cueanexo}",method = RequestMethod.GET)
    public @ResponseBody Establecimientos getEstablecimiento(@PathVariable("cueanexo") int cueanexo){
        return establecimientosDAO.get(cueanexo);
    }
    @RequestMapping(value = "establecimientos/getCursos/{cueanexo}",method = RequestMethod.GET)
    public @ResponseBody List<Curso> getEstablecimientoCursos(@PathVariable("cueanexo") int cueanexo){
        return establecimientosDAO.getCursosByCueAnexo(cueanexo);
    }
    @RequestMapping(value = "establecimientos/getby",method = RequestMethod.GET)
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
    }
}

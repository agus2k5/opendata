/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.controllers;

import com.z.criteria.Criteria;
import com.z.criteria.CriteriaDistance;
import com.z.models.Establecimientos;
import com.z.services.dao.EstablecimientosDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping(value = "establecimientos/getby",method = RequestMethod.GET)
    public @ResponseBody List<Establecimientos> getEstablecimientos(
            @RequestParam (value="lat",required = true) final String lat,
            @RequestParam (value="lng",required = true) final String lng,
            @RequestParam (value="distanciaKM",required = true) final double distanciaKM){
        System.out.println("lat: "+lat+", lng: "+lng);
        Criteria criteria = new CriteriaDistance(Float.parseFloat(lat), Float.parseFloat(lng), distanciaKM);
        return criteria.meetCriteria(establecimientosDAO.list());
    }
}

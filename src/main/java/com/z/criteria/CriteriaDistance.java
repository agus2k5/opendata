/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.criteria;

import com.z.models.Establecimientos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mariano
 */
public class CriteriaDistance implements Criteria {

    private double distanceKM;
    private float my_lat;
    private float my_lon;

    public CriteriaDistance(float my_lat, float my_lon, double distanceKM) {
        this.distanceKM = distanceKM;
        this.my_lat = my_lat;
        this.my_lon = my_lon;
    }

    private double getDistance2(Establecimientos establecimiento) {
        double latitud_radianes = Math.toRadians(my_lat);
        double to_latitud_radianes = Math.toRadians(Double.parseDouble(establecimiento.getLatitud()));
        double delta_longitud = Math.toRadians(my_lon - Double.parseDouble(establecimiento.getLongitud()));
        return (Math.acos(
                Math.sin(latitud_radianes)
                * Math.sin(to_latitud_radianes)
                + Math.cos(latitud_radianes)
                * Math.cos(to_latitud_radianes)
                * Math.cos(delta_longitud)
        ) * 180 / Math.PI) * 100;
    }

    @Override
    public List<Establecimientos> meetCriteria(List<Establecimientos> establecimientos) {
        List<Establecimientos> inRange = new ArrayList<Establecimientos>();
        for (Establecimientos establecimiento : establecimientos) {
            if (!establecimiento.getLatitud().equals("NULL") || !establecimiento.getLongitud().equals("NULL")) {
                double dist = getDistance2(establecimiento);
                if (dist <= this.distanceKM) {
                    System.out.println(establecimiento.getNombre() + "\tDist: " + dist);
                    inRange.add(establecimiento);
                }
            }
        }
        return inRange;
    }

}

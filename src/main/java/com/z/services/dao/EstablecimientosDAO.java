/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.services.dao;

import com.z.models.Establecimientos;
import java.util.List;

/**
 *
 * @author Mariano
 */
public interface EstablecimientosDAO {
    public void add(Establecimientos establecimiento);
    public List<Establecimientos> list();
    public Establecimientos get(String id);
    public List<Establecimientos> listByRegimen(String regimen);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.services.dao;

import com.z.models.Curso;
import com.z.models.Establecimientos;
import java.util.List;

/**
 *
 * @author Mariano
 */
public interface EstablecimientosDAO {
    public void add(Establecimientos establecimiento);
    public List<Establecimientos> list();
    public List<Establecimientos> listByLocalidadAndDepartamento(String localidad,String departamento);
    public List<Establecimientos> listByLocalidadAndDepartamento(String localidad,String departamento,String regimen);
    public Establecimientos get(int cueanexo);
    public List<Establecimientos> listByRegimen(String regimen);
    public List<Curso> getCursosByCueAnexo(int cueanexo);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.services.dao.impl;

import com.z.models.Curso;
import com.z.models.Establecimientos;
import com.z.services.dao.EstablecimientosDAO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mariano
 */
@Service
public class EstablecimientosDAOService implements EstablecimientosDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = false)
    public void add(Establecimientos establecimiento) {
        sessionFactory.getCurrentSession().save(establecimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Establecimientos> list() {
        return sessionFactory.getCurrentSession().createQuery("from Establecimientos").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Establecimientos get(int cueAnexo) {
        return (Establecimientos)sessionFactory.getCurrentSession().get(Establecimientos.class, cueAnexo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Establecimientos> listByRegimen(String regimen) {
        String hqlQuery = "FROM Establecimientos WHERE regimen=:regimen";
        Query query = sessionFactory.getCurrentSession().createQuery(hqlQuery);
        query.setParameter("regimen", regimen);
        return query.list();
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Curso> getCursosByCueAnexo(int cueanexo) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                "select ec.* from establecimiento_cursos ec where CUEANEXO=:cueanexo")
                .addEntity(Curso.class)
                .setParameter("cueanexo", cueanexo);
        return query.list();
    }
    
}

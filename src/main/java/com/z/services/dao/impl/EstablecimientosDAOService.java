/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.services.dao.impl;

import com.z.models.Establecimientos;
import com.z.services.dao.EstablecimientosDAO;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mariano
 */
@Service
public class EstablecimientosDAOService implements EstablecimientosDAO{
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
    public Establecimientos get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

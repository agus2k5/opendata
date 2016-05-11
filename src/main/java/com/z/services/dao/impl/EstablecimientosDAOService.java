/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.services.dao.impl;

import com.z.models.Curso;
import com.z.models.Establecimientos;
import com.z.services.dao.EstablecimientosDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
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
        return sessionFactory.getCurrentSession().createCriteria(Establecimientos.class).list();
        /*sessionFactory.getCurrentSession().cre
        Query q = sessionFactory.getCurrentSession().createQuery("from Establecimientos");
        //q.setMaxResults(1000);
        return q.list();*/
    }
    @Override
    @Transactional(readOnly = true)
    public List<Establecimientos> listByLocalidadAndDepartamento(String localidad, String departamento) {
        Criterion loc = Restrictions.eq("Localidad",localidad);
        Criterion dep = Restrictions.eq("Departamento",departamento);
        LogicalExpression andExp = Restrictions.and(loc, dep);
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Establecimientos.class);
        crit.add(andExp);
        return crit.list();
    }
    @Override
    @Transactional(readOnly = true)
    public List<Establecimientos> listByLocalidadAndDepartamento(String localidad, String departamento,String regimen) {
        Criterion loc = Restrictions.eq("Localidad",localidad);
        Criterion dep = Restrictions.eq("Departamento",departamento);
        Criterion reg = Restrictions.eq("Regimen",regimen);
        LogicalExpression andExp = Restrictions.and(loc, dep);
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Establecimientos.class);
        crit.add(andExp);
        crit.add(reg);
        return crit.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Establecimientos get(int cueAnexo) {
        return (Establecimientos) sessionFactory.getCurrentSession().get(Establecimientos.class, cueAnexo);
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
    public List<Curso> getCursosByCueAnexo(int cueanexo
    ) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                "select ec.* from establecimiento_cursos ec where CUEANEXO=:cueanexo")
                .addEntity(Curso.class)
                .setParameter("cueanexo", cueanexo);
        return query.list();
    }

}

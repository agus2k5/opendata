/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Mariano
 */
@Entity
@Table(name = "Establecimiento_cursos")
public class Curso implements Serializable {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "CUE")
    private int cue;
    @Column(name = "ANEXO")
    private int anexo;
    @Column(name = "CUEANEXO", nullable = false)
    private String cueAnexo;
    @Column(name = "CursoDivisionID")
    private int cursoDivisionID;
    @Column(name = "Curso")
    private String curso;
    @Column(name = "Division")
    private String division;
    @Column(name = "NivelEnseñanza")
    private String nivelEnseñanza;
    @Column(name = "Ciclo")
    private String ciclo;
    @Column(name = "Orientacion")
    private String orientacion;
    @Column(name = "Modalidad")
    private String modalidad;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CueAnexo")
    private Establecimientos establecimiento;

    public Curso() {
    }

    public Establecimientos getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimientos establecimiento) {
        this.establecimiento = establecimiento;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCue() {
        return cue;
    }

    public void setCue(int cue) {
        this.cue = cue;
    }

    public int getAnexo() {
        return anexo;
    }

    public void setAnexo(int anexo) {
        this.anexo = anexo;
    }

    public String getCueAnexo() {
        return cueAnexo;
    }

    public void setCueAnexo(String cueAnexo) {
        this.cueAnexo = cueAnexo;
    }

    public int getCursoDivisionID() {
        return cursoDivisionID;
    }

    public void setCursoDivisionID(int cursoDivisionID) {
        this.cursoDivisionID = cursoDivisionID;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getNivelEnseñanza() {
        return nivelEnseñanza;
    }

    public void setNivelEnseñanza(String nivelEnseñanza) {
        this.nivelEnseñanza = nivelEnseñanza;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

}

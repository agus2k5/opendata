package com.z.models;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="establecimientos")
public class Establecimientos implements Serializable {
	@Id
	@Column(name="CueAnexo",nullable = false)
	private int CueAnexo;

	@Column(name="Nombre",length =100,nullable = true)
	private String Nombre;

	@Column(name="Regimen",length =100,nullable = true)
	private String Regimen;

	@Column(name="Latitud",length =100,nullable = true)
	private String Latitud;

	@Column(name="Longitud",length =100,nullable = true)
	private String Longitud;

	@Column(name="Localidad",length =100,nullable = true)
	private String Localidad;

	@Column(name="Departamento",length =100,nullable = true)
	private String Departamento;

	@Column(name="Jurisdiccion",length =100,nullable = true)
	private String Jurisdiccion;

	public Establecimientos(){}
	
	/******accessors*******/
        
	public void setNombre(String Nombre){
		this.Nombre=Nombre;
	}
	public String getNombre(){
		return this.Nombre;
	}
	public void setRegimen(String Regimen){
		this.Regimen=Regimen;
	}
	public String getRegimen(){
		return this.Regimen;
	}
	public void setJurisdiccion(String Jurisdiccion){
		this.Jurisdiccion=Jurisdiccion;
	}
	public String getJurisdiccion(){
		return this.Jurisdiccion;
	}
	public void setLongitud(String Longitud){
		this.Longitud=Longitud;
	}
	public String getLongitud(){
		return this.Longitud;
	}
	public void setLatitud(String Latitud){
		this.Latitud=Latitud;
	}
	public String getLatitud(){
		return this.Latitud;
	}
	public void setCueAnexo(int CueAnexo){
		this.CueAnexo=CueAnexo;
	}
	public int getCueAnexo(){
		return this.CueAnexo;
	}
	public void setLocalidad(String Localidad){
		this.Localidad=Localidad;
	}
	public String getLocalidad(){
		return this.Localidad;
	}
	public void setDepartamento(String Departamento){
		this.Departamento=Departamento;
	}
	public String getDepartamento(){
		return this.Departamento;
	}
}
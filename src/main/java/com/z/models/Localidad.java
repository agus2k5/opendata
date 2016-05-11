package com.z.models;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
@Table(name="localidad")
public class Localidad implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",length =11,nullable = false)
	private Integer id;

	@Column(name="nombre",length =100,nullable = true)
	private String nombre;

	public Localidad(){}
	public Localidad(Integer id,String nombre){
		this.setId(id);
		this.setNombre(nombre);
	}
	@Override
	public String toString(){
		return "Id: "+this.getId()+", "+"Nombre: "+this.getNombre();
	}
	/******accessors*******/
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	public String getNombre(){
		return this.nombre;
	}
}
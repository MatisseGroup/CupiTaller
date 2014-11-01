/* ========================================================================
   * Copyright 2014 Matisse
   *
   * Licensed under the MIT, The MIT License (MIT)
   * Copyright (c) 2014 Matisse
  
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  
  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.
  
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
   * ========================================================================
  
  
  Source generated by CrudMaker version 1.0.0.201410261249*/

package co.edu.uniandes.csw.Matisse.sesion.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _SesionEntity {

	@Id
	@GeneratedValue(generator = "Sesion")
	private Long id;
	private String name;
	private String estado;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private Integer semanaAnual;
	private String dia;
	private Long estudianteId;
	private Long monitorId;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getEstado(){
		return estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
	public Date getFecha(){
		return fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
	public Integer getSemanaAnual(){
		return semanaAnual;
	}
	
	public void setSemanaAnual(Integer semanaAnual){
		this.semanaAnual = semanaAnual;
	}
	public String getDia(){
		return dia;
	}
	
	public void setDia(String dia){
		this.dia = dia;
	}
	public Long getEstudianteId(){
		return estudianteId;
	}
	
	public void setEstudianteId(Long estudianteId){
		this.estudianteId = estudianteId;
	}
	public Long getMonitorId(){
		return monitorId;
	}
	
	public void setMonitorId(Long monitorId){
		this.monitorId = monitorId;
	}
}
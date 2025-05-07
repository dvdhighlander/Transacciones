package com.usuarios.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClientDTO  {
	public ClientDTO(int idCliente, int idPersona, String nombre, String genero, int edad, long identificacion,
		String direccion, long telefono, String password, boolean estado) {
		this.idCliente=idCliente;
		this.idPersona=idPersona;
		this.nombre=nombre;
		this.genero=genero;
		this.edad=edad;
		this.identificacion=identificacion;
		this.direccion=direccion;
		this.telefono=telefono;
		this.password=password;
		this.estado=estado;
		
	}

	private int idCliente; 
	@JsonIgnore
	private int idPersona;
	
	private String password;
	private boolean estado;
	private String nombre;
	private String genero;
	private int edad;
	private long identificacion;
	private String direccion;
	private long telefono;
	
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public long getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(long identificacion) {
		this.identificacion = identificacion;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	
	
}

package com.banco.futuro.app.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class ClienteDto  extends EntidadBaseDto implements Serializable {

	private static final long serialVersionUID = 6747553019607390414L;

	protected String clave;

	private String estado;

	private PersonaDto persona;

	private List<CuentaDto> listaCuenta = new ArrayList<>();
	

	public ClienteDto() {

	}

	public ClienteDto(String clave, String estado, PersonaDto persona) {
		this.clave = clave;
		this.estado = estado;
		this.persona = persona;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public PersonaDto getPersona() {
		return persona;
	}

	public void setPersona(PersonaDto persona) {
		this.persona = persona;
	}

	public List<CuentaDto> getListaCuenta() {
		return listaCuenta;
	}

	public void setListaCuenta(List<CuentaDto> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}

}

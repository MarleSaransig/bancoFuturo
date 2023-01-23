package com.banco.futuro.app.enumeracion;

import java.util.ArrayList;
import java.util.List;

public enum EstadoEnum {
	
	ACTIVO("Activo"), INACTIVO("Inactivo");

	private String nombre;

	EstadoEnum(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static EstadoEnum obtenerEstado(String generoString) {

		if (generoString.equalsIgnoreCase(EstadoEnum.ACTIVO.getNombre())) {
			return EstadoEnum.ACTIVO;
		}
		if (generoString.equalsIgnoreCase(EstadoEnum.INACTIVO.getNombre())) {
			return EstadoEnum.INACTIVO;
		}
		return null;
	}

	public List<EstadoEnum> getListaEstado() {
		List<EstadoEnum> listaEstado = new ArrayList<>();
		for (EstadoEnum estado : EstadoEnum.values()) {
			listaEstado.add(estado);
		}
		return listaEstado;
	}

}

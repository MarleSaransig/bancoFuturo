package com.banco.futuro.app.enumeracion;

import java.util.ArrayList;
import java.util.List;

public enum GeneroEnum {
	
	MASCULINO("Masculino"), FEMENINO("Femenino");

	private String nombre;

	GeneroEnum(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static GeneroEnum obtenerGenero(String generoString) {

		if (generoString.equalsIgnoreCase(GeneroEnum.MASCULINO.getNombre())) {
			return GeneroEnum.MASCULINO;
		}
		if (generoString.equalsIgnoreCase(GeneroEnum.FEMENINO.getNombre())) {
			return GeneroEnum.FEMENINO;
		}
		return null;
	}

	public List<GeneroEnum> getListaGenero() {
		List<GeneroEnum> listaGenero = new ArrayList<>();
		for (GeneroEnum genero : GeneroEnum.values()) {
			listaGenero.add(genero);
		}
		return listaGenero;
	}

}

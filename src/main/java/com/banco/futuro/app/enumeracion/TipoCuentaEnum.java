package com.banco.futuro.app.enumeracion;

import java.util.ArrayList;
import java.util.List;

public enum TipoCuentaEnum {
	
	AHORROS("Ahorros"), CORRIENTE("Corriente");

	private String nombre;

	TipoCuentaEnum(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static TipoCuentaEnum obtenerTipoCuenta(String tipoCuentaString) {

		if (tipoCuentaString.equalsIgnoreCase(TipoCuentaEnum.AHORROS.getNombre())) {
			return TipoCuentaEnum.AHORROS;
		}
		if (tipoCuentaString.equalsIgnoreCase(TipoCuentaEnum.CORRIENTE.getNombre())) {
			return TipoCuentaEnum.CORRIENTE;
		}
		return null;
	}

	public List<TipoCuentaEnum> getListaTipoCuenta() {
		List<TipoCuentaEnum> listaTipoCuenta = new ArrayList<>();
		for (TipoCuentaEnum tipoCuenta : TipoCuentaEnum.values()) {
			listaTipoCuenta.add(tipoCuenta);
		}
		return listaTipoCuenta;
	}

}

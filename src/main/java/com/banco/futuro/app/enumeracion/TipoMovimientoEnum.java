package com.banco.futuro.app.enumeracion;

import java.util.ArrayList;
import java.util.List;

public enum TipoMovimientoEnum {
	
	DEBITO("Debito"), CREDITO("Credito");

	private String nombre;

	TipoMovimientoEnum(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static TipoMovimientoEnum obtenerTipoMovimiento(String tipoMovimientoString) {

		if (tipoMovimientoString.equalsIgnoreCase(TipoMovimientoEnum.DEBITO.getNombre())) {
			return TipoMovimientoEnum.DEBITO;
		}
		if (tipoMovimientoString.equalsIgnoreCase(TipoMovimientoEnum.CREDITO.getNombre())) {
			return TipoMovimientoEnum.CREDITO;
		}
		return null;
	}

	public List<TipoMovimientoEnum> getListaTipoMovimiento() {
		List<TipoMovimientoEnum> listaTipoMovimiento = new ArrayList<>();
		for (TipoMovimientoEnum tipoMovimiento : TipoMovimientoEnum.values()) {
			listaTipoMovimiento.add(tipoMovimiento);
		}
		return listaTipoMovimiento;
	}

}

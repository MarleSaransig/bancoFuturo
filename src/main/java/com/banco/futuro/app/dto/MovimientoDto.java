package com.banco.futuro.app.dto;


import java.math.BigDecimal;
import java.util.Date;

import com.banco.futuro.app.enumeracion.TipoMovimientoEnum;


public class MovimientoDto extends EntidadBaseDto {
	private static final long serialVersionUID = -4175581783648702062L;

	protected Date fecha;
	
	private String tipoMovimiento;
	
	private BigDecimal valor;

	private BigDecimal saldo;

	private CuentaDto cuenta;


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public CuentaDto getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDto cuenta) {
		this.cuenta = cuenta;
	}

}

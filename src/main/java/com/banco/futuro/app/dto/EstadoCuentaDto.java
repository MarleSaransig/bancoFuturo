package com.banco.futuro.app.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.banco.futuro.app.enumeracion.TipoCuentaEnum;
import com.banco.futuro.app.enumeracion.TipoMovimientoEnum;

public class EstadoCuentaDto {
	
	private String nombre;

	private String identificacion;

	private String direccion;
	
	private Integer numeroCuenta;

	private TipoCuentaEnum tipoCuenta;
	
	private Date fecha;
	
	private TipoMovimientoEnum tipoMovimiento;
	
	private BigDecimal valor;

	private BigDecimal saldo;
	

	public EstadoCuentaDto() {

	}
	

	public EstadoCuentaDto(String nombre, String identificacion, String direccion, Integer numeroCuenta,
			Date fecha, BigDecimal valor, BigDecimal saldo, TipoCuentaEnum tipoCuenta,TipoMovimientoEnum tipoMovimiento ) {
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
	}
	
	public EstadoCuentaDto(String nombre, String identificacion, String direccion, Integer numeroCuenta,
			 Date fecha, BigDecimal valor, BigDecimal saldo) {
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.numeroCuenta = numeroCuenta;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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


	public TipoCuentaEnum getTipoCuenta() {
		return tipoCuenta;
	}


	public void setTipoCuenta(TipoCuentaEnum tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}


	public TipoMovimientoEnum getTipoMovimiento() {
		return tipoMovimiento;
	}


	public void setTipoMovimiento(TipoMovimientoEnum tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
	

}

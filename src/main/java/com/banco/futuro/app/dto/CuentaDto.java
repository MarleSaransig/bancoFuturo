package com.banco.futuro.app.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CuentaDto extends EntidadBaseDto {

	private static final long serialVersionUID = 8368699645650495207L;

	private Integer numeroCuenta;

	private String tipoCuenta;

	private BigDecimal saldoInicial;

	private String estado;
	
	private ClienteDto cliente;

	private List<MovimientoDto> listaMovimiento = new ArrayList<>();

	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public List<MovimientoDto> getListaMovimiento() {
		return listaMovimiento;
	}

	public void setListaMovimiento(List<MovimientoDto> listaMovimiento) {
		this.listaMovimiento = listaMovimiento;
	}


	
}

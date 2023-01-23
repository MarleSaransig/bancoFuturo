package com.banco.futuro.app.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.banco.futuro.app.enumeracion.EstadoEnum;
import com.banco.futuro.app.enumeracion.TipoCuentaEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cuenta")
public class Cuenta extends EntidadBase {

	private static final long serialVersionUID = 8368699645650495207L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta")
	private Integer idCuenta;

	@Column(name = "numero_cuenta", nullable = false, length = 12)
	private Integer numeroCuenta;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_cuenta", nullable = false)
	private TipoCuentaEnum tipoCuenta;

	@Column(name = "saldo_inicial", nullable = false)
	private BigDecimal saldoInicial;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private EstadoEnum estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = false)
	@JsonIgnoreProperties(value = { "cliente", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Cliente cliente;

	@OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "listaMovimiento", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private List<Movimiento> listaMovimiento = new ArrayList<>();

	public Cuenta() {

	}

	public Cuenta(Integer numeroCuenta, String tipoCuenta, BigDecimal saldoInicial, String estado, Cliente cliente) {
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = TipoCuentaEnum.obtenerTipoCuenta(tipoCuenta);
		this.saldoInicial = saldoInicial;
		this.estado = EstadoEnum.obtenerEstado(estado);
		this.cliente = cliente;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public TipoCuentaEnum getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuentaEnum tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Movimiento> getListaMovimiento() {
		return listaMovimiento;
	}

	public void setListaMovimiento(List<Movimiento> listaMovimiento) {
		this.listaMovimiento = listaMovimiento;
	}

}

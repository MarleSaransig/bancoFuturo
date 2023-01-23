package com.banco.futuro.app.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.banco.futuro.app.enumeracion.TipoMovimientoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "movimiento")
public class Movimiento extends EntidadBase {
	private static final long serialVersionUID = -4175581783648702062L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movimiento")
	private Integer idMovimiento;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = true)
    private Date fecha;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_movimiento", nullable = false)
	private TipoMovimientoEnum tipoMovimiento;

	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@Column(name = "saldo", nullable = false)
	private BigDecimal saldo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cuenta", nullable = true)
	@JsonIgnoreProperties(value = { "cuenta", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Cuenta cuenta;
	
	@Column(name = "valor_retiro", nullable = true)
	private BigDecimal valorAcumuladoRetiroDiario;

	public Movimiento() {

	}

	public Movimiento(Date fecha, String tipoMovimiento, BigDecimal valor, BigDecimal saldo,
			Cuenta cuenta) {
		super();
		this.fecha = fecha;
		this.tipoMovimiento = TipoMovimientoEnum.obtenerTipoMovimiento(tipoMovimiento);
		this.valor = valor;
		this.saldo = saldo;
		this.cuenta = cuenta;
	}

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoMovimientoEnum getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimientoEnum tipoMovimiento) {
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

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public BigDecimal getValorAcumuladoRetiroDiario() {
		return valorAcumuladoRetiroDiario;
	}

	public void setValorAcumuladoRetiroDiario(BigDecimal valorAcumuladoRetiroDiario) {
		this.valorAcumuladoRetiroDiario = valorAcumuladoRetiroDiario;
	}

	

}

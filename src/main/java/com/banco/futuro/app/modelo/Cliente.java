package com.banco.futuro.app.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.banco.futuro.app.enumeracion.EstadoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cliente")
public class Cliente extends EntidadBase implements Serializable {

	private static final long serialVersionUID = 6747553019607390414L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer idCliente;

	@Column(name = "clave", nullable = false, length = 12)
	protected String clave;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private EstadoEnum estado;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "persona", "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
	private Persona persona;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	@JsonIgnoreProperties(value = { "listaCuenta", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private List<Cuenta> listaCuenta = new ArrayList<>();

	public Cliente() {

	}

	public Cliente(String clave, String estado, Persona persona) {
		this.clave = clave;
		this.estado = EstadoEnum.obtenerEstado(estado);
		this.persona = persona;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Cuenta> getListaCuenta() {
		return listaCuenta;
	}

	public void setListaCuenta(List<Cuenta> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}

}

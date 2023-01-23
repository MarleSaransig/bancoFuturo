package com.banco.futuro.app.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.banco.futuro.app.enumeracion.GeneroEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "persona")
public class Persona extends EntidadBase {

	private static final long serialVersionUID = -5857258305547313581L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona")
	private Integer idPersona;

	@Column(name = "nombre", nullable = false, length = 250)
	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private GeneroEnum genero;

	@Column(name = "edad", length = 3)
	private Integer edad;

	@Column(name = "identificacion", nullable = false, length = 100)
	private String identificacion;

	@Column(name = "direccion", length = 250)
	private String direccion;

	@Column(name = "telefono", length = 250)
	private String telefono;

	@JsonIgnore
	@JsonIgnoreProperties(value = { "cliente", "hibernateLazyInitializer", "handler" })
	@OneToOne(mappedBy = "persona")
	private Cliente cliente;

	public Persona() {

	}

	public Persona(String nombre, String genero, Integer edad, String identificacion, String direccion,
			String telefono) {
		this.nombre = nombre;
		this.genero = GeneroEnum.obtenerGenero(genero);
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public GeneroEnum getGenero() {
		return genero;
	}

	public void setGenero(GeneroEnum genero) {
		this.genero = genero;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}

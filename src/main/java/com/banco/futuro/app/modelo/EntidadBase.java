package com.banco.futuro.app.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadBase implements Serializable {

	private static final long serialVersionUID = -480825653854463386L;

	@Column(name = "fecha_creacion", insertable = true, updatable = false, nullable = true)
	protected Date fechaCreacion;

	@Column(name = "usuario_creacion", insertable = true, updatable = false, nullable = true, length = 50)
	protected String usuarioCreacion;

	@Column(name = "fecha_modificacion", insertable = false, updatable = true, nullable = true)
	protected Date fechaModificacion;

	@Column(name = "usuario_modificacion", insertable = false, updatable = true, nullable = true, length = 50)
	protected String usuarioModificacion;

	@Column(name = "eliminado")
	protected Boolean eliminado = Boolean.FALSE;

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

}

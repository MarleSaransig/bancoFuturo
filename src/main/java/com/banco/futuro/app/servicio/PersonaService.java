package com.banco.futuro.app.servicio;

import com.banco.futuro.app.modelo.Persona;

public interface PersonaService {
	
	Persona buscarPersonaPorIdentificacion(String identificacion);
	
	void eliminarPersonaPorIdPersona(Integer idPersona);
	
	Persona guardar(Persona persona);

}

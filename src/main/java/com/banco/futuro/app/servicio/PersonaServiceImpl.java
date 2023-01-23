package com.banco.futuro.app.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banco.futuro.app.modelo.Persona;
import com.banco.futuro.app.repositorio.PersonaRepositorio;





@Service
public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	private PersonaRepositorio personaRepositorio;


	@Override
	@Transactional(readOnly=true)
	public Persona buscarPersonaPorIdentificacion(String identificacion) {
		return personaRepositorio.buscarPersonaPorIdentificacion(identificacion);
	}
	
	@Override
	@Transactional
	public Persona guardar(Persona persona) {
		return personaRepositorio.save(persona);
	}


	@Override
	public void eliminarPersonaPorIdPersona(Integer idPersona) {
		personaRepositorio.deleteById(idPersona);
	}
}

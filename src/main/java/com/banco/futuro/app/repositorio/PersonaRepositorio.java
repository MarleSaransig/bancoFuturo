package com.banco.futuro.app.repositorio;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banco.futuro.app.modelo.Persona;

@Repository
public interface PersonaRepositorio  extends JpaRepository<Persona, Integer> {

	@Query("SELECT p FROM Persona p WHERE p.identificacion = :identificacion")
	Persona buscarPersonaPorIdentificacion(@Param("identificacion") String identificacion);

	Persona deleteByIdentificacion(String identificacion);

}

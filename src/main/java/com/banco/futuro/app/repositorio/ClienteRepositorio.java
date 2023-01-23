package com.banco.futuro.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banco.futuro.app.modelo.Cliente;


@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
	
	@Query("SELECT c FROM Cliente c WHERE c.persona.identificacion = :identificacion")
	Cliente findByIdentificacion(@Param("identificacion") String identificacion);
	
	
	@Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.listaCuenta lc WHERE c.persona.identificacion = :identificacion")
	Cliente buscarClienteCuentasPorIdentificacion(@Param("identificacion") String identificacion);

}

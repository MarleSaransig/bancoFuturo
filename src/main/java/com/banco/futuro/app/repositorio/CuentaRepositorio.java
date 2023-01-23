package com.banco.futuro.app.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banco.futuro.app.modelo.Cliente;
import com.banco.futuro.app.modelo.Cuenta;


@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Integer> {
	
	
	@Query("SELECT c FROM Cuenta c WHERE c.cliente = :cliente")
	List<Cuenta> buscarCuentasPorCliente(@Param("cliente") Cliente cliente);
	
	
	@Query("SELECT c FROM Cuenta c WHERE c.cliente = :cliente and c.numeroCuenta =:numeroCuenta")
	Cuenta buscarCuentaPorClientePorNumeroCuenta(@Param("cliente") Cliente cliente,@Param("numeroCuenta") Integer numeroCuenta);

}

package com.banco.futuro.app.repositorio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banco.futuro.app.dto.EstadoCuentaDto;
import com.banco.futuro.app.modelo.Cuenta;
import com.banco.futuro.app.modelo.Movimiento;


@Repository
public interface MovimientoRepositorio extends JpaRepository<Movimiento, Integer> {
	
	
	@Query("SELECT m FROM Movimiento m WHERE m.cuenta = :cuenta")
	List<Movimiento> buscarMovimientoPorCuenta(@Param("cuenta") Cuenta cuenta);
	
	@Query("SELECT m FROM Movimiento m WHERE m.cuenta = :cuenta and m.idMovimiento = :idMovimiento")
	Movimiento buscarMovimientoPorIdMovimientoPorCuenta(@Param("idMovimiento") Integer idMovimiento, @Param("cuenta") Cuenta cuenta);
	
	@Query("SELECT new com.banco.futuro.app.dto.EstadoCuentaDto(per.nombre, per.identificacion, "
			+ "per.direccion,cue.numeroCuenta,mov.fecha, mov.saldo, mov.valor, cue.tipoCuenta,mov.tipoMovimiento) "
			+ "FROM Cuenta cue JOIN cue.listaMovimiento mov JOIN cue.cliente cli "
			+ "JOIN cli.persona per WHERE mov.cuenta in :listaCuenta "
			+ "AND mov.fecha BETWEEN :fechaDesde AND :fechaHasta")
	List<EstadoCuentaDto> obtenerEstadoCuenta(@Param("listaCuenta") List<Cuenta> listaCuenta,
			@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);

}

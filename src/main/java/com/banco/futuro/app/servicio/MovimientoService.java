package com.banco.futuro.app.servicio;

import java.util.Date;
import java.util.List;

import com.banco.futuro.app.dto.EstadoCuentaDto;
import com.banco.futuro.app.modelo.Cuenta;
import com.banco.futuro.app.modelo.Movimiento;

public interface MovimientoService {

	Movimiento guardar(Movimiento movimiento);

	void eliminarMovimientoPorIdMovimiento(Integer idMovimiento);

	List<Movimiento> buscarMovimientoPorCuenta(Cuenta cuenta);
	
	Movimiento buscarMovimientoPorIdPorCuenta(Integer idMovimiento, Cuenta cuenta);
	
	List<EstadoCuentaDto> obtenerEstadoCuenta(List<Cuenta> listaCuenta, Date fechaDesde, Date fechaHasta);

}

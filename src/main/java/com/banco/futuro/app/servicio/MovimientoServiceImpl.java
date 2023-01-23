package com.banco.futuro.app.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banco.futuro.app.dto.EstadoCuentaDto;
import com.banco.futuro.app.modelo.Cuenta;
import com.banco.futuro.app.modelo.Movimiento;
import com.banco.futuro.app.repositorio.MovimientoRepositorio;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	private MovimientoRepositorio movimientoRepositorio;

	@Override
	@Transactional(readOnly = true)
	public List<Movimiento> buscarMovimientoPorCuenta(Cuenta cuenta) {
		return movimientoRepositorio.buscarMovimientoPorCuenta(cuenta);
	}

	@Override
	@Transactional
	public Movimiento guardar(Movimiento movimiento) {
		return movimientoRepositorio.save(movimiento);
	}

	@Override
	@Transactional
	public void eliminarMovimientoPorIdMovimiento(Integer idMovimiento) {
		movimientoRepositorio.deleteById(idMovimiento);
	}

	@Override
	public Movimiento buscarMovimientoPorIdPorCuenta(Integer idMovimiento, Cuenta cuenta) {
		return movimientoRepositorio.buscarMovimientoPorIdMovimientoPorCuenta(idMovimiento, cuenta);
	}

	@Override
	public List<EstadoCuentaDto> obtenerEstadoCuenta(List<Cuenta> listaCuenta, Date fechaDesde, Date fechaHasta) {
		return movimientoRepositorio.obtenerEstadoCuenta(listaCuenta,fechaDesde,fechaHasta);
	}


}

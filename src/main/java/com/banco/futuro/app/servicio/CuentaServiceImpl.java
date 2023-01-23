package com.banco.futuro.app.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banco.futuro.app.modelo.Cliente;
import com.banco.futuro.app.modelo.Cuenta;
import com.banco.futuro.app.repositorio.CuentaRepositorio;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaRepositorio cuentaRepositorio;

	@Override
	@Transactional(readOnly = true)
	public List<Cuenta> buscarCuentasPorCliente(Cliente cliente) {
		return cuentaRepositorio.buscarCuentasPorCliente(cliente);
	}

	@Override
	@Transactional
	public Cuenta guardar(Cuenta cuenta) {
		return cuentaRepositorio.save(cuenta);
	}

	@Override
	@Transactional
	public void eliminarCuentaPorIdCuenta(Integer idCuenta) {
		cuentaRepositorio.deleteById(idCuenta);
	}

	@Override
	@Transactional(readOnly = true)
	public Cuenta buscarCuentaPorClientePorNumeroCuenta(Cliente cliente, Integer numeroCuenta) {
		return cuentaRepositorio.buscarCuentaPorClientePorNumeroCuenta(cliente, numeroCuenta);
	}

}

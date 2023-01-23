package com.banco.futuro.app.servicio;

import java.util.List;

import com.banco.futuro.app.modelo.Cliente;
import com.banco.futuro.app.modelo.Cuenta;

public interface CuentaService {

	Cuenta guardar(Cuenta cuenta);

	void eliminarCuentaPorIdCuenta(Integer idCuenta);

	List<Cuenta> buscarCuentasPorCliente(Cliente cliente);

	Cuenta buscarCuentaPorClientePorNumeroCuenta(Cliente cliente, Integer numeroCuenta);

}

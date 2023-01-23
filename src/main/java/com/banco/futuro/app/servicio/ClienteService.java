package com.banco.futuro.app.servicio;

import com.banco.futuro.app.modelo.Cliente;

public interface ClienteService {
	
	Cliente buscarClientePorIdentificacion(String identificacion);
	
	Cliente guardar(Cliente cliente);
	
	void eliminarClientePorIdCliente(Integer idCliente);
	
	Cliente buscarClienteCuentasPorIdentificacion(String identificacion);

}

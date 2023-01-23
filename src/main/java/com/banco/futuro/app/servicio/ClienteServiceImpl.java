package com.banco.futuro.app.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banco.futuro.app.modelo.Cliente;
import com.banco.futuro.app.repositorio.ClienteRepositorio;


@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;


	@Override
	@Transactional(readOnly=true)
	public Cliente buscarClientePorIdentificacion(String identificacion) {
		return clienteRepositorio.findByIdentificacion(identificacion);
	}
	
	@Override
	@Transactional
	public Cliente guardar(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	
	@Override
	@Transactional
	public void eliminarClientePorIdCliente(Integer idCliente) {
		clienteRepositorio.deleteById(idCliente);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Cliente buscarClienteCuentasPorIdentificacion(String identificacion) {
		return clienteRepositorio.buscarClienteCuentasPorIdentificacion(identificacion);
	}


}

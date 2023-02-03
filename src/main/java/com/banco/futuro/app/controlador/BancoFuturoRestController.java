package com.banco.futuro.app.controlador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banco.futuro.app.dto.DatosConsultaReporteDto;
import com.banco.futuro.app.dto.EstadoCuentaDto;
import com.banco.futuro.app.enumeracion.TipoMovimientoEnum;
import com.banco.futuro.app.modelo.Cliente;
import com.banco.futuro.app.modelo.Cuenta;
import com.banco.futuro.app.modelo.Movimiento;
import com.banco.futuro.app.modelo.Persona;
import com.banco.futuro.app.servicio.ClienteService;
import com.banco.futuro.app.servicio.CuentaService;
import com.banco.futuro.app.servicio.MovimientoService;
import com.banco.futuro.app.servicio.PersonaService;
import com.banco.futuro.app.util.Constantes;

@CrossOrigin(origins = { "localhost:4200", "*" })
@RestController
@RequestMapping("/bancoFuturo")
public class BancoFuturoRestController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private CuentaService cuentaService;

	@Autowired
	private MovimientoService movimientoService;

	@GetMapping("/consultarPersonaPorIdentificacion/{identificacion}")
	public ResponseEntity<?> consultarPersonaPorIdentificacion(@PathVariable String identificacion) {
		Persona persona = null;
		Map<String, Object> response = new HashMap<>();
		try {
			persona = personaService.buscarPersonaPorIdentificacion(identificacion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (persona == null) {
			response.put("mensaje", "Persona con identificación: "
					.concat(identificacion.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}

	@PutMapping("/crearActualizarPersona")
	public ResponseEntity<?> crearActualizarPersona(@Valid @RequestBody Persona persona, BindingResult result) {
		Persona personaActual = null;
		if (persona != null) {
			personaActual = personaService.buscarPersonaPorIdentificacion(persona.getIdentificacion());
		}
		Boolean esCreacion = Boolean.FALSE;
		Persona personaUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			if (personaActual == null) {
				esCreacion = Boolean.TRUE;
				personaActual = new Persona(persona.getNombre(), persona.getGenero().getNombre(), persona.getEdad(),
						persona.getIdentificacion(), persona.getDireccion(), persona.getTelefono());
				personaActual.setFechaCreacion(new Date());
				personaActual.setUsuarioCreacion(Constantes.USUARIO);
			} else {
				personaActual.setNombre(persona.getNombre());
				personaActual.setGenero(persona.getGenero());
				personaActual.setEdad(persona.getEdad());
				personaActual.setIdentificacion(persona.getIdentificacion());
				personaActual.setDireccion(persona.getDireccion());
				personaActual.setTelefono(persona.getTelefono());
				personaActual.setFechaModificacion(new Date());
				personaActual.setUsuarioModificacion(Constantes.USUARIO);
			}

			personaUpdated = personaService.guardar(personaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la persona en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (esCreacion) {
			response.put("mensaje", "La persona ha sido creada con éxito!");
		} else {
			response.put("mensaje", "La persona ha sido modificada con éxito!");
		}

		response.put("persona", personaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/eliminarPersonaPorIdentificacion/{identificacion}")
	public ResponseEntity<?> delete(@PathVariable String identificacion) {

		Map<String, Object> response = new HashMap<>();
		Persona persona = personaService.buscarPersonaPorIdentificacion(identificacion);
		try {
			if (persona != null) {
				personaService.eliminarPersonaPorIdPersona(persona.getIdPersona());
			} else {
				response.put("mensaje", "Persona con identificación: ".concat(
						identificacion.toString().concat(" no existe en la base de datos, no puede ser eliminada")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la persona de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Persona eliminada con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/consultarClientePorIdentificacion/{identificacion}")
	public ResponseEntity<?> consultarClientePorIdentificacion(@PathVariable String identificacion) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.buscarClientePorIdentificacion(identificacion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "Cliente con identificación: "
					.concat(identificacion.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PutMapping("/crearActualizarCliente")
	public ResponseEntity<?> crearActualizarCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Cliente clienteActual = null;
		String identificacion = null;
		Persona persona = null;
		Boolean esCreacion = Boolean.FALSE;
		Cliente clienteUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			if (cliente != null && cliente.getPersona() != null) {
				identificacion = cliente.getPersona().getIdentificacion();
				persona = personaService.buscarPersonaPorIdentificacion(identificacion);
				clienteActual = clienteService.buscarClientePorIdentificacion(identificacion);
			}
			persona = obtenerPersona(cliente, identificacion, persona);
			persona = personaService.guardar(persona);
			if (clienteActual == null) {
				esCreacion = Boolean.TRUE;
				clienteActual = new Cliente(cliente.getClave(), cliente.getEstado().getNombre(), persona);
				clienteActual.setFechaCreacion(new Date());
				clienteActual.setUsuarioCreacion(Constantes.USUARIO);
			} else {
				clienteActual = fijarDatosCliente(clienteActual, cliente, persona);
			}
			clienteUpdated = clienteService.guardar(clienteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al guardar al cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (esCreacion) {
			response.put("mensaje", "El cliente ha sido creado con éxito!");
		} else {
			response.put("mensaje", "El cliente ha sido modificado con éxito!");
		}

		response.put("cliente", clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	public Persona obtenerPersona(Cliente cliente, String identificacion, Persona persona) {
		if (persona == null) {
			persona = new Persona(cliente.getPersona().getNombre(), cliente.getPersona().getGenero().getNombre(),
					cliente.getPersona().getEdad(), cliente.getPersona().getIdentificacion(),
					cliente.getPersona().getDireccion(), cliente.getPersona().getTelefono());
			persona.setFechaCreacion(new Date());
			persona.setUsuarioCreacion(Constantes.USUARIO);
		} else {
			persona = fijarDatosPersona(cliente, persona);
		}
		return persona;
	}

	public Persona fijarDatosPersona(Cliente cliente, Persona persona) {
		persona.setNombre(cliente.getPersona().getNombre());
		persona.setGenero(cliente.getPersona().getGenero());
		persona.setEdad(cliente.getPersona().getEdad());
		persona.setIdentificacion(cliente.getPersona().getIdentificacion());
		persona.setDireccion(cliente.getPersona().getDireccion());
		persona.setTelefono(cliente.getPersona().getTelefono());
		persona.setFechaModificacion(new Date());
		persona.setUsuarioModificacion(Constantes.USUARIO);
		return persona;
	}

	public Cliente fijarDatosCliente(Cliente clienteActual, Cliente cliente, Persona persona) {
		clienteActual.setClave(cliente.getClave());
		clienteActual.setEstado(cliente.getEstado());
		clienteActual.setPersona(persona);
		clienteActual.setFechaModificacion(new Date());
		clienteActual.setUsuarioModificacion(Constantes.USUARIO);
		return clienteActual;
	}

	@PutMapping("/eliminarClientePorIdentificacion/{identificacion}")
	public ResponseEntity<?> eliminarClientePorIdentificacion(@PathVariable String identificacion) {
		Cliente clienteActual = null;

		Map<String, Object> response = new HashMap<>();
		try {
			clienteActual = clienteService.buscarClientePorIdentificacion(identificacion);
			if (clienteActual == null) {
				response.put("mensaje", "Cliente con identificación: ".concat(
						identificacion.toString().concat(" no existe en la base de datos, no puede ser eliminada")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			} else {
				clienteService.eliminarClientePorIdCliente(clienteActual.getIdCliente());
			}

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar al cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/consultarCuentasClientePorIdentificacion/{identificacion}")
	public ResponseEntity<?> consultarCuentasClientePorIdentificacion(@PathVariable String identificacion) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.buscarClienteCuentasPorIdentificacion(identificacion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "Cliente con identificación: "
					.concat(identificacion.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PutMapping("/crearActualizarCuenta")
	public ResponseEntity<?> crearActualizarCuenta(@Valid @RequestBody Cuenta cuenta, BindingResult result) {
		Cliente cliente = null;
		Cuenta cuentaActual = null;
		String identificacion = null;
		Boolean esCreacion = Boolean.FALSE;
		Cuenta cuentaUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			if (cuenta != null && cuenta.getCliente() != null) {
				identificacion = cuenta.getCliente().getPersona().getIdentificacion();
				cliente = clienteService.buscarClientePorIdentificacion(identificacion);
				cuentaActual = cuentaService.buscarCuentaPorClientePorNumeroCuenta(cliente, cuenta.getNumeroCuenta());
			}
			if (cliente == null) {
				response.put("mensaje", "Cliente con identificación: "
						.concat(identificacion.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if (cuentaActual == null) {
				esCreacion = Boolean.TRUE;
				cuentaActual = new Cuenta(cuenta.getNumeroCuenta(), cuenta.getTipoCuenta().getNombre(),
						cuenta.getSaldoInicial(), cuenta.getEstado().getNombre(), cliente);
				cuentaActual.setFechaCreacion(new Date());
				cuentaActual.setUsuarioCreacion(Constantes.USUARIO);
			} else {
				cuentaActual = fijarDatosCuenta(cuentaActual, cuenta, cliente);
			}
			cuentaUpdated = cuentaService.guardar(cuentaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al guardar cuenta cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (esCreacion) {
			response.put("mensaje", "La cuenta ha sido creada con éxito!");
		} else {
			response.put("mensaje", "La cuenta ha sido modificada con éxito!");
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	public Cuenta fijarDatosCuenta(Cuenta cuentaActual, Cuenta cuenta, Cliente cliente) {
		cuentaActual.setEstado(cuenta.getEstado());
		cuentaActual.setCliente(cliente);
		cuentaActual.setSaldoInicial(cuenta.getSaldoInicial());
		cuentaActual.setFechaModificacion(new Date());
		cuentaActual.setUsuarioModificacion(Constantes.USUARIO);
		return cuentaActual;
	}

	@PutMapping("/eliminarCuentaPorClienteNumeroCuenta/{identificacion}/{numeroCuenta}")
	public ResponseEntity<?> eliminarCuentaPorClienteNumeroCuenta(@PathVariable String identificacion,
			@PathVariable Integer numeroCuenta) {
		Cliente cliente = null;
		Cuenta cuenta = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.buscarClientePorIdentificacion(identificacion);
			if (cliente == null) {
				response.put("mensaje", "Cliente con identificación: ".concat(
						identificacion.toString().concat(" no existe en la base de datos, no puede ser eliminada")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			} else {
				cuenta = cuentaService.buscarCuentaPorClientePorNumeroCuenta(cliente, numeroCuenta);
				if (cuenta == null) {
					response.put("mensaje",
							"Cuenta : ".concat(numeroCuenta.toString().concat(" para el cliente con identificacion ")
									.concat(identificacion.toString())
									.concat(" no existe en la base de datos, no puede ser eliminada")));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				} else {
					cuentaService.eliminarCuentaPorIdCuenta(cuenta.getIdCuenta());
				}

			}

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la cuenta del cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La cuenta ha sido eliminada con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/crearActualizarMovimientoCuenta")
	public ResponseEntity<?> crearActualizarMovimientoCuenta(@Valid @RequestBody Movimiento movimiento,
			BindingResult result) {
		Cliente cliente = null;
		Cuenta cuenta = null;
		Movimiento movimientoActual = null;
		String identificacion = null;
		Boolean esCreacion = Boolean.FALSE;
		Movimiento movimientoUpdated = null;
		BigDecimal saldoCalculado = BigDecimal.ZERO;
		BigDecimal saldoActual = BigDecimal.ZERO;
		BigDecimal valorDebito = BigDecimal.ZERO;
		BigDecimal valorCredito = BigDecimal.ZERO;
		BigDecimal valorFinal = BigDecimal.ZERO;
		BigDecimal valorDiarioDebito = BigDecimal.ZERO;
		Date fechaActual = new Date();
		Date fechaUltimoMovimiento = null;
		String tipoMovimiento = null;
		List<Movimiento> listaMovimiento = new ArrayList();
		Movimiento ultimoMovimiento = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			if (movimiento != null && movimiento.getCuenta() != null && movimiento.getCuenta().getCliente() != null) {
				identificacion = movimiento.getCuenta().getCliente().getPersona().getIdentificacion();
				cliente = clienteService.buscarClientePorIdentificacion(identificacion);
				cuenta = cuentaService.buscarCuentaPorClientePorNumeroCuenta(cliente,
						movimiento.getCuenta().getNumeroCuenta());
				if (movimiento.getIdMovimiento() != null) {
					movimientoActual = movimientoService.buscarMovimientoPorIdPorCuenta(movimiento.getIdMovimiento(),
							cuenta);
				}
				listaMovimiento = movimientoService.buscarMovimientoPorCuenta(cuenta);
			}
			if (cliente == null) {
				response.put("mensaje", "Cliente con identificación: "
						.concat(identificacion.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			if (cuenta == null) {
				response.put("mensaje",
						"Cuenta : ".concat(movimiento.getCuenta().getNumeroCuenta().toString()
								.concat(" para el cliente con identificacion ").concat(identificacion.toString())
								.concat(" no existe en la base de datos, no puede ser eliminada")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			if (!listaMovimiento.isEmpty()) {
				ultimoMovimiento = Collections.max(listaMovimiento, Comparator.comparing(Movimiento::getFecha));
				if (ultimoMovimiento != null) {
					saldoActual = ultimoMovimiento.getSaldo();
					fechaUltimoMovimiento = ultimoMovimiento.getFecha();
					valorFinal = movimiento.getValor();
					if (verificarMismoDia(fechaUltimoMovimiento, fechaActual)) {
						valorDiarioDebito = ultimoMovimiento.getValorAcumuladoRetiroDiario();
					}
					if (movimiento.getTipoMovimiento().equals(TipoMovimientoEnum.CREDITO)) {
						valorCredito = movimiento.getValor();
						saldoCalculado = saldoActual.add(valorCredito);
					} else {
						valorDebito = movimiento.getValor();
						if (saldoActual.equals(BigDecimal.ZERO.setScale(2))) {
							response.put("mensaje",
									"Saldo actual de la cuenta : ".concat(movimiento.getCuenta().getNumeroCuenta()
											.toString().concat(" para el cliente con identificacion ")
											.concat(identificacion.toString())
											.concat(" es 0, no puede ser debitada la cantidad")));
							return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
						}
						valorDiarioDebito = valorDiarioDebito.add(valorDebito);
						if (valorDiarioDebito.compareTo(Constantes.VALOR_LIMITE_DIARIO) == 1) {
							response.put("mensaje",
									"Cupo diario excedido de la cuenta : "
											.concat(movimiento.getCuenta().getNumeroCuenta().toString()
													.concat(" para el cliente con identificacion ")
													.concat(identificacion.toString())
													.concat(", no puede ser debitada la cantidad")));
							return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
						}
						saldoCalculado = saldoActual.add(valorDebito.negate());
					}
				}
			}
			if (movimientoActual == null) {
				esCreacion = Boolean.TRUE;
				if (cuenta.getSaldoInicial() != null && listaMovimiento.isEmpty()) {
					tipoMovimiento = TipoMovimientoEnum.CREDITO.getNombre();
					valorFinal = valorFinal.add(cuenta.getSaldoInicial());
					saldoCalculado = saldoCalculado.add(valorFinal);
					movimientoActual = new Movimiento(movimiento.getFecha(), tipoMovimiento,
							valorFinal, saldoCalculado, cuenta);
					movimientoUpdated = movimientoService.guardar(movimientoActual);
					valorFinal = BigDecimal.ZERO;
					if (movimiento.getTipoMovimiento().equals(TipoMovimientoEnum.CREDITO)) {
						valorCredito = movimiento.getValor();
						tipoMovimiento = TipoMovimientoEnum.CREDITO.getNombre();
						valorFinal = valorFinal.add(valorCredito);
					} else {
						valorDebito = movimiento.getValor();
						valorFinal = valorFinal.add(valorDebito.negate());
						tipoMovimiento = TipoMovimientoEnum.DEBITO.getNombre();
						valorDiarioDebito = valorDiarioDebito.add(valorDebito);
					}

				} 
				saldoCalculado = saldoCalculado.add(valorFinal);
				movimientoActual = new Movimiento(movimiento.getFecha(), movimiento.getTipoMovimiento().getNombre(),
						valorFinal, saldoCalculado, cuenta);
				movimientoActual.setValorAcumuladoRetiroDiario(valorDiarioDebito);
				movimientoActual.setFechaCreacion(new Date());
				movimientoActual.setUsuarioCreacion(Constantes.USUARIO);
			} else {
				movimiento.setValor(valorFinal);
				movimiento.setSaldo(saldoCalculado);
				movimiento.setValorAcumuladoRetiroDiario(valorDiarioDebito);
				movimientoActual = fijarDatosMovimiento(movimientoActual, movimiento, cuenta);
				movimientoActual.setValorAcumuladoRetiroDiario(valorDiarioDebito);
			}
			movimientoUpdated = movimientoService.guardar(movimientoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al guardar movimiento cuenta cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (esCreacion) {
			response.put("mensaje", "El movimiento ha sido creado con éxito!");
		} else {
			response.put("mensaje", "El movimiento ha sido modificado con éxito!");
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	public Movimiento fijarDatosMovimiento(Movimiento movimientoActual, Movimiento movimiento, Cuenta cuenta) {
		movimientoActual.setCuenta(cuenta);
		movimientoActual.setSaldo(movimiento.getSaldo());
		movimientoActual.setValor(movimiento.getValor());
		movimientoActual.setTipoMovimiento(movimiento.getTipoMovimiento());
		movimientoActual.setFecha(movimiento.getFecha());
		movimientoActual.setFechaModificacion(new Date());
		movimientoActual.setUsuarioModificacion(Constantes.USUARIO);
		movimientoActual.setValorAcumuladoRetiroDiario(movimiento.getValorAcumuladoRetiroDiario());
		return movimientoActual;
	}

	public static boolean verificarMismoDia(Date fecha, Date fecha2) {
		LocalDate localDate1 = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDate2 = fecha2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate1.isEqual(localDate2);
	}
	
	
	@PostMapping("/obtenerEstadoCuenta")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> obtenerEstadoCuenta(@Valid @RequestBody DatosConsultaReporteDto datosConsultaReporteDto, BindingResult result) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		List<EstadoCuentaDto> listaEstadoCuentaDto = new ArrayList();
		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors()
					.stream()
					.map( error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			cliente = clienteService.buscarClienteCuentasPorIdentificacion(datosConsultaReporteDto.getIdentificacion());
			List<Cuenta> listaCuenta = cuentaService.buscarCuentasPorCliente(cliente);
			listaEstadoCuentaDto = movimientoService.obtenerEstadoCuenta(listaCuenta,datosConsultaReporteDto.getFechaDesde(),datosConsultaReporteDto.getFechaHasta()); 
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (listaEstadoCuentaDto == null) {
			response.put("mensaje", "No existe datos para el reporte del cliente con identificación: "
					.concat(datosConsultaReporteDto.getIdentificacion().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			response.put("reporte", listaEstadoCuentaDto);
		} 	
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
}
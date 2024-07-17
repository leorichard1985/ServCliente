package com.fiap.servCliente.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.servCliente.records.ClienteRecords;
import com.fiap.servCliente.services.interfaces.ClienteService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	public CompletableFuture<ResponseEntity<Object>> ListarClistes() {

		try {

			List<ClienteRecords> clientes = service.ListarClientes();

			if (Objects.isNull(clientes)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.ok().body(clientes));
			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}
	}

	@GetMapping("/{idCliente}")
	public CompletableFuture<ResponseEntity<Object>> BuscarClientePorId(
			@PathVariable("idCliente") Integer idCliente) {

		try {

			ClienteRecords cliente = service.BuscarClientePorId(idCliente);

			if (Objects.isNull(cliente)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.ok().body(cliente));
			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}

	}

	@PostMapping()
	@Transactional
	public CompletableFuture<ResponseEntity<Object>> CriarCliente(@RequestBody ClienteRecords objCriarCliente)
			throws URISyntaxException {

		try {

			ClienteRecords cliente = service.CriarCliente(objCriarCliente);
			
			if (Objects.isNull(cliente)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(cliente));
			}

			

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}

	}

	@PutMapping("/{idCliente}")
	public CompletableFuture<ResponseEntity<Object>> AtualizarCliente(@PathVariable("idCliente") Integer idCliente,
			@RequestBody ClienteRecords objAtualizarCliente) {

		try {

			ClienteRecords cliente = service.AtualizarCliente(idCliente, objAtualizarCliente);

			if (Objects.isNull(cliente)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.ok().body(cliente));
			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}
	}

	@DeleteMapping("/{idCliente}")
	public CompletableFuture<ResponseEntity<Object>> DeletarCliente(@PathVariable("idCliente") Integer idCliente) {

		try {

			ClienteRecords cliente = service.BuscarClientePorId(idCliente);

			if (Objects.isNull(cliente)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				service.DeletarCliente(idCliente);

				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());

			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}
	}

	
}

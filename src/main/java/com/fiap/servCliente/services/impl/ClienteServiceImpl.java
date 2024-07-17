package com.fiap.servCliente.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fiap.servCliente.model.jpaStructure.ClienteJpa;
import com.fiap.servCliente.records.ClienteRecords;
import com.fiap.servCliente.repository.ClienteRepository;
import com.fiap.servCliente.services.interfaces.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private ClienteRepository repository;

	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ClienteRecords> ListarClientes() {

		List<ClienteJpa> lstJpa = repository.findAll();

		if (lstJpa.isEmpty()) {

			return null;

		} else {

			List<ClienteRecords> lstCliente = new ArrayList<>();

			for (int i = 0; i < lstJpa.size() - 1; i++) {

				ClienteRecords itemCliente = new ClienteRecords(lstJpa.get(i).idCliente, lstJpa.get(i).nomeCliente,
						lstJpa.get(i).enderecoCliente, lstJpa.get(i).cepCliente, lstJpa.get(i).cidadeCliente,
						lstJpa.get(i).estadoCliente);

				lstCliente.add(itemCliente);

			}

			return lstCliente;

		}
	}

	@Override
	public ClienteRecords AtualizarCliente(Integer idCliente, ClienteRecords objAtualizarCliente) {

		Optional<ClienteJpa> optJpa = repository.findById(idCliente);

		if (optJpa.isEmpty()) {

			return null;

		} else {

			optJpa.get().nomeCliente = objAtualizarCliente.nomeCliente();
			optJpa.get().enderecoCliente = objAtualizarCliente.enderecoCliente();
			optJpa.get().cepCliente = objAtualizarCliente.cepCliente();
			optJpa.get().cidadeCliente = objAtualizarCliente.cidadeCliente();
			optJpa.get().estadoCliente = objAtualizarCliente.estadoCliente();

			ClienteJpa optJpaPreSaved = optJpa.get();

			ClienteJpa optJpaSaved = repository.save(optJpaPreSaved);

			return new ClienteRecords(optJpaSaved.idCliente, optJpaSaved.nomeCliente, optJpaSaved.enderecoCliente,
					optJpaSaved.cepCliente, optJpaSaved.cidadeCliente, optJpaSaved.estadoCliente);

		}

	}

	@Override
	public ClienteRecords BuscarClientePorId(Integer idCliente) {

		Optional<ClienteJpa> optJpa = repository.findById(idCliente);

		if (optJpa.isEmpty()) {

			return null;

		} else {

			return new ClienteRecords(optJpa.get().idCliente, optJpa.get().nomeCliente, optJpa.get().enderecoCliente,
					optJpa.get().cepCliente, optJpa.get().cidadeCliente, optJpa.get().estadoCliente);

		}

	}

	@Override
	public ClienteRecords CriarCliente(ClienteRecords objCriarCliente) {
		ClienteJpa clienteJpa = new ClienteJpa();

		clienteJpa.nomeCliente = objCriarCliente.nomeCliente();
		clienteJpa.enderecoCliente = objCriarCliente.enderecoCliente();
		clienteJpa.cepCliente = objCriarCliente.cepCliente();
		clienteJpa.cidadeCliente = objCriarCliente.cidadeCliente();
		clienteJpa.estadoCliente = objCriarCliente.estadoCliente();

		ClienteJpa optJpaSaved = repository.save(clienteJpa);

		return new ClienteRecords(optJpaSaved.idCliente, optJpaSaved.nomeCliente, optJpaSaved.enderecoCliente,
				optJpaSaved.cepCliente, optJpaSaved.cidadeCliente, optJpaSaved.estadoCliente);
	}

	@Override
	public void DeletarCliente(Integer idCliente) {
		repository.deleteById(idCliente);

	}

}

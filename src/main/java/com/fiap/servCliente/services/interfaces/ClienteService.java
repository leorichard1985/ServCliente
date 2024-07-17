package com.fiap.servCliente.services.interfaces;

import java.util.List;

import com.fiap.servCliente.records.ClienteRecords;

public interface ClienteService {

	List<ClienteRecords> ListarClientes();

	ClienteRecords BuscarClientePorId(Integer idCliente);

	ClienteRecords CriarCliente(ClienteRecords objCriarCliente);

	ClienteRecords AtualizarCliente(Integer idCliente, ClienteRecords objAtualizarCliente);

	void DeletarCliente(Integer idCliente);
	
}

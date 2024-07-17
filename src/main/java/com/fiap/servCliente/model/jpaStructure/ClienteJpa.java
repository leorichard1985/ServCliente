package com.fiap.servCliente.model.jpaStructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_CAD_Cliente")
public class ClienteJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	public Integer idCliente;

	@Column(name = "nomeCliente")
	public String nomeCliente;

	@Column(name = "enderecoCliente")
	public String enderecoCliente;
	
	@Column(name = "cepCliente")
	public String cepCliente;
	
	@Column(name = "cidadeCliente")
	public String cidadeCliente;
	

	@Column(name = "estadoCliente")
	public String estadoCliente;
	
}

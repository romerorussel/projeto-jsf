package com.algaworks.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.erp.interfaces.Transacional;
import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.repository.Empresas;

public class CadastroEmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Transacional
	public void salvar(Empresa empresa) {
		empresas.save(empresa);
	}

	@Transacional
	public void excluir(Empresa empresa) {
		empresas.remove(empresa);
	}
	
}

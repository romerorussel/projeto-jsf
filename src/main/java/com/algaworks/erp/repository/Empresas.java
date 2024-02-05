package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.erp.interfaces.CrudEntity;
import com.algaworks.erp.interfaces.Transacional;
import com.algaworks.erp.model.Empresa;

public class Empresas implements Serializable, CrudEntity<Empresa> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;
	
	public Empresas() {}
	
	public Empresas(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Empresa getById(Long id) {
		return this.entityManager.find(Empresa.class, id);
	}
	
	@Override
	public List<Empresa> getFilteredList(String param){
		String jpql = "from Empresa where nomeFantasia like :nomeFantasia";
		TypedQuery<Empresa> typedQuery = this.entityManager.createQuery(jpql, Empresa.class);
		typedQuery.setParameter("nomeFantasia", param + "%");
		return typedQuery.getResultList();
	}
	
	@Override
	public List<Empresa> getAll(){
		return this.entityManager.createQuery("from Empresa", Empresa.class).getResultList();
	}
	
	@Override
	@Transacional
	public Empresa save(Empresa empresa) {
		return this.entityManager.merge(empresa);
	}
	
	@Override
	@Transacional
	public void remove(Empresa empresa) {
		empresa = getById(empresa.getId());
		this.entityManager.remove(empresa);
	}
}

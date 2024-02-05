package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.algaworks.erp.interfaces.CrudEntity;
import com.algaworks.erp.model.RamoAtividade;

public class RamoAtividades implements Serializable, CrudEntity<RamoAtividade> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager entityManager;
	
	public RamoAtividades() {}
	
	public RamoAtividades(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public RamoAtividade getById(Long id) {
		return this.entityManager.find(RamoAtividade.class, id);
	}

	@Override
	public List<RamoAtividade> getFilteredList(String param) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);
		
		Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);
		
		criteriaQuery.select(root);
		
		criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), param + "%"));
		
		TypedQuery<RamoAtividade> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	@Override
	public List<RamoAtividade> getAll() {
		return this.entityManager.createQuery("from RamoAtividade", RamoAtividade.class).getResultList();
	}

	@Override
	public RamoAtividade save(RamoAtividade entity) {
		return this.entityManager.merge(entity);
	}

	@Override
	public void remove(RamoAtividade entity) {
		entity = getById(entity.getId());
		this.entityManager.remove(entity);
	}

}

package com.algaworks.erp.interfaces;

import java.util.List;

public interface CrudEntity<T> {
	
	public T getById(Long id);
	
	public List<T> getFilteredList(String param);
	
	public List<T> getAll();
	
	public T save(T entity);
	
	public void remove(T entity);

}

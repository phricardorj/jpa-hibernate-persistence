package br.com.phricardo.store.dao;

import javax.persistence.EntityManager;

import br.com.phricardo.store.models.Category;

public class CategoryDao {
	
	private EntityManager em;

	public CategoryDao(EntityManager em) {
		this.em = em;
	}
	
	public void register(Category category) {
		this.em.persist(category);
	}
	
	public void update(Category category) {
		this.em.merge(category);
	}
	
	public void delete(Category category) {
		category = em.merge(category);
		this.em.remove(category);
	}

}

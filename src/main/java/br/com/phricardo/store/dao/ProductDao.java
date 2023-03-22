package br.com.phricardo.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.phricardo.store.models.Product;

public class ProductDao {

	private EntityManager em;

	public ProductDao(EntityManager em) {
		this.em = em;
	}

	public void register(Product product) {
		this.em.persist(product);
	}

	public void update(Product product) {
		this.em.merge(product);
	}

	public void delete(Product product) {
		product = em.merge(product);
		this.em.remove(product);
	}
	
	public Product findById(Long id) {
		return em.find(Product.class, id);
	}
	
	public List<Product> findAll() {
		String jpql = "SELECT p FROM Product p";
		return em.createQuery(jpql, Product.class).getResultList();
	}
	
	public List<Product> findByName(String name) {
		String jpql = "SELECT p FROM Product p WHERE p.name = :name";
		return em.createQuery(jpql, Product.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	public List<Product> findByCategoryName(String name) {
		String jpql = "SELECT p FROM Product p WHERE p.category.name = :name";
		return em.createQuery(jpql, Product.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	public BigDecimal findProductPriceWithName(String name) {
		String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("name", name)
				.getSingleResult();
	}

}

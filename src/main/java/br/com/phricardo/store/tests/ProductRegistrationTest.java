package br.com.phricardo.store.tests;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.phricardo.store.dao.CategoryDao;
import br.com.phricardo.store.dao.ProductDao;
import br.com.phricardo.store.models.Category;
import br.com.phricardo.store.models.Product;
import br.com.phricardo.store.util.JPAUtil;

public class ProductRegistrationTest {

	public static void main(String[] args) {
		Category smartphone = new Category("SMARTPHONES");
		Product redmi = new Product("Xiaomi Redmi", "text here", new BigDecimal("800"), smartphone);

		EntityManager em = JPAUtil.getEntityManager();
		ProductDao productDao = new ProductDao(em);
		CategoryDao categoryDao = new CategoryDao(em);

		em.getTransaction().begin();

		categoryDao.register(smartphone);
		productDao.register(redmi);

		em.getTransaction().commit();
		em.close();
	}
}

package br.com.phricardo.store.tests;

import br.com.phricardo.store.dao.CategoryDao;
import br.com.phricardo.store.dao.ProductDao;
import br.com.phricardo.store.models.Category;
import br.com.phricardo.store.models.Product;
import br.com.phricardo.store.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductFindTest {
    public static void main(String[] args) {
        productRegister();

        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(em);

        Product p = productDao.findById(1l);
        System.out.println(p.getPrice());

        List<Product> todos = productDao.findByCategoryName("SMARTPHONES");
        todos.forEach(p2 -> System.out.println(p.getName()));

        BigDecimal precoDoProduto = productDao.findProductPriceWithName("Xiaomi Redmi");
        System.out.println("Preco do Produto: " + precoDoProduto);
    }

    private static void productRegister() {
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

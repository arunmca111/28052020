package com.aadp.vend.ws.io.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadp.vend.ws.io.entity.Products;

@Repository
@Transactional
public class ProductsRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Products findProductsById(String productId) {
		Products product = em.find(Products.class, productId);
		logger.info("Products -> {}", product);
		return product;
	}

	public List<Products> findAllProducts() {
		return em.createQuery("select p from Products p", Products.class).getResultList();
	}

	public Products addProducts(Products products) {

		logger.info("adding Products");
		
			em.persist(products);
			em.flush();

			logger.info("Products Added -> {}", products.getTitle());
			
			return products;
			
	}
	
	public Products updateProducts(Products products) {

		logger.info("adding Products");
		
			em.merge(products);
			em.flush();

			logger.info("Products Updated -> {}", products.getTitle());
			
			return products;
			
	}
	
	public void deleteProduct(Products products) {

		logger.info("deleting Products");
		
			em.remove(products);
			em.flush();

			logger.info("Products Deleted -> {}", products.getTitle());
			
			
	}
	

}
package com.aadp.vend.ws.io.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadp.vend.ws.io.entity.MachineInventory;
import com.aadp.vend.ws.io.entity.Products;
import com.aadp.vend.ws.io.entity.ProductsFavorite;
import com.aadp.vend.ws.shared.dto.ProductsDto;

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

	public List<Object[]> findProductsBymachineId(String machineId) {

		logger.info("MachineId -> {}", machineId);
		
		/*
		 * TypedQuery<ProductsDto> query = em.createQuery(
		 * "select m.machine_slot_id, p.* from machine_inventory m JOIN products p ON "
		 * + "m.product_id =p.product_id WHERE " + "m.machine_code = :machineId and  " +
		 * "m.machine_slot_active_status = true and  " + "m.machine_item_count > 0  " +
		 * "order by m.machine_slot_id", ProductsDto.class); return
		 * query.setParameter("machineId", machineId).getResultList();
		 */
		
		List<Object[]> results = em.createQuery(
				"select m.machineSlotId,p.productId,p.title,p.description,p.price,p.imageUrl,p.isFavorite from MachineInventory m JOIN Products p ON "
						+ "m.productId =p.productId "
				).getResultList();
		
		return results;

	}

	public List<Products> findAllProducts() {
		return em.createQuery("select p from Products p", Products.class).getResultList();
	}

	public List<Products> getProductsByuserID(String userId) {
		TypedQuery<Products> query = em.createQuery("SELECT p FROM Products p WHERE p.userId = :userid",
				Products.class);
		return query.setParameter("userid", userId).getResultList();
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

	public boolean addFavorite(ProductsFavorite prodFav) {

		logger.info("add Favorite");

		em.persist(prodFav);
		em.flush();

		logger.info("Products Favorite -> {}", prodFav.getProductId());

		return true;

	}

	public ProductsFavorite findProductsById(String productId, String userId) {
		ProductsFavorite product = em.find(ProductsFavorite.class, productId);
		logger.info("Products -> {}", product);
		return product;
	}
	
	public MachineInventory getProductsAvailability(String machineCode, String machineSlotId, String productId, int itemCount) {
		TypedQuery<MachineInventory> query = em.createQuery("SELECT M FROM MachineInventory M WHERE M.machineCode = :machineCode and M.machineSlotId = :machineSlotId and M.productId =:productId and M.machineItemCount >= :itemCount",
				MachineInventory.class);
		 query.setParameter("machineCode", machineCode);
		 query.setParameter("machineSlotId", machineSlotId);
		 query.setParameter("productId", productId);
		 return query.setParameter("itemCount", itemCount).getResultList().stream().findFirst().orElse(null);
	}

}
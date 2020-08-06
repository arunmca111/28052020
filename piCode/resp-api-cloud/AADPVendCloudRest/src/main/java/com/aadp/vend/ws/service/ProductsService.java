package com.aadp.vend.ws.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.aadp.vend.ws.shared.dto.FavoriteDto;
import com.aadp.vend.ws.shared.dto.ProductsDto;

public interface ProductsService  {

	ProductsDto createProducts(ProductsDto products) throws Exception;
	List<ProductsDto> getProducts(String machineId) throws Exception;
	List<ProductsDto> getAllProducts() throws Exception;
	List<ProductsDto> getProductsByuserID(String userId) throws Exception;
	ProductsDto updateProduct(String productId, ProductsDto products) throws Exception;
	void deleteUser(String productId) throws Exception;
	boolean addFavorite(FavoriteDto favoriteDto) throws Exception;
	boolean getProductsAvailability(String machinecode, String machineSlotId, String productId, int itemCount) throws Exception;
	
	


}

package com.aadp.vend.ws.service;

import java.util.List;

import com.aadp.vend.ws.shared.dto.FavoriteDto;
import com.aadp.vend.ws.shared.dto.ProductsDto;

public interface ProductsService  {

	ProductsDto createProducts(ProductsDto products) throws Exception;
	ProductsDto getProducts(String productId) throws Exception;
	List<ProductsDto> getAllProducts() throws Exception;
	List<ProductsDto> getProductsByuserID(String userId) throws Exception;
	ProductsDto updateProduct(String productId, ProductsDto products) throws Exception;
	void deleteUser(String productId) throws Exception;
	boolean addFavorite(FavoriteDto favoriteDto) throws Exception;
	
	


}

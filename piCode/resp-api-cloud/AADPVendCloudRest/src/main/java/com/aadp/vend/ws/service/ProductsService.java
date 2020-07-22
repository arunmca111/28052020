package com.aadp.vend.ws.service;

import java.util.List;

import com.aadp.vend.ws.shared.dto.ProductsDto;
import com.aadp.vend.ws.shared.dto.UserDto;

public interface ProductsService  {

	ProductsDto getProducts(String productId) throws Exception;
	List<ProductsDto> getAllProducts() throws Exception;
	ProductsDto updateProduct(String productId, ProductsDto products) throws Exception;
	void deleteUser(String productId) throws Exception;
	
	ProductsDto createProducts(ProductsDto products) throws Exception;


}

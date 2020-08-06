package com.aadp.vend.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aadp.vend.ws.io.entity.Products;
import com.aadp.vend.ws.io.repository.ProductsRepository;
import com.aadp.vend.ws.service.ProductsService;
import com.aadp.vend.ws.shared.Utils;
import com.aadp.vend.ws.shared.dto.FavoriteDto;
import com.aadp.vend.ws.shared.dto.ProductsDto;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	Utils utils;

	@Override
	public List<ProductsDto> getProducts(String machineId) throws Exception {
		List<ProductsDto> returnValue = new ArrayList<>();
		ProductsDto storeDto = null;
		List<Object[]> results = productsRepository.findProductsBymachineId(machineId);
		
		for (Object[] product : results) {
			storeDto = new ProductsDto();
			storeDto.setMachineslotId((String) product[0]);
			storeDto.setProductId((String) product[1]);
			storeDto.setTitle((String) product[2]);
			storeDto.setDescription((String) product[3]);
			storeDto.setPrice((double) product[4]);
			storeDto.setImageUrl((String) product[5]);
			storeDto.setFavorite((boolean) product[6]);
			returnValue.add(storeDto);
			
		}

		return returnValue;

	}
	
	@Override
	public List<ProductsDto> getAllProducts() throws Exception {
		List<ProductsDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		Iterable<Products> products = productsRepository.findAllProducts();

		for (Products product : products) {
			returnValue.add(modelMapper.map(product, ProductsDto.class));
		}

		return returnValue;
	}
	
	@Override
	public List<ProductsDto> getProductsByuserID(String userId) throws Exception {
		List<ProductsDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		Iterable<Products> products = productsRepository.getProductsByuserID(userId);

		for (Products product : products) {
			returnValue.add(modelMapper.map(product, ProductsDto.class));
		}

		return returnValue;
	}

	@Override
	public ProductsDto createProducts(ProductsDto products) throws Exception {
		if (productsRepository.findProductsById(products.getProductId()) != null)
			throw new Exception("Product already exists");

		ModelMapper modelMapper = new ModelMapper();
		Products productsEntity = modelMapper.map(products, Products.class);

		String publicProductId = utils.generateProductId(30);
		productsEntity.setId(publicProductId);

		Products storedUserDetails = productsRepository.addProducts(productsEntity);

		ProductsDto returnValue = modelMapper.map(storedUserDetails, ProductsDto.class);

		return returnValue;

	}

	@Override
	public ProductsDto updateProduct(String productId, ProductsDto products) throws Exception {

		Products productsEntity = productsRepository.findProductsById(productId);

		if (productsEntity == null) {
			throw new Exception(productId);
		}
		if (products.getDescription() != null) {
			productsEntity.setDescription(products.getDescription());
		}
		if (products.getImageUrl() != null) {
			productsEntity.setImageUrl(products.getImageUrl());
		}
		if (products.getPrice() != 0) {
			productsEntity.setPrice(products.getPrice());
		}

		if (products.getTitle() != null) {
			productsEntity.setTitle(products.getTitle());
		}

		productsEntity.setFavorite(products.isFavorite());

		productsRepository.updateProducts(productsEntity);

		ModelMapper modelMapper = new ModelMapper();
		ProductsDto returnValue = modelMapper.map(productsEntity, ProductsDto.class);

		return returnValue;
	}

	@Override
	public void deleteUser(String productId) throws Exception {
		Products productsEntity = productsRepository.findProductsById(productId);

		if (productsEntity == null)
			throw new Exception(productId);

		productsRepository.deleteProduct(productsEntity);

	}

	@Override
	public boolean addFavorite(FavoriteDto favoriteDto) throws Exception {
		

		return true;
	}

	@Override
	public boolean getProductsAvailability(String machinecode, String machineSlotId, String productId, int itemCount) throws Exception {
		boolean result = productsRepository.getProductsAvailability(machinecode, machineSlotId, productId, itemCount) !=null ? true : false ;
		return result;
	}

}

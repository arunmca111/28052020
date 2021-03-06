package com.aadp.vend.ws.ui.contoller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aadp.vend.ws.service.ProductsService;
import com.aadp.vend.ws.shared.dto.ProductsDto;
import com.aadp.vend.ws.ui.model.request.FavoriteRequestModel;
import com.aadp.vend.ws.ui.model.request.ProductDetailRequestModel;
import com.aadp.vend.ws.ui.model.response.OperationStatusModel;
import com.aadp.vend.ws.ui.model.response.ProductAvailabilityResponse;
import com.aadp.vend.ws.ui.model.response.ProductsResponse;
import com.aadp.vend.ws.ui.model.response.RequestOperationStatus;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductsService productsService;

	@GetMapping(path = "/{machineId}")
	public List<ProductsResponse> getProductsForMachine(@PathVariable String machineId) {
		List<ProductsResponse> returnValue = new ArrayList<ProductsResponse>();
		List<ProductsDto> productsDto = null;
		ModelMapper modelMapper = new ModelMapper();

		try {
			productsDto = productsService.getProducts(machineId);

			for (ProductsDto productDto : productsDto) {
				ProductsResponse productModel = new ProductsResponse();
				modelMapper.map(productDto, productModel);
				returnValue.add(productModel);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}

	@PatchMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ProductsResponse updateUser(@PathVariable String id, @RequestBody ProductDetailRequestModel productDetails) {
		ModelMapper modelMapper = new ModelMapper();
		ProductsResponse returnValue = new ProductsResponse();
		ProductsDto productsDto = modelMapper.map(productDetails, ProductsDto.class);
		ProductsDto updateProductDto = null;

		try {
			updateProductDto = productsService.updateProduct(id, productsDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnValue = new ModelMapper().map(updateProductDto, ProductsResponse.class);
		return returnValue;
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteProduct(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationStatus.ERROR.name());

		try {
			productsService.deleteUser(id);
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
	}

	@GetMapping()
	public List<ProductsResponse> getAllProducts() {

		List<ProductsDto> productsDto = null;
		List<ProductsResponse> returnValue = new ArrayList<ProductsResponse>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			productsDto = productsService.getAllProducts();

			for (ProductsDto productDto : productsDto) {
				ProductsResponse productModel = new ProductsResponse();
				modelMapper.map(productDto, productModel);
				returnValue.add(productModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}

	@GetMapping(path = "/{userId}/userProducts", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public List<ProductsResponse> getProductsByuserID(@PathVariable String userId) {

		List<ProductsDto> productsDto = null;
		List<ProductsResponse> returnValue = new ArrayList<ProductsResponse>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			productsDto = productsService.getProductsByuserID(userId);

			for (ProductsDto productDto : productsDto) {
				ProductsResponse productModel = new ProductsResponse();
				modelMapper.map(productDto, productModel);
				returnValue.add(productModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}

	@PostMapping
	@RequestMapping("/product")
	public ProductsResponse createProduct(@RequestBody ProductDetailRequestModel productDetails) {
		ProductsResponse returnValue = new ProductsResponse();
		ProductsDto createdProduct = null;
		ModelMapper modelMapper = new ModelMapper();
		ProductsDto productsDto = modelMapper.map(productDetails, ProductsDto.class);

		try {
			createdProduct = productsService.createProducts(productsDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnValue = modelMapper.map(createdProduct, ProductsResponse.class);

		return returnValue;
	}

	@PatchMapping
	public String updateProductByPut() {
		return "update patch Product called";
	}

	@PutMapping
	public String updateProduct() {
		return "update Product called";
	}

	@PostMapping
	@RequestMapping("/favorite")
	public boolean addFavorite(@RequestBody FavoriteRequestModel favDetail) {

		return true;

	}

	@GetMapping(path = "machinecode/{machinecode}/machineSlotId/{machineSlotId}/productId/{productId}/itemCount/{itemCount}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public ProductAvailabilityResponse getProductsAvailability(@PathVariable String machinecode, @PathVariable String machineSlotId, @PathVariable String productId, @PathVariable int itemCount) {

		boolean returnValue = false;
		ProductAvailabilityResponse response = new ProductAvailabilityResponse();
	

		try {
			returnValue = productsService.getProductsAvailability(machinecode, machineSlotId, productId, itemCount);
			response.setResult(returnValue);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}
}

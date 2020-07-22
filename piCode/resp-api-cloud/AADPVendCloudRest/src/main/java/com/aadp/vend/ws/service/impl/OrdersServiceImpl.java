package com.aadp.vend.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aadp.vend.ws.io.entity.CartItems;
import com.aadp.vend.ws.io.entity.Orders;
import com.aadp.vend.ws.io.repository.OrdersRepository;
import com.aadp.vend.ws.service.OrdersService;
import com.aadp.vend.ws.shared.Utils;
import com.aadp.vend.ws.shared.dto.OrdersDto;
import com.aadp.vend.ws.shared.dto.ProductsDto;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	Utils utils;

	@Override
	public OrdersDto createOrders(OrdersDto orders) throws Exception {
		if (ordersRepository.findOrdersById(orders.getOrdersId()) != null)
			throw new Exception("Orders already exists");

		ModelMapper modelMapper = new ModelMapper();
		Orders ordersEntity = modelMapper.map(orders, Orders.class);

		String publicOrderId = utils.generateOrderId(5);
		ordersEntity.setOrdersId(publicOrderId);

		List<CartItems> bufferCartItems = new ArrayList<CartItems>();

		for (CartItems cartItem : ordersEntity.getCartItems()) {

			// setting the relationship
			String publicCartId = utils.generateCartItemId(10);
			cartItem.setCartId(publicCartId);
			cartItem.setOrder(ordersEntity);
			bufferCartItems.add(cartItem);
		}

		ordersEntity.setCartItems(bufferCartItems);

		Orders storedOrdersDetails = ordersRepository.addOrders(ordersEntity);

		OrdersDto returnValue = modelMapper.map(storedOrdersDetails, OrdersDto.class);

		return returnValue;

	}

	@Override
	public List<OrdersDto> fetchAllOrders() throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		List<OrdersDto> returnValue = new ArrayList<>();
		List<Orders> storedOrdersDetails = ordersRepository.fetchAllOrders();

		Type listType = new TypeToken<List<OrdersDto>>() {
		}.getType();
		returnValue = modelMapper.map(storedOrdersDetails, listType);

		return returnValue;
	}

}

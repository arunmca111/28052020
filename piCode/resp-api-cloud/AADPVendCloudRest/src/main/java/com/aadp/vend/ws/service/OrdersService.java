package com.aadp.vend.ws.service;

import java.util.List;

import com.aadp.vend.ws.shared.dto.OrdersDto;

public interface OrdersService  {

	OrdersDto createOrders(OrdersDto orders) throws Exception;
	
	List<OrdersDto> fetchAllOrders() throws Exception;

}

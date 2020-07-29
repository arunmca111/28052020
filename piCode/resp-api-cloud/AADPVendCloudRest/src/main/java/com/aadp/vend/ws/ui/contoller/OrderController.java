package com.aadp.vend.ws.ui.contoller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aadp.vend.ws.service.OrdersService;
import com.aadp.vend.ws.shared.dto.OrdersDto;
import com.aadp.vend.ws.ui.model.request.OrderDetailRequestModel;
import com.aadp.vend.ws.ui.model.response.OrdersFetchResponse;
import com.aadp.vend.ws.ui.model.response.OrdersResponse;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrdersService ordersService;

	@PostMapping
	@RequestMapping("/order")
	public OrdersResponse createOrder(@RequestBody OrderDetailRequestModel orderDetails) {
		OrdersResponse returnValue = new OrdersResponse();
		OrdersDto createdOrder = null;
		ModelMapper modelMapper = new ModelMapper();
		OrdersDto ordersDto = modelMapper.map(orderDetails, OrdersDto.class);

		try {
			createdOrder = ordersService.createOrders(ordersDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnValue = modelMapper.map(createdOrder, OrdersResponse.class);

		return returnValue;
	}
	
	@GetMapping(path = "/{userId}")
	public List<OrdersFetchResponse> getAllOrders(@PathVariable String userId) {

		List<OrdersDto> ordersDto = null;
		List<OrdersFetchResponse> returnValue = new ArrayList<OrdersFetchResponse>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			ordersDto = ordersService.fetchAllOrders(userId);

			for (OrdersDto orderDto : ordersDto) {
				OrdersFetchResponse ordersFetchModel = new OrdersFetchResponse();
				modelMapper.map(orderDto, ordersFetchModel);
				returnValue.add(ordersFetchModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}

}

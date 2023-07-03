package com.app.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
public class OrderCreation {
   @PostMapping("/createOrder")
   @ResponseBody
   public String createOrder(@RequestParam("amount") int amount) {
	   Order order=null;
	  try {
		RazorpayClient razorpay=new  RazorpayClient("rzp_test_3QKgWhJ8NxlMt1"+ "", "ykn9r6EdB84nxqEai5rREazr"+ "");
		try {
			  JSONObject orderRequest = new JSONObject();
			  orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			  orderRequest.put("currency", "INR");
			  orderRequest.put("receipt", "order_rcptid_11");

			 order = razorpay.orders.create(orderRequest);
			 System.out.println(order);
			} catch (RazorpayException e) {
			  // Handle Exception
			  System.out.println(e.getMessage());
			}
	} catch (RazorpayException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  finally {
		  return order.toString();
	  }
	  }
}
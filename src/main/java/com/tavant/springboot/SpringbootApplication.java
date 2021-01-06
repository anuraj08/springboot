package com.tavant.springboot;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.tavant.springboot.service.CustomersService;
import com.tavant.springboot.service.EmployeeService;
import com.tavant.springboot.service.OfficeService;
import com.tavant.springboot.service.OrderDetailsService;
import com.tavant.springboot.service.OrdersService;
import com.tavant.springboot.service.PaymentsService;
import com.tavant.springboot.service.ProductLinesService;
import com.tavant.springboot.service.ProductsService;
import com.tavant.springboot.utils.DBUtils;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
		
		DBUtils dbUtils = context.getBean(DBUtils.class);
		Connection connection = dbUtils.getConnection();
		// We will get true if connection is established
		System.out.println(connection!=null);
		
		dbUtils.closeConnection(connection);
		
//		EmployeeService employeeService = context.getBean(EmployeeService.class);
//		System.out.println(employeeService!=null);
//		
//		CustomersService customersService = context.getBean(CustomersService.class);
//		System.out.println(customersService!=null);
//		
//		OfficeService officeService = context.getBean(OfficeService.class);
//		System.out.println(officeService!=null);
//		
//		OrderDetailsService orderDetailsService = context.getBean(OrderDetailsService.class);
//		System.out.println(orderDetailsService!=null);
//		
//		OrdersService ordersService = context.getBean(OrdersService.class);
//		System.out.println(ordersService!=null);
//		
//		PaymentsService paymentsService = context.getBean(PaymentsService.class);
//		System.out.println(paymentsService!=null);
//		
//		ProductLinesService productLinesService = context.getBean(ProductLinesService.class);
//		System.out.println(productLinesService!=null);
//		
//		ProductsService productsService = context.getBean(ProductsService.class);
//		System.out.println(productsService!=null);
	}

}

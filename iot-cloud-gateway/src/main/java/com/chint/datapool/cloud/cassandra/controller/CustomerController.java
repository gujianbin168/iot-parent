package com.chint.datapool.cloud.cassandra.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chint.datapool.cloud.cassandra.dao.CustomerDao;
import com.chint.datapool.cloud.cassandra.model.Customer;
import com.chint.datapool.cloud.cassandra.service.CustomerService;
import com.datastax.driver.core.utils.UUIDs;


 
@RestController
public class CustomerController {
	@Autowired
	private CustomerDao repository;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/save")
	public String save() {
		Customer customer = new Customer(UUIDs.timeBased(), "Alice", "Smith");
		customerService.save(customer);
		System.out.println("Customers found with findByLastName():");
		System.out.println("-------------------------------");
		for (Customer customer1 : customerService.findByLastName("Smith")) {
			System.out.println(customer1);
		}
		return "save success";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		UUID uuid = UUIDs.timeBased();
		customerService.delete(uuid);
		return "delete success";
	}

	@RequestMapping("/update")
	public String update() {
		List<Customer> customers = customerService.findByLastName("Smith");
		for (Customer customer : customers) {
			customer.setLastName("Smith1");
			customerService.update(customer);
			System.out.println(customer);
		}
		return "update success";
	}
 
}

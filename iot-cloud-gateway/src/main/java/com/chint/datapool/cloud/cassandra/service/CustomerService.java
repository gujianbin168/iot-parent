package com.chint.datapool.cloud.cassandra.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chint.datapool.cloud.cassandra.dao.CustomerDao;
import com.chint.datapool.cloud.cassandra.model.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Transactional
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	public List<Customer> findByLastName(String lastName) {
		return customerDao.findByLastName(lastName);
	}

	@Transactional
	public void delete(UUID id) {
		customerDao.deleteByID(id);
	}

	public Iterable<Customer> getAll() {
		return customerDao.findAll();
	}

	@Transactional
	public void update(Customer customer) {
		customerDao.save(customer);
	}
}

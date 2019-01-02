package com.chint.datapool.cloud.cassandra.dao;

import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.chint.datapool.cloud.cassandra.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, String> {

	@Query("Select * from customer where firstName=?0")
	Customer findByFirstName(String firstName);

	@Query("Select * from customer where lastName=?0")
	List<Customer> findByLastName(String lastName);
	
	@Query("Delete from customer where id=?0")
	void deleteByID(UUID id);
	
	@Query("Select * from customer where id=?0")
	List<Customer> findByID(UUID id);

}

//package com.chint.datapool.cloud.cassandra.dao;
//
//import java.util.List;
//import java.util.UUID;
//import org.springframework.data.cassandra.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import com.chint.datapool.cloud.cassandra.model.PressureGage;
// 
//
//public interface PressureGageDao extends CrudRepository<PressureGage, String> {
//
//	@Query("Select * from PressureGage where companyName=?0")
//	List<PressureGage> findByCompanyName(String companyName);
//
//	@Query("Select * from PressureGage where deviceName=?0")
//	List<PressureGage> findByDeviceName(String deviceName);
//	
//	@Query("Delete from PressureGage where id=?0")
//	void deleteByID(UUID id);
//	
//	@Query("Select * from PressureGage where id=?0")
//	PressureGage findByID(UUID id);
//
//	//registerTime
//}

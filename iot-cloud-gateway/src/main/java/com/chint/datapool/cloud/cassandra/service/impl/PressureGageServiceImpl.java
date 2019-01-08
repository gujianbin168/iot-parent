//package com.chint.datapool.cloud.cassandra.service.impl;
//
//import java.util.List;
//import java.util.UUID;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.chint.datapool.cloud.cassandra.dao.PressureGageDao;
//import com.chint.datapool.cloud.cassandra.model.Device;
//import com.chint.datapool.cloud.cassandra.model.PressureGage;
//import com.chint.datapool.cloud.cassandra.service.PressureGageService;
//import com.chint.datapool.cloud.cassandra.util.Constants;
//
//@Service
//public class PressureGageServiceImpl implements PressureGageService {
//	@Autowired
//	private PressureGageDao pressureGageDao;
//
//	@Transactional
//	public void save(PressureGage pressureGage) {
//		System.out.println("-------------------------------");
//		pressureGageDao.save(pressureGage);
//	}
//
//	public List<PressureGage> findByDeviceName(String deviceName) {
//		return pressureGageDao.findByDeviceName(deviceName);
//	}
//	
//	public List<PressureGage> findByCompanyName(String deviceName) {
//		return pressureGageDao.findByCompanyName(deviceName);
//	}
//
//	@Transactional
//	public void deleteByID(UUID id) {
//		pressureGageDao.deleteByID(id);
//	}
//
//	public Iterable<PressureGage> getAll() {
//		return pressureGageDao.findAll();
//	}
//	
//	public PressureGage findByID(UUID id) {
//		return pressureGageDao.findByID(id);
//	}
//
//	@Transactional
//	public void update(PressureGage pressureGage) {
//		pressureGageDao.save(pressureGage);
//	}
//}

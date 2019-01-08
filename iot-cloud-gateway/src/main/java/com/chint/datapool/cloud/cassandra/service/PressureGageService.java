package com.chint.datapool.cloud.cassandra.service;

import java.util.List;
import java.util.UUID;
import com.chint.datapool.cloud.cassandra.model.PressureGage;

public interface PressureGageService {

	public void save(PressureGage pressureGage);

	public List<PressureGage> findByDeviceName(String lastName);
	
	public List<PressureGage> findByCompanyName(String lastName);

	public void deleteByID(UUID id);

	public Iterable<PressureGage> getAll();

	public PressureGage findByID(UUID id);

	public void update(PressureGage pressureGage);
}

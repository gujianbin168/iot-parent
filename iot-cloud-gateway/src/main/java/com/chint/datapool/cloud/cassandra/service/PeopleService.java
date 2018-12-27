package com.chint.datapool.cloud.cassandra.service;

 

import java.util.List;
import com.chint.datapool.cloud.cassandra.model.People;

 
public interface PeopleService {

    void save(People people);

    List<People> findall();

    void delete();

}
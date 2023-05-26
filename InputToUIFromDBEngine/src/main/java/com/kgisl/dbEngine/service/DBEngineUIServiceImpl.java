package com.kgisl.dbEngine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgisl.dbEngine.src.dao.DBEngineUIRepoImpl;

@Service
public class DBEngineUIServiceImpl implements DBEngineUIService {
	
	@Autowired
	DBEngineUIRepoImpl dbEngineUIRepoImpl;

	public Map<String, List<String>> getSourceForUI() {
		
		try {
			return dbEngineUIRepoImpl.getTables();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

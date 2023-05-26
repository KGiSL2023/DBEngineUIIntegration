package com.kgisl.dbEngine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.dbEngine.service.DBEngineUIService;

@RestController
@RequestMapping(path = "/")
public class DBEngineController {
	
	@Autowired
	DBEngineUIService dbEngineService;
	
	@GetMapping("/getSource")
	public Map<String,List<String>> getSourceForUI(){
		
		Map<String, List<String>> tableColumnMap = dbEngineService.getSourceForUI();
		return tableColumnMap;
		
	}

}

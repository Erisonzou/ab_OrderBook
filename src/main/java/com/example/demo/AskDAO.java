package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AskDAO {
	@Autowired
	AskRepo repo;
	
	public Ask insert(Ask ask) {
		return repo.save(ask); 
		
	}
	

}

package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AskRepo extends JpaRepository<Ask,String>{
	
	@Query("select ask from Ask ask where ask.price<=?1 and ask.stock=?2")
	public List<Ask> findbyprice(Double price, String Stock);
}

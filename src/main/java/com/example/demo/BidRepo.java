package com.example.demo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BidRepo extends JpaRepository<Bid,String>{
	
	@Query("select bid from Bid bid where bid.price>=?1 and bid.stock=?2")
	public List<Bid> findbyprice(Double price, String Stock);
	
}

package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Ask {
	private int quantity;
	@Id
	private double pricePoint; 
	private int userid; 
	private String stock; 
		

}

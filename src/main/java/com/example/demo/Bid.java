package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Bid {
	
	@Id
	private double price;
	private String stock;
	private int quantity;
}

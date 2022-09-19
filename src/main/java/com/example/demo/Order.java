package com.example.demo;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Order {
	
	@Id
	private String userid;
	private String orderType;
	private String Stock;
	private int quantity;
	private double price;
	private String orderStatus;
	private LocalDateTime time;

}

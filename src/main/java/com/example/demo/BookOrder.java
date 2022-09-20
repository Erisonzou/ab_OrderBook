package com.example.demo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class BookOrder {

	private String user;
	private String orderType;
	private String Stock;
	private int Quantity;
	private double Price;
	private String OrderStatus;
	@Id
	private LocalDateTime time;

}

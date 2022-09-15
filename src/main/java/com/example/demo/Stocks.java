package com.example.demo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Stocks {
	
	@Id
	private String Ticker;
	private double latestbid;
	private double latestask;
	private double volume;

}

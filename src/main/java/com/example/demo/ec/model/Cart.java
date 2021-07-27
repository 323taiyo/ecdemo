package com.example.demo.ec.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cart implements Serializable{

	private int id;
	private String name;
	private int price;
	private int quantity;
	private String image;

}

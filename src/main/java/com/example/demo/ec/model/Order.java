package com.example.demo.ec.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Data
public class Order implements Serializable {
	private int id;
	private String address;
	private String date;
}

package com.example.demo.ec.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Data
public class User implements Serializable {

	@Pattern(regexp = "[a-zA-Z0-9]{4,16}", message = "IDは半角英数4~16文字で入力してください")
	private String id;

	@Pattern(regexp = "[a-zA-Z0-9]{4,16}", message = "パスワードは半角英数4~16文字で入力してください")
	private String pass;

	@Size(min = 1, max = 16, message = "名前は1~16文字以内で入力してください")
	private String name;

	@Size(min = 1, max = 255, message = "住所は1~255文字以内で入力してください")
	private String address;

	@Pattern(regexp = "[0-9]{10,11}", message = "電話番号は10~11文字で入力してください")
	private String tel;


}

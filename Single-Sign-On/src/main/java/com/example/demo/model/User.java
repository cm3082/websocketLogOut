package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName(value="user")
@Data
public class User {

	//@TableId(value = "id", type = IdType.AUTO)
	private int id;
	//@TableField("username")
	private String username;
	//@TableField("password")
	private String password;
}

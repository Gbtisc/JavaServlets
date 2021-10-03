package com.register.db;

import java.util.List;
import com.google.gson.Gson;
import com.register.bean.UserBean;

public class DBUtils {
	public static final String CREATEUSER = "INSERT INTO users "
			+ "(firstName, lastName, userName, userPassword, userEmail, userStatus) "
			+ "VALUES (?,?,?,?,?,?)";
	public static final String READALLUSERS = "SELECT * FROM users";
	public static final String READACTIVEUSERS = "SELECT * FROM users WHERE userStatus = 1";
	public static final String READINACTIVEUSERS = "SELECT * FROM users WHERE userStatus = 0";
	public static final String READUSER = "SELECT * FROM users WHERE userId = ?";
	public static final String UPDATEUSER = "UPDATE users SET firstName = ?, "
			+ "lastName = ?, userName = ?, userPassword = ?, userEmail = ?, "
			+ "userStatus = ? WHERE userId = ?";
	public static final String DELETEUSER = "DELETE FROM users WHERE userId = ?";
	
	public String listToJSON(List<UserBean> list) {
		String json = new Gson().toJson(list);
		return json;
	}
}
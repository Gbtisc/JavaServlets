package com.register.Imp;

import java.sql.SQLException;
import java.util.List;
import com.register.bean.UserBean;

public interface UserDAO {
	public boolean createUser(UserBean userBean) throws SQLException;
	public List<UserBean> readAllUsers() throws SQLException;
	public List<UserBean> readActiveUsers() throws SQLException;
	public List<UserBean> readInactiveUsers() throws SQLException;
	public UserBean readUser(int userId) throws SQLException;
	public boolean updateUser(UserBean userBean) throws SQLException;
	public boolean deleteUser(int userId) throws SQLException;
}
package com.register.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.register.bean.UserBean;
import com.register.connection.ConnectionRF;
import com.register.db.DBUtils;

public class UserImp implements UserDAO  {
	private ConnectionRF connectionRF = null;
	private Connection connection = null;
	
	public UserImp() throws SQLException, ClassNotFoundException {
		this.connectionRF = new ConnectionRF();
		this.connection = connectionRF.getConnection();
	}

	@Override
	public boolean createUser(UserBean userBean) throws SQLException {
		boolean result = false;
		try {
			PreparedStatement pstm = connection.prepareStatement(DBUtils.CREATEUSER);
			pstm.setString(1, userBean.getFirstName());
			pstm.setString(2, userBean.getLastName());
			pstm.setString(3, userBean.getUserName());
			pstm.setString(4, userBean.getUserPassword());
			pstm.setString(5, userBean.getUserEmail());
			pstm.setInt(6, userBean.getUserStatus());
			pstm.executeUpdate();
			result = true;
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			connectionRF.close();
		}
		return result;
	}

	@Override
	public List<UserBean> readAllUsers() throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
		
		try {
			PreparedStatement pstm = connection.prepareStatement(DBUtils.READALLUSERS);
			ResultSet resultSet = pstm.executeQuery();
			
			while (resultSet.next()) {
				UserBean userBean = new UserBean();
				userBean.setUserId(resultSet.getInt(1));
				userBean.setFirstName(resultSet.getString(2));
				userBean.setLastName(resultSet.getString(3));
				userBean.setUserName(resultSet.getString(4));
				userBean.setUserPassword(resultSet.getString(5));
				userBean.setUserEmail(resultSet.getString(6));
				userBean.setUserStatus(resultSet.getInt(7));
				list.add(userBean);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			connectionRF.close();
		}
		return list;
	}
	
	@Override
	public List<UserBean> readActiveUsers() throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
		
		try {
			PreparedStatement pstm = connection.prepareStatement(DBUtils.READACTIVEUSERS);
			ResultSet resultSet = pstm.executeQuery();
			
			while (resultSet.next()) {
				UserBean userBean = new UserBean();
				userBean.setUserId(resultSet.getInt(1));
				userBean.setFirstName(resultSet.getString(2));
				userBean.setLastName(resultSet.getString(3));
				userBean.setUserName(resultSet.getString(4));
				userBean.setUserPassword(resultSet.getString(5));
				userBean.setUserEmail(resultSet.getString(6));
				userBean.setUserStatus(resultSet.getInt(7));
				list.add(userBean);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			connectionRF.close();
		}
		return list;
	}
	
	@Override
	public List<UserBean> readInactiveUsers() throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
		
		try {
			PreparedStatement pstm = connection.prepareStatement(DBUtils.READINACTIVEUSERS);
			ResultSet resultSet = pstm.executeQuery();
			
			while (resultSet.next()) {
				UserBean userBean = new UserBean();
				userBean.setUserId(resultSet.getInt(1));
				userBean.setFirstName(resultSet.getString(2));
				userBean.setLastName(resultSet.getString(3));
				userBean.setUserName(resultSet.getString(4));
				userBean.setUserPassword(resultSet.getString(5));
				userBean.setUserEmail(resultSet.getString(6));
				userBean.setUserStatus(resultSet.getInt(7));
				list.add(userBean);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			connectionRF.close();
		}
		return list;
	}

	@Override
	public UserBean readUser(int userId) throws SQLException {
		UserBean userBean = new UserBean();
		try {
			PreparedStatement pstm = connection.prepareStatement(DBUtils.READUSER);
			pstm.setInt(1, userId);
			ResultSet resultSet = pstm.executeQuery();
			
			while (resultSet.next()) {
				userBean.setUserId(resultSet.getInt(1));
				userBean.setFirstName(resultSet.getString(2));
				userBean.setLastName(resultSet.getString(3));
				userBean.setUserName(resultSet.getString(4));
				userBean.setUserPassword(resultSet.getString(5));
				userBean.setUserEmail(resultSet.getString(6));
				userBean.setUserStatus(resultSet.getInt(7));
			}
		} catch(SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			connectionRF.close();
		}
		return userBean;
	}

	@Override
	public boolean updateUser(UserBean userBean) throws SQLException {
		boolean result = false;
		
		try {
			PreparedStatement pstm = connection.prepareStatement(DBUtils.UPDATEUSER);
			pstm.setString(1, userBean.getFirstName());
			pstm.setString(2, userBean.getLastName());
			pstm.setString(3, userBean.getUserName());
			pstm.setString(4, userBean.getUserPassword());
			pstm.setString(5, userBean.getUserEmail());
			pstm.setInt(6, userBean.getUserStatus());
			pstm.setInt(7, userBean.getUserId());
			pstm.executeUpdate();
			result = true;
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			connectionRF.close();
		}
		return result;
	}

	@Override
	public boolean deleteUser(int userId) throws SQLException {
		boolean result = false;
		
		try {
			PreparedStatement pstm = connection.prepareStatement(DBUtils.DELETEUSER);
			pstm.setInt(1, userId);
			pstm.executeUpdate();
			result = true;
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			connectionRF.close();
		}
		return result;
	}
}
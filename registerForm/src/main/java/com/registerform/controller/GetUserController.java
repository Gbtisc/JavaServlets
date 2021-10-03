package com.registerform.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.register.Imp.UserImp;
import com.register.bean.UserBean;
import com.register.db.DBUtils;

public class GetUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GetUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String result = request.getParameter("result");
		DBUtils utils = new DBUtils();
		UserBean userBean = new UserBean();
		
		try {
			UserImp userImp = new UserImp();
			
			if (result.equals("createUser") || result.equals("updateUser")) {
				userBean.setFirstName(request.getParameter("firstName"));
				userBean.setLastName(request.getParameter("lastName"));
				userBean.setUserName(request.getParameter("userName"));
				userBean.setUserPassword(request.getParameter("userPassword"));
				userBean.setUserEmail(request.getParameter("userEmail"));
				userBean.setUserStatus(Integer.parseInt(request.getParameter("userStatus")));
			}
			
			if (result.equals("updateUser") || result.equals("readUser") || 
					result.equals("deleteUser")) {
				userBean.setUserId(Integer.parseInt(request.getParameter("userId")));
			}
			
			switch (result) {
				case "createUser":
					if (userImp.createUser(userBean)) {
						writer.write("CR");
					} else {
						writer.write("F");
					}
					break;
				case "readAllUsers":
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					writer.print(utils.listToJSON(userImp.readAllUsers()));
					writer.flush();
					break;
				case "readActiveUsers":
					response.setCharacterEncoding("UTF-8");
					writer.print(utils.listToJSON(userImp.readActiveUsers()));
					writer.flush();
					break;
				case "readInactiveUsers":
					response.setCharacterEncoding("UTF-8");
					writer.print(utils.listToJSON(userImp.readInactiveUsers()));
					writer.flush();
					break;
				case "updateUser":
					if (userImp.updateUser(userBean)) {
						writer.write("UP");
					} else {
						writer.write("F");
					}
					break;
				case "deleteUser":
					userImp.deleteUser(userBean.getUserId());
					break;
				default:
					System.out.println("Invalid action");
					break;
			}
		} catch (SQLException SQLException) {
			SQLException.printStackTrace();
			writer.write("DB");
		} catch (ClassNotFoundException classNFException) {
			classNFException.printStackTrace();
			writer.write("DR");
		}
	}
}
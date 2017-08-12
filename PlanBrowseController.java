package dbs.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbs.dao.CallDAO;
import dbs.pojo.Calls;

public class PlanBrowseController {
	@SuppressWarnings("deprecation")
	public void getCustomerCallLog(HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			String customerId = request.getParameter("customerid");
			
			List<Calls> calllogs = CallDAO.getCallLogsForCustomer(customerId);
			session.putValue("calls", calllogs);
			
			if(calllogs.isEmpty()) {
				response.sendRedirect("nocalls");
			}else {
				response.sendRedirect("displaycalllogs");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

import java.beans.Customizer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class EMIController {
	public void EMICalculattion(HttpServletRequest request,HttpServletResponse response) {
		float emi;
		HttpSession session=request.getSession();
		String planId=request.getParameter("PlanId");
		Plan plan=cus.fetchPlanDetails(planId);
		if(planId!=null) {
			
		int totalAmount=request.getParameter("TotalAmount");
		float interest=(totalAmount*plan.getRate())/100;
		totalAmount+=interest;
		CustomerDAO cus=new CustomerDAO();
		
		List<Float> payment=new List<Float>();
		List<Float> balance=new List<Float>();
	    float emi=totalAmount/plan.getMonths();
	    for(int i;i<plan.getMonths();i++) {
	    	payment.add(emi);
	    	balance.add(totalAmount-emi);
	    	totalAmount-=emi;
	    }
	    session.putValue("payment", payment);
	    session.putValue("balance", balance);
	    response.sendRedirect("Confirm");
			 
			 
		}
		else {
			response.sendRedirect("Error");
		}
		
				
	}

}

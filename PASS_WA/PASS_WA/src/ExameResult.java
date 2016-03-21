

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ExameResult
 */
@WebServlet("/ExameResult")
public class ExameResult extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
    
    public ExameResult() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		HttpSession s=request.getSession();
		
		HttpSession aSesssion = request.getSession(false);
	 	String email=(String)aSesssion.getAttribute("email");
	 	aSesssion.setAttribute("email",email);
	 
	
		String maintopic=(String) s.getAttribute("maintopic");
	 	String subtopicname=(String) s.getAttribute("subtopicname");
	 	String testname=(String) s.getAttribute("testname");
	 	String type = (String) aSesssion.getAttribute("type");
	 	aSesssion.setAttribute("type",type);
	 	
	 	s.setAttribute("maintopic",maintopic);
		s.setAttribute("subtopicname",subtopicname);
		s.setAttribute("testname",testname);
		
	 	
		int status=Integer.parseInt(request.getParameter("status"));
		
	 	if(status!=0)
	 	{
		
		int q=(Integer) s.getAttribute("q");
		int NofQtions=(int) s.getAttribute("NofQtions");
	    int Min=Integer.parseInt(request.getParameter("clock1"));
		int sec=Integer.parseInt(request.getParameter("clock"));
		String ans=request.getParameter("g"+q);
		String DBans=(String) s.getAttribute("DBans");
		int count=(Integer) s.getAttribute("count");
		int attempt=(Integer) s.getAttribute("attempt");
	
	 	if(ans!=null)
	 	{
	 		s.setAttribute("attempt",++attempt);
			if(ans.equals(DBans))
		 	{
		  		s.setAttribute("count",++count);
				System.out.println(":::::\n count="+count+"\nans="+ans+"\nDBans="+DBans);
		 	}
	 	}
	 	s.setAttribute("Min",Min);
	 	s.setAttribute("sec",sec);
	 	s.setAttribute("q",++q);
	 	s.setAttribute("NofQtions",NofQtions);
	 	if(q>NofQtions)
		{
			RequestDispatcher rd=request.getRequestDispatcher("/ResultDisplay");
		 	rd.forward(request, response);
		 
		 		}
		else
		{
		RequestDispatcher rd=request.getRequestDispatcher("/AttendExamPage");
	 	rd.forward(request, response);
		}
	 }	
   else
   {
	 RequestDispatcher rd=request.getRequestDispatcher("/ResultDisplay");
	 rd.forward(request, response);
	 
		 
    }
   }

	

}

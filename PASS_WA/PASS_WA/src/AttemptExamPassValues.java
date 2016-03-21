

import java.io.IOException;
import java.io.Serializable;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Databasepack.Databaseopp;

import com.mysql.jdbc.ResultSet;

/**
 * Servlet implementation class AttemptExamPassValues
 */
@WebServlet("/AttemptExamPassValues")
public class AttemptExamPassValues extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
	int Min,sec;  
   
	@Override
	public void init() throws ServletException {System.out.println("yes");
		// TODO Auto-generated method stub
		
	}
    public AttemptExamPassValues() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession aSesssion = request.getSession();
		String subtopicname=request.getParameter("subtopicname");
	 	String testname=request.getParameter("testname");
	 	String type = (String) aSesssion.getAttribute("type");
	 	aSesssion.setAttribute("type",type);
	 	String email=(String)aSesssion.getAttribute("email");
	 	aSesssion.setAttribute("email",email);
	 	String maintopic=request.getParameter("maintopic");	
	try
    {
	   int Atmt=0;
		 
	 	Databaseopp d=new Databaseopp();
	 	out.println("<!DOCTYPE html>");
	 	out.println("<html lang=\"en\">");
	 	out.println("<head>");
	 	out.println("<title>Student\'s Site</title>");
	 	out.println("<meta charset=\"utf-8\">");
	 	out.println("<link rel=\"stylesheet\" href=\"css/reset.css\" type=\"text/css\" media=\"all\">");
	 	out.println("<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"all\">");
	 	out.println("<script type=\"text/javascript\" src=\"js/jquery-1.4.2.min.js\" ></script>");
	 	out.println("<script type=\"text/javascript\" src=\"js/cufon-yui.js\"></script>");
	 	out.println("<script type=\"text/javascript\" src=\"js/cufon-replace.js\"></script>");
	 	out.println("<script type=\"text/javascript\" src=\"js/Myriad_Pro_300.font.js\"></script>");
	 	out.println("<script type=\"text/javascript\" src=\"js/Myriad_Pro_400.font.js\"></script>");
	 	out.println("<script type=\"text/javascript\" src=\"js/script.js\"></script>");
	 	out.println("</head>");
	 	out.println("<body id=\"page1\">");
	 	out.println("<div class=\"wrap\">");
	 	out.println("  <header>  <div class=\"container\">");
	 	//out.println("      <h11><marquee> <p >Software and Web Applications Security:State-of-the-Art Courseware and Learning Paradigm</p></marquee></h11>");
	 	out.println("       <nav> <ul>  <li class=\"current\"><a href=\"http://www.examination.com/PASS/Logout\" class=\"m1\">Log Out</a></li> </ul> </nav>  </div>");
	 	out.println("	</header>");
	 	out.println("  <div class=\"container\">");
	 	out.println("    <h3>"+testname+"</h3>");
	  	out.println("    <br><h3>Instructions:</h3>");
	 	out.println("     <h4> <ul class=\"categories\">");
	 	int NoOfqtions=0;
		NoOfqtions=(int) d.ExampapersPath(maintopic,subtopicname,testname,type);
		if(NoOfqtions!=0)
		{
			HttpSession s = request.getSession();
		    Min=NoOfqtions;
		    s.setAttribute("q", 1);
		    s.setAttribute("NofQtions", NoOfqtions);
		    s.setAttribute("Min",2);
		    s.setAttribute("sec",2);
		    s.setAttribute("count", 0);
		    s.setAttribute("attempt", 0);
			s.setAttribute("maintopic",maintopic);
		    s.setAttribute("subtopicname", subtopicname);
		    s.setAttribute("testname", testname);
		   
		    ResultSet check =(ResultSet) d.checkAttempt(email,subtopicname,testname,type);
		 	if(check==null)
		 	{
		 	boolean insertb= d.insertBeforeResult(email,maintopic,NoOfqtions,subtopicname,testname,type);
			 	if(insertb)
			 	{
			 		System.out.println("\n '''before inserted'''\n");
			 	}
			}
		 	else
		 	{
		 		while(check.next())
			 		{
			 			 Atmt=check.getInt(2);
			 		}
			 		
			 	if(Atmt==3)
				{
					out.println("<br><h3>You Can Attend Exam 3 times only</h3> <br>");
				}
				else
				{
					out.println("<form  action='http://www.examination.com/PASS/AttendExamPage' method='get'>");
					
					
					out.println("1).No of Questions are "+NoOfqtions+".<br><br>");
					out.println("2).You have "+NoOfqtions+" minutes for attempt all questions. <br><br>");
				 	out.println("3).You can attempt exam three times only.<br><br> ");
				 	out.println("4).Should Attempt All Questions. <br><br>");
				 	out.println("<center><h3>**All the best**</h3>");
				 	out.println("<center><input type='submit' value='Start Exam'> <br></form>");
					
					
				}
		 	}
			
		} 
		else
			out.println("<br><h3>Exam not Available</h3> <br>");	
	  
	 }
	 catch(Exception e)
	 {
	          
	             e.printStackTrace();                      
	 }
   out.println("	  </ul></h4><h2>Fresh <span>News</span></h2>");
   out.println("      <ul class=\"news\"><li><strong>June 30, 2013</strong>");
   out.println("      <h4>Education</h4> Focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students with updated courseware . </li> </ul> ");
   out.println("  </div>");
   out.println("</div></body>");
   out.println("</html>");
 
 	
	}

	

}

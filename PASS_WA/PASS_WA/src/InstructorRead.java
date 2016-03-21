

import java.io.IOException;
import java.io.Serializable;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Databasepack.Databaseopp;
import Sturecordpack.Sturecord;

/**
 * Servlet implementation class InstructorRead
 */
@WebServlet("/InstructorRead")
public class InstructorRead extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
   
    public InstructorRead() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
	 	PrintWriter out=res.getWriter();
	 	Databaseopp d=new Databaseopp();
	    Sturecord record=d.readInstructerRecord(req);
	    boolean status = false;
		try 
		{
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
	 		//out.println("      <h11><marquee> <p >Welcomes to Graduates</p></marquee></h11>");
	 		out.println("      <nav> <ul>  <li class=\"current\"><a href=\"http://www.examination.com/PASS/Logout\" class=\"m1\">Log Out</a></li> </ul> </nav>  </div>");
	 		out.println("	</header>");
	 		out.println("  <div class=\"container\">");
	 		out.println("    <h3>Main Topics</h3>");
	 		out.println("     <h4> <ul class=\"categories\">");

			status=d.checkInstructorRegister(record);
			if(status)
			{
				out.println("<br>Your Already Registered<br>");
			}
			else
			{
		 	status = d.insertInstructorRecord(record);
			if(status)
			{
		 	res.sendRedirect("http://www.examination.com/PASS/instructerloginpage.html?....SUCCESS...");
			}
		 	else
		 		out.println("<br>Insert not success");
			}
			out.println("	  </ul></h4> <h2>Fresh <span>News</span></h2>");
	 		out.println("      <ul class=\"news\"><li><strong>June 30, 2013</strong>");
	 		out.println("      <h4>Education</h4> Focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students with updated courseware . </li> </ul> ");
	 		out.println("  </div>");
	 		out.println("</div></body>");
	 		out.println("</html>");
		
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	
}

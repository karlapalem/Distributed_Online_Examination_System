

import java.io.IOException;
import java.io.Serializable;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddSubTopicpage
 */
@WebServlet("/AddSubTopicpage")
public class AddSubTopicpage extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
   
    public AddSubTopicpage() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
	 	PrintWriter out=response.getWriter();
	 	HttpSession aSesssion = request.getSession(false);
	 	String code=request.getParameter("code");
	 	aSesssion.setAttribute("code67",code);
	   
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
	 	out.println("<header><div class=\"container\">");
	 	//out.println("<h11><marquee> <p >Software and Web Applications Security:State-of-the-Art Courseware and Learning Paradigm</p></marquee></h11>");
	 	out.println("<nav> <ul>  <li class=\"current\"><a href=\"http://www.examination.com/PASS/Logout\" class=\"m1\">Log Out</a></li> </ul> </nav>  </div>");
	 	out.println("	</header>");
	 	out.println("  <div class=\"container\">");
	 	out.println("    <h3>Instructer Add Sub Topic</h3>");
	 	out.println("     <h4> <ul class=\"categories\">");
		out.println("<form name='form1' action='http://www.examination.com/PASS/AddSubtopic' method='get' onsubmit='return validate()' >"
		    +"<fieldset><legend><font size='3' color='blue'>Enter</font></legend><br><br><label>SubTopic Name :"
			+"<input  type='text'  name='subtopic'  /></label><br><br></fieldset>"	
			+"<input type='hidden' name='code' value='"+code+"'>"
			+"<input class='submit' type='submit' value='Add'></form></table>");
		out.println("	  </ul></h4><h2>Fresh <span>News</span></h2>");
	 	out.println("      <ul class=\"news\"><li><strong>June 30, 2013</strong>");
	 	out.println("      <h4>Education</h4> Focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students with updated courseware . </li> </ul> ");
	 	out.println("  </div>");
	 	out.println("</div></body></html>");
	  }

	

}

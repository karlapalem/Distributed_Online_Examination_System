

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

/**
 * Servlet implementation class ResultDisplay
 */
@WebServlet("/ResultDisplay")
public class ResultDisplay extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
   
    public ResultDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession aSesssion = request.getSession(false);
	 	String email=(String)aSesssion.getAttribute("email");
	 	aSesssion.setAttribute("email",email);
	 	String maintopic=(String) aSesssion.getAttribute("maintopic");
		String subtopicname=(String) aSesssion.getAttribute("subtopicname");
	 	HttpSession s=request.getSession();
		int NofQtions= (int)s.getAttribute("NofQtions");
		int count=(Integer) s.getAttribute("count");
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
	 	out.println("    <h3>Result</h3>");
	 	out.println("     <h4> <ul class=\"categories\">");
	 	
		out.println("Number Of Questions &nbsp=&nbsp"+NofQtions+"<br><br>Right Answers &nbsp:&nbsp"+count);
		Databaseopp d=new Databaseopp();
	 	boolean b =d.stuResult(aSesssion,email,count,NofQtions);
	 	if(b==true)
	 	{
	 		System.out.println("::::::Result inserted::::::::::");
	 	}
	 	else 
	 		System.out.println("::::::Fail in insertion::::::::::");
	 
	 		
	 	out.println("</h4><br><br><br>Click on below link to go Back<br><br><A HREF='http://www.examination.com/PASS/AttemptExame?subtopicname="+subtopicname+"&maintopic="+maintopic+"'>Back:</A>");
		out.println("  </div>");
	 	out.println("</div></body>");
	 	out.println("</html>");
	 	
	 	
	}

	

}

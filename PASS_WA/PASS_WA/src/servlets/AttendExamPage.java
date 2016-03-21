package servlets;
import java.io.IOException;
import java.io.Serializable;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.ResultSet;

import Databasepack.Databaseopp;
/**
 * Servlet implementation class AttendExamPage
 */
@WebServlet("/AttendExamPage")
public class AttendExamPage extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendExamPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession aSesssion = request.getSession(false);
		String type = (String) aSesssion.getAttribute("type");
	 	String email=(String)aSesssion.getAttribute("email");
	 	String subtopicname=(String)aSesssion.getAttribute("subtopicname");
	 	String testname=(String)aSesssion.getAttribute("testname");
	 	int q=(Integer)aSesssion.getAttribute("q");
	 	int count=(Integer) aSesssion.getAttribute("count");
	 	int attempt=(Integer) aSesssion.getAttribute("attempt");
		String maintopic=(String)aSesssion.getAttribute("maintopic");
		int NofQtions=(int)aSesssion.getAttribute("NofQtions");
	    aSesssion.setAttribute("type",type);
	 	aSesssion.setAttribute("subtopicname",subtopicname);
	 	aSesssion.setAttribute("testname",testname);
	 	aSesssion.setAttribute("email",email);
	 	
	
		
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
	 	out.println("<script> ");
		HttpSession s=request.getSession();
		int Min= (int)s.getAttribute("Min");
		int sec= (int)s.getAttribute("sec");
	    out.println("var Min = "+Min+" ");
		out.println("var timeout = "+sec+"");
		out.println("function timer() ");
		out.println("{ ");
		out.println("if( --timeout > 0 ) ");
		out.println("{ ");
		out.println("document.forma.clock1.value = Min; ");
		out.println("document.forma.clock.value = timeout; ");
		out.println("window.setTimeout( \"timer()\", 1000 ); ");
		out.println("} ");
		out.println("else ");
		out.println("{ ");
		out.println("  if(Min>0)" +
				         "{" +
				      	"timeout=60;Min--;  "
				      	+"document.forma.clock1.value = Min;"
						+"document.forma.clock.value = timeout; "
						+"window.setTimeout( \"timer()\", 1000 );"
						+"} ");
			out.println("else " +
						"{");
			out.println("alert('Time over')");
			out.println(" window.location.replace ('http://www.examination.com/PASS/ExameResult?status=0')");
			out.println("}");
		out.println(" }");
		out.println("} ");
		out.println("//--> ");
		out.println("</script> ");
	
	 	out.println("</head>");
	 	out.println("<body onload=timer(); id=\"page1\">");
	 	out.println("<div class=\"wrap\">");
	 	out.println("  <header>  <div class=\"container\">");
	 	//out.println("      <h11><marquee> <p >Software and Web Applications Security:State-of-the-Art Courseware and Learning Paradigm</p></marquee></h11>");
	 	out.println("       <nav> <ul>  <li class=\"current\"><a href=\"http://www.examination.com/PASS/Logout\" class=\"m1\">Log Out</a></li> </ul> </nav>  </div>");
	 	out.println("	</header>");
	 	out.println("  <div class=\"container\">");
	 	
	 	out.println("    <h3>"+testname+"</h3>");
	 	out.println("     <h4> <ul class=\"categories\">");
	 	out.println("<form name='forma' action='http://www.examination.com/PASS/ExameResult' method='get'/>");
		out.println("Seconds Remaining:<input type=\"text\" style='text-align: center; border:0px solid white; width:30px; ' name=\"clock1\" value='"+Min+"  'style='border:0px solid white'> ");
		out.println(":");
		out.println(" <input type=\"text\" style='text-align: center; border:0px solid white; width:30px; ' name=\"clock\" value='"+sec+"  'style='border:0px solid white'><br><br> ");

	 
	 	s.setAttribute("q",q);
	 	s.setAttribute("count",count);
	 	s.setAttribute("attempt",attempt);
		s.setAttribute("NofQtions",NofQtions);
	 	s.setAttribute("maintopic",maintopic);
		s.setAttribute("subtopicname",subtopicname);
		s.setAttribute("testname",testname);
		
	 	
	 	Databaseopp d=new Databaseopp();
	 	ResultSet rs;
	 	rs = (ResultSet) d.accessQtion(s,q,type);
	 	
	 	try
		{
	 	if(rs!=null)
		 {	while(rs.next())
		 	{
		 	s.setAttribute("DBans",rs.getString(6));	
			out.println(rs.getString(1));
		 	 	for(int i=2;i<6;i++)
			 	{
					out.println("<br><INPUT TYPE='radio' NAME='g"+q+"' VALUE='"+rs.getString(i)+"'>"+rs.getString(i)+"");
				}
		 	}
		 out.println("<input type=\"hidden\" name=\"status\" value='1'");
			
		 	out.println("<br><center><input type='submit' value='next'> <br></form>");
		 }
	 	else {  out.println("Error");}
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	   out.println("	<br>  </ul></h4><h2>Fresh <span>News</span></h2>");
	   out.println("      <ul class=\"news\"><li><strong>June 30, 2013</strong>");
	   out.println("      <h4>Education</h4> Focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students with updated courseware . </li> </ul> ");
	   out.println("  </div>");
	   out.println("</div></body>");
	   out.println("</html>");
	 	
	}

	
}

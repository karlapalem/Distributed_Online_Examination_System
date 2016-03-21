

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
 * Servlet implementation class Addexamepeper
 */
@WebServlet("/Addexamepeper")
public class Addexamepeper extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
   
    public Addexamepeper() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
	 	PrintWriter out=response.getWriter();
	 	String maintopic=request.getParameter("maintopic");
	 	String subtopicname=request.getParameter("subtopicname");
	 	HttpSession aSesssion = request.getSession(false);
	 	String name = (String) aSesssion.getAttribute("name");
	 	String type = (String) aSesssion.getAttribute("type");
	 	aSesssion.setAttribute("name",name);
	 	aSesssion.setAttribute("type",type);
	 	
	
	 	
		
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
	 	
	 	 out.println("<script language=javascript>	");
		 out.println(" function validate()");
		 out.println(" {");
		 out.println("	 var a = document.form1.testname.value;");
		 out.println("		var b = document.form1.file1.value;");
		 out.println("		");
		 out.println("		if(a.length==0)");
		 out.println("		{");
		 out.println("			alert(\"Please Enter testname\");");
		 out.println("			return false;			");
		 out.println("		}");
		 out.println("		if(b.length==0)");
		 out.println("		{");
		 out.println("			alert(\"Please Upload Exam Paper \");");
		 out.println("			return false;");
		 out.println("		");
		 out.println("		}");
		
		 out.println(" }");
		 out.println("</script>");
	 	
	 	out.println("</head>");
	 	out.println("<body id=\"page1\">");
	 	out.println("<div class=\"wrap\">");
	 	out.println("  <header>  <div class=\"container\">");
	 	//out.println("      <h11><marquee> <p >Software and Web Applications Security:State-of-the-Art Courseware and Learning Paradigm</p></marquee></h11>");
	 	out.println("       <nav> <ul>  <li class=\"current\"><a href=\"http://www.examination.com/PASS/Logout\" class=\"m1\">Log Out</a></li> </ul> </nav>  </div>");
	 	out.println("	</header>");
	 	out.println("  <div class=\"container\">");
	 	
	 	out.println("<form name='form1' action='http://www.examination.com/PASS/ExamePaperPath' method='post' enctype='multipart/form-data' onsubmit='return validate()'>"
	 		+"<font color='blue'><h3>Upload Exampaper</h3></font>"
			+"<input type='hidden' name='name' value='"+name+"' />"			
			+"<input type='hidden' name='maintopic' id= 'maintopic' value='"+maintopic+"'/><br>"
			+"<input type='hidden' name='subtopicname' value='"+subtopicname+"'/><br></label>" 	
			+"TestName :<input  type='text'  name='testname'  /><br><br>"
			+"source :<input type='file' name='file1' ><br><br>"				 
			+"<input class='submit' type='submit' value='Upload'>"
			+"</form><br><br><br>");
	 	out.println("	  </ul></h4><h2>Fresh <span>News</span></h2>");
	 	out.println("      <ul class=\"news\"><li><strong>June 30, 2013</strong>");
	 	out.println("      <h4>Education</h4> Focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students with updated courseware . </li> </ul> ");
	 	out.println("</body></html>");
		
	}

	

}

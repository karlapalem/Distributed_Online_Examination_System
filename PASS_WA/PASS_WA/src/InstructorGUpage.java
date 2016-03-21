

import java.io.IOException;
import java.io.Serializable;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/InstructorGUpage")
public class InstructorGUpage extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
   
    public InstructorGUpage() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
	 	PrintWriter out=res.getWriter();
	 	String name=req.getParameter("name");
	 	HttpSession aSesssion = req.getSession(false);
	 	aSesssion.setAttribute("name",name); 
	 	 
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
	 	out.println("<!--[if lt IE 7]>");
	 	out.println("<link rel=\"stylesheet\" href=\"css/ie6.css\" type=\"text/css\" media=\"screen\">");
	 	out.println("<script type=\"text/javascript\" src=\"js/ie_png.js\"></script>");
	 	out.println("<script type=\"text/javascript\">ie_png.fix(\'.png, footer, header nav ul li a, .nav-bg, .list li img\');</script>");
	 	out.println("<![endif]-->");
	 	out.println("<!--[if lt IE 9]><script type=\"text/javascript\" src=\"js/html5.js\"></script><![endif]-->");
	 	out.println("</head>");
	 	out.println("<body id=\"page1\">");
	 	out.println("<!-- START PAGE SOURCE -->");
	 	out.println("<div class=\"wrap\">");
	 	out.println("  <header>");
	 	out.println("    <div class=\"container\">");
	 	//out.println("      <h11><marquee> <p >Software and Web Applications Security:State-of-the-Art Courseware and Learning Paradigm</p></marquee></h11>");
	 	out.println("      <nav>");
	 	out.println("        <ul>");
	 	out.println("          <li class=\"current\"><a href=\"http://www.examination.com/PASS/Logout\" class=\"m1\">Logout</a></li>");
	 
	 	
	 	out.println("        </ul>");
	 	out.println("      </nav>");
	 	out.println("      </div>");
	 	out.println("  </header>");
	 	out.println("  <div class=\"container\">");
	 	out.println("    <aside>");
	 	out.println("      <h3>Welcome</h3>");
	 	out.println("      <ul class=\"categories\">");
	 	
	 	out.println("       </ul>");
	 	out.println("     <h2>Fresh <span>News</span></h2>");
	 	out.println("      <ul class=\"news\">");
	 	out.println("        <li><strong>June 30, 2013</strong>");
	 	out.println("          <h4>Education</h4>");
	 	out.println("           Focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students with updated courseware . </li>");
	 	out.println("		   </ul>");
	 	out.println("    </aside>");
	 	out.println("    <section id=\"content\">");
	 	out.println("      <div id=\"banner\">");
	 	out.println("        <h2>Professional <span>Online Education <span>Since 2013</span></span></h2>");
	 	out.println("      </div>");
	 	out.println("      <div class=\"inside\">");
	 	out.println("       <h2>Move Forward <span>With Your Education</span></h2>");
	 	out.println("        <p><span class=\"txt1\"> Software and Web applications security: state-of-the-art courseware and learning paradigm</span> This site is focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students. </p>");
	 	out.println("       <fieldset><br><h2><legend><font size=\"3\" color=\"blue\">Select  Students</legend></font></h2>");
	 	out.println("				 <A HREF=\"http://www.examination.com/PASS/Instructermaintopics\">GRADUATES :<br><br>");
	 	out.println("				 <A HREF=\"http://www.examination.com/PASS/InsytructerUgmaintopics\">UNDER GRADUATES :<br><br></fieldset>");
	 	out.println("				");
	 	out.println("      </div>");
	 	out.println("    </section>");
	 	out.println("  </div>");
	 	out.println("</div></body>");
	 	out.println("</html>");

	}

	
}

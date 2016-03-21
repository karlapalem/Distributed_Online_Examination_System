

import java.io.IOException;
import java.io.Serializable;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Databasepack.Databaseopp;

/**
 * Servlet implementation class Instructeruploadexcesise
 */
@WebServlet("/Instructeruploadexcesise")
public class Instructeruploadexcesise extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
   
    public Instructeruploadexcesise() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try
		{
			
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
	 	out.println("</head>");
	 	out.println("<body id=\"page1\">");
	 	out.println("<div class=\"wrap\">");
	 	out.println("  <header>  <div class=\"container\">");
	 	//out.println("      <h11><marquee> <p >Software and Web Applications Security:State-of-the-Art Courseware and Learning Paradigm</p></marquee></h11>");
	 	out.println("       <nav> <ul>  <li class=\"current\"><a href=\"http://www.examination.com/PASS/Logout\" class=\"m1\">Log Out</a></li> </ul> </nav>  </div>");
	 	out.println("	</header>");
	 	out.println("  <div class=\"container\">");
	 	out.println("    <h3>"+subtopicname+" Notes</h3>");
	 	out.println("     <h4> <ul class=\"categories\">");
	  	Databaseopp d=new Databaseopp();
	 	ResultSet rs=d.ContentAvailDowloadDiplay1(maintopic,subtopicname,type);
	   
	 	if(rs==null)
	 	{out.println("<br>No Notes Available<br>");}
	 	else 
	 	{
	 		while(rs.next())
		 	{
		 	out.println("<li><span><A HREF='http://www.examination.com/PASS/FileDownloadservlet?subtopicname="+subtopicname+"&maintopic="+maintopic+"&filename="+rs.getString(1)+"'>"+rs.getString(1)+":</A></span></li>");

		 	}
	 	}
		out.println("    </ul></h4><h3>Available Tests to Attend</h3>");
	 	out.println("     <h4> <ul class=\"categories\">");
	  
	 	ResultSet rs1=d.ExampapersAvailDowloadDiplay(maintopic,subtopicname,type);
	 	String testname="+rs1.getString(1)+";
	 	if(rs1==null)
	 	{out.println("<br>No Tests Available<br>");}
	 	else 
	 	{
	 	while(rs1.next())
	 	{
	 		out.println("<li><span><A HREF='http://www.examination.com/PASS/ExampaperDownloadPage?subtopicname="+subtopicname+"&maintopic="+maintopic+"&testname="+rs1.getString(1)+"'>"+rs1.getString(1)+":</A></span></li>");
	 		
	  	}}
	 	out.println("<br><center><A HREF='http://www.examination.com/PASS/Fileuploadservletpage?subtopicname="+subtopicname+"&maintopic="+maintopic+"'>Add content:</A>&nbsp&nbsp&nbsp"
			    +"<A HREF='http://www.examination.com/PASS/DeleteContent?subtopicname="+subtopicname+"&maintopic="+maintopic+"'>Delete content:</A>&nbsp&nbsp&nbsp&nbsp"
			    +"<A HREF='http://www.examination.com/PASS/Addexamepeper?subtopicname="+subtopicname+"&maintopic="+maintopic+"'>Add Exam paper:</A>&nbsp&nbsp&nbsp&nbsp"
			    +"<A HREF='http://www.examination.com/PASS/InstructerRemoveExampaperpage?subtopicname="+subtopicname+"&maintopic="+maintopic+"&testname="+testname+"'>Remove Exam peper:</A>&nbsp&nbsp&nbsp&nbsp</center>");
		out.println("	  </ul></h4><h2>Fresh <span>News</span></h2>");
	 	out.println("      <ul class=\"news\"><li><strong>June 30, 2013</strong>");
	 	out.println("      <h4>Education</h4> Focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students with updated courseware . </li> </ul> ");
	 	out.println("  </div>");
	 	out.println("</div></body>");
	 	out.println("</html>");
	 		} 
		catch (Exception e) 
		{
			
		}		
	}

	

}

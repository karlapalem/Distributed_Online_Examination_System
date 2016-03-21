

import java.io.IOException;
import java.io.Serializable;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InstructerAddContent
 */
@WebServlet("/InstructerAddContent")
public class InstructerAddContent extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
   
    public InstructerAddContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
	 	PrintWriter pw=response.getWriter();
	 	String maintopic=request.getParameter("maintopic");
	 	String subtopicname=request.getParameter("subtopicname");
	 	String html="<html><title>Instructer Upload</title></head><body bgcolor=' maroon'>"
	 	+"<table bgcolor='lightyellow' align='center' width='800'>"
	 	+"<tr><td width='660' valign='top'>"
	 	+"<div align='center' ><font size='5' color='blue'>Instructer Add Content </font></div>" 
			 	      
		+"<form action='http://www.examination.com/PASS/Fileuploadservlet'  method='get' enctype='multipart/form-data'>"
		+"<fieldset>"
		+"<legend><font size='3' color='blue'>Enter</font></legend>"
		+"<label><br>Enter name:"
		+"<input value=''  type='text' name='name'  />"
		+"</label><br><br>"
			 						
		+"<label>filename :"
		+"<input  type='text'  name='filename1'  />"
		+"</label><br><br>"
		
		+"<label>source :"
		+"<input type='file' name='file1' >" 
		+"<input type='hidden' name='maintopic' value= '"+maintopic+"'/>"
		+"<input type='hidden' name='subtopicname' value= '"+subtopicname+"'/>" 
		+"</label><br><br>"				 
			 					 
			 					 
		+"</fieldset>" +
		"<input class='submit' type='submit' value='submit'>"
		+"</form></table></body></html>";
	 	pw.print(html);
	 	
	}

	

}



import java.io.IOException;
import java.io.Serializable;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InstructerDeleteContentpage
 */
@WebServlet("/InstructerDeleteContentpage")
public class InstructerDeleteContentpage extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
   
    public InstructerDeleteContentpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	 	PrintWriter pw=response.getWriter();
	 	
		pw.println("<html><body bgcolor=' maroon'><table bgcolor='lightyellow' align='center' width='800'><tr><td width='660' valign='top'>"
		 	 +"<div align='center' >Instructer </div><form action='http://localhost/SWAS/InstructerDeleteContent' method='get' enctype='multipart/form-data'>"
			 +"<fieldset><legend><font size='3' color='blue'>Enter</font></legend><label><br>Enter name:"
			 +"<input value=''  type='text' name='name'  /></label>"
			 +"<input type='hidden' name='maintopic' id= 'maintopic' value='"+request.getParameter("maintopic")+"'/><br>"
			 +"<input type='hidden' name='subtopicname' value='"+request.getParameter("subtopicname")+"'/><br>" 	
										
			+"<label>filename :<input  type='text'  name='filename1'  /></label><br><br></fieldset>"	
			+"<input class='submit' type='submit' value='Remove'></form></table></body></html>");

	}

	

}

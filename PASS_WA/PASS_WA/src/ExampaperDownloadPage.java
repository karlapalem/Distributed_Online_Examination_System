

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.ResultSet;

import Databasepack.Databaseopp;

/**
 * Servlet implementation class ExampaperDownloadPage
 */
@WebServlet("/ExampaperDownloadPage")
public class ExampaperDownloadPage extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
   
    public ExampaperDownloadPage() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
    	PrintWriter out=response.getWriter();
     	String maintopic=request.getParameter("maintopic");
     	String subtopicname=request.getParameter("subtopicname");
     	String testname=request.getParameter("testname");
     	HttpSession aSesssion = request.getSession(false);
	 	String name = (String) aSesssion.getAttribute("name");
	 	String type = (String) aSesssion.getAttribute("type");
	 	aSesssion.setAttribute("name",name);
	 	aSesssion.setAttribute("type",type);
        Databaseopp d=new Databaseopp();
    	ResultSet rs=(ResultSet) d.ExampapersPathDown(maintopic,subtopicname,testname,type);
    	String fileName=null;
    try
    {	while (rs.next())
    		{
    			 fileName=rs.getString(1);
    		
    		}
        fileName=fileName.replace("////","//");
        FileReader inputFile = new FileReader(fileName);
        BufferedReader bufferReader = new BufferedReader(inputFile);
        String line;
        out.println("<html><body bgcolor='lightblue'>");
        while ((line = bufferReader.readLine()) != null)  
	        {
	        	out.println(line+"<br>");	
	        }
    }
        catch (Exception e) 
        {
			// TODO: handle exception
		}	
	}



}

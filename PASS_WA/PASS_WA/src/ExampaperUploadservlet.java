

import javax.servlet.annotation.*;

import java.io.Serializable;
import com.mysql.jdbc.PreparedStatement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



/**
 * Servlet implementation class ExampaperUploadservlet
 */
@WebServlet("/ExampaperUploadservlet")
@MultipartConfig
public class ExampaperUploadservlet extends HttpServlet implements Serializable
{ 
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = 
    Logger.getLogger(ExampaperUploadservlet.class.getCanonicalName());

	
   
    public ExampaperUploadservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw=res.getWriter();
		try{
			
			
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chary","root","minnita0706");
			
		
		 try{
			 
                res.setContentType("text/html");
                String name=req.getParameter("name");
                String maintopic=req.getParameter("maintopic");
                String subtopicname=req.getParameter("subtopicname");
                String testname=req.getParameter("testname");          
                final Part filePart = req.getPart("file1");
           	    final String fileName = getFileName(filePart);
           		 PreparedStatement ps =  (PreparedStatement) con.prepareStatement("insert into exampapers values(?,?,?,?,?,?)"); 
                 ps.setString(1,name); 
	   			 ps.setString(2,maintopic);
	   			 ps.setString(3,subtopicname);
	   			 ps.setString(4,testname);
	   			 ps.setBinaryStream(5, filePart.getInputStream(),(int)filePart.getSize());
	   			 ps.setString(6,fileName);
		   		int c=ps.executeUpdate();
                if(c==0){pw.println("<h1>Upload fail");}
                else{pw.println("<h1>Uploaded");}
            }
            finally{if(con!=null)con.close();}
        }
        catch(ClassNotFoundException ce){pw.println("<h1> Fail");}
		
        catch(SQLException se){pw.println("<h1> Fail");} 
		catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
        pw.flush();
        pw.close();
	}
	private String getFileName(final Part part) 
   	{
   	    final String partHeader = part.getHeader("content-disposition");
   	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
   	    for (String content : part.getHeader("content-disposition").split(";")) 
   	    {
   	        if (content.trim().startsWith("filename")) 
   	        {
   	            return content.substring(
   	                    content.indexOf('=') + 1).trim().replace("\"", "");
   	        }
   	    }
   	    return null;
   	}
}

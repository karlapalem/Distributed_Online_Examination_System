package servlets;

import java.io.*;
import java.io.Serializable;
    import javax.servlet.*;
    import javax.servlet.http.*;
    import javax.servlet.annotation.*;

import Databasepack.Databaseopp;

import com.mysql.jdbc.PreparedStatement;

import java.sql.*;
/**
 * Servlet implementation class Fileuploadservlet
 */
@WebServlet("/Fileuploadservlet")
@MultipartConfig
public class Fileuploadservlet extends HttpServlet implements Serializable
{	 private static final long serialVersionUID = 1L;
	 private String getFileName(final Part part) 
	    {
	  //final String partHeader = part.getHeader("content-disposition");
	   for (String content : part.getHeader("content-disposition").split(";")) 
	     {
	     if (content.trim().startsWith("filename")) 
	       {
	       return content.substring(content.indexOf('=') +   
	                               1).trim().replace("\"", "");
	       }
	        }
	        return null;
	    }
    public Fileuploadservlet() 
    {
        super();
        
    }

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{  
			PrintWriter out=res.getWriter();
			HttpSession aSesssion = req.getSession(false);
		 	String type = (String) aSesssion.getAttribute("type");
		 	aSesssion.setAttribute("type",type);
			String name=req.getParameter("name");
            String maintopic=req.getParameter("maintopic");
            String subtopicname=req.getParameter("subtopicname");
            String filename=req.getParameter("filename");          
         
			Connection con = null;
		try
		 {
			int i=0;
			Databaseopp d=new Databaseopp();
			ResultSet rs=d.ContentAvailDowloadDiplay1(maintopic,subtopicname,type);
			 try
			 {
				 if(rs!=null){
				while(rs.next())
				 {
					if(rs.getString(1).equals(filename))
					{	i=1;
						
					}
				}}
			} 
			 catch (SQLException e)
			{	
				
				e.printStackTrace();
			}
	     	
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
		 	if(i==1)
		 	{
		 		out.println(" <h4> File Name Already Existed");
			}
		 	else
		 	{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chary","root","minnita0706");
		
		 	
			Part file1 = req.getPart("file1");
			final String fileNameExt = getFileName(file1);   
			String extension = "";

			int j = fileNameExt.lastIndexOf('.');
			if (j > 0) {
			    extension = fileNameExt.substring(j+1);
			}

       
                
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("insert into fileupload values(?,?,?,?,?,?,?)"); 
            ps.setString(1,name); 
            ps.setString(2,maintopic);
            ps.setString(3,subtopicname);
            ps.setString(4,filename);
            ps.setBinaryStream(5, file1.getInputStream(),(int)file1.getSize()); 
            ps.setString(6,type);
            ps.setString(7,extension);
            int c=ps.executeUpdate();
            if(c==0){out.println("Upload failed");}
            else{out.println("<h4>Successfully Uploaded</h4><br><br>");}  
            if(con!=null){ con.close();}   
		 	}
            out.println("	  </ul></h4><h2>Fresh <span>News</span></h2>");
		 	out.println("      <ul class=\"news\"><li><strong>June 30, 2013</strong>");
		 	out.println("      <h4>Education</h4> Focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students with updated courseware . </li> </ul> ");
		 	out.println("  </div>");
		 	out.println("</div></body>");
		 	out.println("</html>");
		 	out.flush();
			out.close();
		 }
        catch (Exception e) 
        {
			
			e.printStackTrace();
		}
	}
		
}

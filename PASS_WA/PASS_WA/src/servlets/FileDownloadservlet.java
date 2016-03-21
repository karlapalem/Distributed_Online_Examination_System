package servlets;

import java.io.IOException;
import java.io.Serializable;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Blob;
/**
 * Servlet implementation class FileDownloadservlet
 */
@WebServlet("/FileDownloadservlet")
public class FileDownloadservlet extends HttpServlet implements Serializable
{	 private static final long serialVersionUID = 1L;
	
    public FileDownloadservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try
		{
			HttpSession aSesssion = request.getSession(false);
		 	String name = (String) aSesssion.getAttribute("name");
		 	String type = (String) aSesssion.getAttribute("type");
		 	aSesssion.setAttribute("name",name);
		 	aSesssion.setAttribute("type",type);
		 	Connection conn = null;
			Statement stmt = null;
			ResultSet rs=null;
			Blob b = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chary","root","minnita0706");
			stmt = conn.createStatement();
			String sql = "select source,extension from fileupload where maintopic='"+request.getParameter("maintopic")+"' and chapter='"+request.getParameter("subtopicname")+"' and filename='"+request.getParameter("filename")+"'and type='"+type+"'";
			rs=stmt.executeQuery(sql);
		if(rs.next())
		
		b = (Blob)rs.getBlob(1);
		String extension=rs.getString(2);
		if(b==null){System.out.println("fail blob");}
	
		if(b != null)
		{
			
			String fileName =request.getParameter("filename");
			byte[] ba = b.getBytes(1, (int)b.length());
			
			switch (extension)
			{
            case "docx": response.setHeader("Content-Type", "application/msword");
            break;
            case "doc": response.setHeader("Content-Type", "application/msword");
            break;
            case "txt":response.setHeader("Content-Type", "text/plain");
            break;
            case "rar":response.setHeader("Content-Type", "application/x-rar-compressed	");
            break;
            case "xlsx": response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            break;
            case "pptx": response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
            break;
            case "ppt": response.setHeader("Content-Type", "application/vnd.ms-powerpoint");
            break;
            case "xls": response.setHeader("Content-Type", "application/vnd.ms-excel");
            break;
            default: System.out.println("\n default extension ="+extension);
            response.setHeader("Content-Type", "application/"+extension+"");
			}
			response.setHeader("Content-Disposition","attachement; filename=\""+fileName+"\"");
			OutputStream os = response.getOutputStream();
			os.write(ba);
			
			os.close();
			ba = null;
			
		}
		else
			System.out.println(" DBFile not Downloaded successfully ");
		}
			catch (Exception e)
		{
			
		}

		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

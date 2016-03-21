
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import Databasepack.Databaseopp;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class ExamePaperPath
 */
@WebServlet("/ExamePaperPath")

public class ExamePaperPath extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	private List<FileItem> fileItem = null;
	
	
	@SuppressWarnings("unchecked")
	protected List<FileItem> initRequest(HttpServletRequest req) {
	boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(!isMultipart) throw new UnsupportedOperationException();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(REQUEST_SIZE);
		List<FileItem> formItems = null;
		try 
		{
			formItems = upload.parseRequest(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formItems;
	}
	protected String getFieldValue(List<FileItem> formItems, String fieldName) 
	{
		String value = null;
		try {
			for(FileItem fi : formItems )
			{
				if (fi.isFormField()) 
				{
					if(fi.getFieldName().equals(fieldName))
					{
						value = fi.getString();
					}
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return value;
	}
	protected File uploadFile(List<FileItem> formItems, String destFolder) 
	{
		String uploadPath = destFolder;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		File uploadedFile = null;
		try {
			for(FileItem fi : formItems ){
				if (!fi.isFormField()) {
					String fileName = new File(fi.getName()).getName();
					String filePath = uploadPath + File.separator + fileName;
					uploadedFile = new File(filePath);
					fi.write(uploadedFile);
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return uploadedFile;
	}
	
       
    
    public ExamePaperPath() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	 try 
		{
		 int i=0;
		fileItem = initRequest(request);
		HttpSession aSesssion = request.getSession(false);
		String type = (String) aSesssion.getAttribute("type");
		aSesssion.setAttribute("type",type);
		String name= getFieldValue(fileItem,"name");
		String maintopic= getFieldValue(fileItem,"maintopic");
		String subtopicname= getFieldValue(fileItem,"subtopicname");
		String testname= getFieldValue(fileItem,"testname");
		Databaseopp d=new Databaseopp();
		ResultSet rs=d.ExampapersAvailDowloadDiplay(maintopic,subtopicname,type);
		if(rs!=null)
		{
		while(rs.next())
		 {
			if(rs.getString(1).equals(testname))
			{	i=1;
				
			}
		 }
		}
		PrintWriter out=response.getWriter();
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chary","root","minnita0706");
		
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
		 	out.println("<header><div class=\"container\">");
		 	//out.println("<h11><marquee> <p >Software and Web Applications Security:State-of-the-Art Courseware and Learning Paradigm</p></marquee></h11>");
		 	out.println("<nav> <ul><li class=\"current\"><a href=\"http://www.examination.com/PASS/Logout\" class=\"m1\">Log Out</a></li> </ul> </nav>  </div>");
		 	out.println("	</header>");
		 	out.println("  <div class=\"container\">");
		if(i==1)
		{out.println(" <h4> Exam Paper Name Already Existed<br>");}
		else
		{
		File file = uploadFile(fileItem,"C:\\SWAS\\");
		String filepath ="C:\\SWAS\\"+file.getName();
		
		boolean b=false;
		filepath=filepath.replace("////","//");
	    FileReader inputFile = new FileReader(filepath);
       BufferedReader bufferReader = new BufferedReader(inputFile);
       String line;
       int q=1;
		String[] Qtns=new String[150];
		while ((line = bufferReader.readLine()) != null)  
		{
			String[] result =line.split(" ");
			String s=new String(result[0]);
			if(s.charAt(0)=='A')
			{	
				
				    b=d.QutionsAns(request,q,Qtns[q],result,type, maintopic,subtopicname,testname);
				q++;
			 }
			else
			{
				Qtns[q]=line;
			}
		}
		PreparedStatement ps =  (PreparedStatement) con.prepareStatement("insert into exampapers values(?,?,?,?,?,?,?)"); 
		ps.setString(1,name); 
		ps.setString(2,maintopic);
		ps.setString(3,subtopicname);
		ps.setString(4,testname);
		ps.setString(5,filepath);
		ps.setString(6,type);
		ps.setInt(7,(q-1));
		int c=ps.executeUpdate();
		
	     if((c==0)&&(b==true))
		   { out.println("<h4>Exam Paper Uploading fail"); }
		   else
		   {  out.println("<h4>Exam Paper Uploaded</h4><br>");
		   }
	 	
		
		}
	 	out.println("	  </ul></h4><h2>Fresh <span>News</span></h2>");
	 	out.println("      <ul class=\"news\"><li><strong>June 30, 2013</strong>");
	 	out.println("      <h4>Education</h4> Focused on designed and developed state-of-the-art courseware and appropriate learning paradigm in software and Web applications security for computer science and computer information systems students with updated courseware . </li> </ul> ");
	 	out.println("  </div>");
	 	out.println("</div></body>");
	 	
	  } 
	  catch (Exception e) 
	  {
			
	  }
	 response.sendRedirect("./InstructorGUpage");
	}
}

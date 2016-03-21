package dbuploaddownload;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;

public class DBUploadDownload
{

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs=null;
	PreparedStatement st;
	public boolean openConnection()
	{
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chary","root","minnita0706");
			
			stmt = conn.createStatement();
			return true;
			}
			catch(Exception e)
			{
					e.printStackTrace();
			}
		return false;
	}
	public boolean colseConnection()
	{
		try
		{
			stmt.close();
			conn.close();
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public boolean searchfileFound(String maintopic,String subtopicname,String filename)
	{
	 try
		{		
	   	 openConnection();
		 stmt=conn.createStatement();
		 rs=stmt.executeQuery("select * from fileupload where maintopic='"+maintopic+"' and chapter='"+subtopicname+"'");
		 	if(rs.next())
			{
				System.out.println("searched");
			   return true;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return false;			
	}
	public boolean searchExampaperFound(String maintopic,String subtopicname)
	{
	 try
		{		
	   	 openConnection();
		 stmt=conn.createStatement();
		 rs=stmt.executeQuery("select * from exampapers where maintopic='"+maintopic+"'and chapter='"+subtopicname+"'");
			if(rs.next())
			{
				
			   return true;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 
	return false;			
	}
	public boolean fileDownload(HttpServletResponse response,String maintopic,String subtopicname, String filename)
	{
	 try
				{
				openConnection();
				stmt=conn.createStatement();
				rs=stmt.executeQuery("select source,filename from fileupload  where maintopic='"+maintopic+"'and chapter='"+subtopicname+"'and filename='"+filename+"'");
				rs.next();
				Blob b = null;
				b = (Blob) rs.getBlob(1);
				
				if(b!= null)
				{
					
					byte[] ba = b.getBytes(1, (int)b.length());
					response.setContentType("application/msword");
					response.setHeader("Content-Disposition","attachment; filename=\""+rs.getString(2)+"\"");
					OutputStream os = response.getOutputStream();
					os.write(ba);
				    os.close();
				   
					ba = null;
					return true;
					
				}
				else
					System.out.println(" DBFile not Downloaded successfully ");
				}
	 			catch (Exception e)
				{
					
				}
	return false;
	}
	public boolean searchExampaperRead(HttpServletResponse response,String maintopic,String subtopicname)
	{
	 try
				{
				openConnection();
				stmt=conn.createStatement();
				String sub="Abusing search function";
				String sql="select source,filename from exampapers  where chapter='"+sub+"'";
				rs=stmt.executeQuery(sql);
				rs.next();
				Blob b = null;
				b = (Blob) rs.getBlob(1);
				
				if(b!= null)
				{
					
					byte[] ba = b.getBytes(1, (int)b.length());
					response.setContentType("application/msword");
					response.setHeader("Content-Disposition","attachment; filename=\""+rs.getString(2)+"\"");
					OutputStream os = response.getOutputStream();
				    os.write(ba);
				    os.close();
					ba = null;
					return true;
					
					
				}
				else
					System.out.println(" DBFile not Downloaded successfully ");
				}
	 			catch (Exception e)
				{
					
				}
	return false;
	}
	public int ExamPaperDelete(HttpServletRequest request,String name,int i,ArrayList<String> al,String type)
	{int s=0;
		try 
		{	
		
		openConnection();
		for(int j=0;j<i;j++)
		{
		String sql = "delete from exampapers where email='"+name+"' and maintopic='"+request.getParameter("maintopic")+"' and chapter='"+request.getParameter("subtopicname")+"' and testname='"+al.get(j)+"' and type='"+type+"'";
		
		String sql1 = "delete from examqtionans where maintopic='"+request.getParameter("maintopic")+"' and chapter='"+request.getParameter("subtopicname")+"' and testname='"+al.get(j)+"' and type='"+type+"'";
		String sql2 = "delete from studentresult where subtopicname='"+request.getParameter("subtopicname")+"' and testname='"+al.get(j)+"' and type='"+type+"'";
		s = stmt.executeUpdate(sql);
		s = stmt.executeUpdate(sql1);
		s = stmt.executeUpdate(sql2);s++;
		}
		return s;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
		
	}
	public int ContentDelete(HttpServletRequest request,String name,int i,ArrayList<String> al,String type)
	{
		try 
		{
		int s=0;
		openConnection();
		for(int j=0;j<i;j++)
		{
		
		String sql = "delete from fileupload where email='"+name+"' and maintopic='"+request.getParameter("maintopic")+"' and chapter='"+request.getParameter("subtopicname")+"' and filename='"+al.get(j)+"' and type='"+type+"'";
		
		s = stmt.executeUpdate(sql);
		}
		return s;
		} 
		catch (Exception e)
		{
			return 0;	
		}
		
		
	}
	
}

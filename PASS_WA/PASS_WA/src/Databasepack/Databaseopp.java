package Databasepack;

import java.sql.*;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;

import Sturecordpack.Sturecord;

public class Databaseopp 
{
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs=null;
	public boolean openConnection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chary","root","minnita0706");
			stmt = conn.createStatement();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean colseConnection(){
		try{
			stmt.close();
			conn.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public Sturecord readRecord(HttpServletRequest req)
	{
		Sturecord record=new Sturecord();
		
		record.setFirstname(req.getParameter("firstname"));
		record.setLastname(req.getParameter("lastname"));
		record.setTelephone(Long.parseLong(req.getParameter("telephone")));
		record.setSelect1(req.getParameter("gu"));
		record.setEmail(req.getParameter("email"));
		record.setPassword(req.getParameter("password1"));
	
		return record;
	}
	public Sturecord readInstructerRecord(HttpServletRequest req)
	{
		Sturecord record=new Sturecord();
		
		record.setFirstname(req.getParameter("firstname"));
		record.setEmail(req.getParameter("email"));
		record.setPassword(req.getParameter("password1"));
	
		return record;
	}	
	public boolean checkRegister(Sturecord record)
	{
	 try
				{
				openConnection();
				String sql = "select email from studatabase where email='"+record.getEmail()+"'";		
			
				rs = stmt.executeQuery(sql);
				if(rs.next())
					{
						colseConnection();
						return true;
					}
				} 
				catch(Exception e)
				{
					e.printStackTrace();
				}
					colseConnection();
				return false;	
	}
	public boolean checkInstructorRegister(Sturecord record)
	{
	 try
				{
				openConnection();
				String sql = "select email from instructor where email='"+record.getEmail()+"'";		
				
				rs = stmt.executeQuery(sql);
				if(rs.next())
					{
						colseConnection();
						return true;
					}
				} 
				catch(Exception e)
				{
					e.printStackTrace();
				}
					colseConnection();
				return false;	
	}
	public boolean insertRecord(Sturecord record) throws IOException, SQLException
	{				
		try
		{
			openConnection();
			String sql = "insert into studatabase values('"+ record.getFirstname()+"','"+record.getLastname()+"','"+record.getSelect1()+"','"+record.getEmail()+"','"+record.getPassword()+"','"+record.getTelephone()+"')" ;
			int i=stmt.executeUpdate(sql);
			if(i!=0)
			{
				colseConnection();
				return true;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
			colseConnection();
		return false;				
		
	}
	public boolean insertInstructorRecord(Sturecord record) throws IOException, SQLException
	{				
		try
		{
			openConnection();
			String sql = "insert into instructor values('"+ record.getFirstname()+"','"+record.getEmail()+"','"+record.getPassword()+"')" ;
			int i=stmt.executeUpdate(sql);
			if(i!=0)
			{
				colseConnection();
				return true;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
			colseConnection();
		return false;				
		
	}	
	
	
	public Sturecord readloginRecord(HttpServletRequest req)
	{
		Sturecord record=new Sturecord();
		record.setLoginemail(req.getParameter("user"));
		record.setLoginpassword(req.getParameter("password"));
		return record;
	}	
	
	public String chekValid(Sturecord record) throws IOException, SQLException
	{		
		try
		{	
			openConnection();
			String sql = "select gu from studatabase where email='"+record.getLoginemail() +"' and password='"+record.getLoginpassword()+"'";		
			
			rs = stmt.executeQuery(sql);
			  if(rs.next()){
				  
				  return rs.getString(1);
			  }
			  else{
				  
				  return "login failed";
			  }
		}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		return "Server problem";
	}
	public String chekInstructorValid(Sturecord record) throws IOException, SQLException
	{		
		try
		{	
			openConnection();
			String sql = "select email from instructor where email='"+record.getLoginemail() +"' and password='"+record.getLoginpassword()+"'";		
			
			rs = stmt.executeQuery(sql);
			  if(rs.next()){
				  
				  return rs.getString(1);
			  }
			  else{
				  
				  return "login failed";
			  }
		}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		return "Server problem";
	}
	public ResultSet readMaintopics1(String email) 
	{
		try 
		{	
		openConnection();
		
		String sql = "select * from maintopics where email='"+email+"'";
		rs = stmt.executeQuery(sql);
		} 
		catch (Exception e)
		{
			
		}
		
		return rs;
	}
	
	public ResultSet readMaintopics() 
	{
		try 
		{	
		openConnection();
		
		String sql = "select * from maintopics";
		rs = stmt.executeQuery(sql);
		} 
		catch (Exception e)
		{
			
		}
		
		return rs;
	}
	
	
	public ResultSet readSubtopics(HttpServletRequest req)
	{
		try 
		{	
		openConnection();
		
		String sql = "select subtopics from subtopics where code='"+req.getParameter("topicId")+"'";
		
		rs = stmt.executeQuery(sql);
		} 
		catch (Exception e)
		{
			
		}
		
		return rs;
	}
	

	public ResultSet readSubtopics1(HttpServletRequest req,String name)
	{
		try 
		{	
		openConnection();
		
		String sql = "select subtopics from subtopics where code='"+req.getParameter("topicId")+"' and email='"+name+"'";
		
		rs = stmt.executeQuery(sql);
		} 
		catch (Exception e)
		{
			
		}
		
		return rs;
	}
	public ResultSet readUgSubtopics(HttpServletRequest req)
	{
		try 
		{	
		openConnection();
		
		String sql = "select subtopics from ugsubtopics where code='"+req.getParameter("topicId")+"'";
		
		rs = stmt.executeQuery(sql);
		} 
		catch (Exception e)
		{
			
		}
		
		return rs;
	}
	
	public ResultSet readUgSubtopics1(HttpServletRequest req,String email)
	{
		try 
		{	
		openConnection();
		
		String sql = "select subtopics from ugsubtopics where code='"+req.getParameter("topicId")+"' and email='"+email+"'";
		
		rs = stmt.executeQuery(sql);
		} 
		catch (Exception e)
		{
			
		}
		
		return rs;
	}
	
	public boolean imageDownload(HttpServletResponse res)
	{
	 try
				{
				openConnection();
				PreparedStatement st = (PreparedStatement) conn.createStatement();
				rs=st.executeQuery("select image from btable where name='Penguins'");
				
				rs.next();
				Blob b = null;
				b = (Blob) rs.getBlob(1);
				
				if(b!= null)
				{
					String fileName = "image";
					byte[] ba = b.getBytes(1, (int)b.length());
					 res.setContentType("image/jpg");
					res.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
					
					OutputStream os = res.getOutputStream();
					os.write(ba);
					os.close();
					ba = null;
					
					return true;
					
				}
				else
					System.out.println(" record not Downloaded successfully ");
				}
	 			catch (Exception e)
				{
					
				}
	return false;
	}
	
	public int addMaintopics(HttpServletRequest request,String name)
	{
		try 
		{	
		openConnection();
		
		String sql1 = "select code from maintopics where code='"+request.getParameter("code")+"'";
		
		ResultSet rs1 = stmt.executeQuery(sql1);
			if(rs1.next()==false)
			{
		
			String sql = "insert into maintopics values('"+request.getParameter("code")+"','"+request.getParameter("maintopic")+"','"+name+"')";
			
			int i = stmt.executeUpdate(sql);
			return i;
			}
		return 0;
		} 
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
		return 0;
	}
	
	
	public int removeMaintopics(int i,ArrayList<String> al)
	{
		int s=0;String code = null;
		try 
			{	
			openConnection();
			
			for(int j=0;j<i;j++)
			{
			String sql = "select code from maintopics where topicname='"+al.get(j)+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{code=rs.getString(1);System.out.println("\ncode=="+code);}
			String sql1 = "delete from  maintopics where topicname='"+al.get(j)+"'";
			String sql2 = "delete from subtopics where code='"+code+"'";
			String sql3 = "delete from ugsubtopics where code='"+code+"'";
			String sql4 = "delete from exampapers where maintopic='"+al.get(j)+"'";
			String sql5 = "delete from examqtionans where maintopic='"+al.get(j)+"'";
			String sql6 = "delete from fileupload where maintopic='"+al.get(j)+"'";
			String sql7 = "delete from studentresult where maintopic='"+al.get(j)+"'";
		
			
			s = stmt.executeUpdate(sql1);
			
			s = stmt.executeUpdate(sql2);
			
			s = stmt.executeUpdate(sql3);
			
			s = stmt.executeUpdate(sql4);
			
			s = stmt.executeUpdate(sql5);
			
			s = stmt.executeUpdate(sql6);
			
			s = stmt.executeUpdate(sql7);
			
			
			s++;
			}
			return s;
			} 
			catch (Exception e)
			{return 0;
				
			}
			
			
	}
	
	public int addSubtopics(HttpServletRequest request,String name)
	{
		try 
		{	
		openConnection();
		
		String sql = "insert into subtopics values('"+request.getParameter("code")+"','"+request.getParameter("subtopic")+"','"+name+"')";
		
		int i = stmt.executeUpdate(sql);
		return i;
		} 
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
		return 0;
	}
	public int addUgSubtopics(HttpServletRequest request,String name)
	{
		try 
		{	
		openConnection();
		
		String sql = "insert into ugsubtopics values('"+request.getParameter("code")+"','"+request.getParameter("subtopic")+"','"+name+"')";
		
		int i = stmt.executeUpdate(sql);
		return i;
		} 
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
		return 0;
	}
	public int removeSubtopics(int i,ArrayList<String> al,String code,String type)
	{
		int s=0;
		try 
		{	
		openConnection();
		System.out.println("\n\n\n\nint i,ArrayList<String> al"+i+","+al+"  type "+type);
		for(int j=0;j<i;j++)
		{
		
		String sql = "delete from subtopics where code='"+code+"' and subtopics='"+al.get(j)+"'";
		
		String sql1 = "delete from exampapers where chapter='"+al.get(j)+"' and type='"+type+"'";
		String sql2 = "delete from examqtionans where chapter='"+al.get(j)+"' and type='"+type+"'";
		String sql3 = "delete from studentresult where subtopicname='"+al.get(j)+"' and type='"+type+"'";
		String sql4 = "delete from fileupload where chapter='"+al.get(j)+"' and type='"+type+"'";
		
		s = stmt.executeUpdate(sql3);
		
		
		s = stmt.executeUpdate(sql4);
	
		
		s = stmt.executeUpdate(sql);
		
		s = stmt.executeUpdate(sql1);
		
		s = stmt.executeUpdate(sql2);
		
		s++;
		}
		return s;
		} 
		catch (Exception e)
		{return 0;
			// TODO: handle exception
		}
		
		
	}
	public int removeUgSubtopics(int i,ArrayList<String> al,String code,String type)
	{
		int s=0;
		try 
		{	
		openConnection();
		
		for(int j=0;j<i;j++)
		{
		
		String sql = "delete from ugsubtopics where code='"+code+"' and subtopics='"+al.get(j)+"'";
		
		String sql1 = "delete from exampapers where chapter='"+al.get(j)+"' and type='"+type+"'";
		String sql2 = "delete from examqtionans where chapter='"+al.get(j)+"' and type='"+type+"'";
		String sql3 = "delete from studentresult where subtopicname='"+al.get(j)+"' and type='"+type+"'";
		String sql4 = "delete from fileupload where chapter='"+al.get(j)+"' and type='"+type+"'";
		
		s = stmt.executeUpdate(sql3);
		
		
		s = stmt.executeUpdate(sql4);
		
		
		s = stmt.executeUpdate(sql);
		
		s = stmt.executeUpdate(sql1);
		
		s = stmt.executeUpdate(sql2);
		
		s++;
		}
		return s;
		} 
		catch (Exception e)
		{return 0;
			// TODO: handle exception
		}
		
		
	}
	public ResultSet ContentAvailDowloadDiplay(String maintopic,String subtopicname,String type,String name)
	{
	 try
		{		
	   	 openConnection();
	   	 String sql="select filename from fileupload where maintopic='"+maintopic+"' and chapter='"+subtopicname+"' and type='"+type+"' and email='"+name+"'";
		 stmt=conn.createStatement();
		 rs=stmt.executeQuery(sql);
		
		 if(rs.next())
			{ rs.beforeFirst(); 
				return rs;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 
	return null;			
	}
	public ResultSet ContentAvailDowloadDiplay1(String maintopic,String subtopicname,String type)
	{
	 try
		{		
	   	 openConnection();
		 stmt=conn.createStatement();
		 String sql="select filename from fileupload where maintopic='"+maintopic+"' and chapter='"+subtopicname+"' and type='"+type+"'";
		 rs=stmt.executeQuery(sql);
		
		 if(rs.next())
			{ rs.beforeFirst(); 
				return rs;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	return null;			
	}
	public ResultSet ExampapersAvailDowloadDiplay(String maintopic,String subtopicname,String type)
	{
	 try
		{		
	   	 openConnection();
		 stmt=conn.createStatement();
		 System.out.println(maintopic);
		 System.out.println(subtopicname);
		 System.out.println(type);
		 String sql="select testname from exampapers where maintopic='"+maintopic+"' and chapter='"+subtopicname+"' and type='"+type+"'";
		 rs=stmt.executeQuery(sql);
		 
		 if(rs.next())
			 System.out.println(rs.getString(1));
			{ rs.beforeFirst(); 
			System.out.println("\nreurned");
				return rs;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 
	return null;			
	}
	public ResultSet ExampapersAvailDowloadDiplay(String maintopic,String subtopicname,String type,String name)
	{
	 try
		{		
	   	 openConnection();
		 stmt=conn.createStatement();
		 String sql="select testname from exampapers where maintopic='"+maintopic+"' and chapter='"+subtopicname+"' and type='"+type+"' and email='"+name+"'";
		 rs=stmt.executeQuery(sql);
		
		 if(rs.next())
			{ rs.beforeFirst(); 
			
				return rs;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 return null;
	
				
	}
	public boolean add(HttpServletRequest request) throws IOException, SQLException
	{				
		try
		{
			String code=request.getParameter("code");
			String topic=request.getParameter("topic");
			openConnection();
			String sql = "insert into ugsubtopics values('"+code+"','"+topic+"')" ;
			int i=stmt.executeUpdate(sql);
			if(i!=0)
			{ 
				colseConnection();
				return true;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
			colseConnection();
		return false;				
		
	}
	public int ExampapersPath(String maintopic,String subtopicname,String testname,String type)
	{
	 try
		{	
		 int NoOfqtions=0;
	   	 openConnection();
		 stmt=conn.createStatement();
		 String sql="select * from examqtionans where maintopic='"+maintopic+"' and chapter='"+subtopicname+"' and testname='"+testname+"' and type='"+type+"'";
		
		 rs=stmt.executeQuery(sql);
			while(rs.next())
			{  
				NoOfqtions++;
			}
			return NoOfqtions;
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 
	return 0;			
	}
	public ResultSet ExampapersPathDown(String maintopic,String subtopicname,String testname,String type)
	{
	 try
		{	
		 
	   	 openConnection();
		 stmt=conn.createStatement();
		 String sql="select path from exampapers where maintopic='"+maintopic+"' and chapter='"+subtopicname+"' and testname='"+testname+"' and type='"+type+"'";
		 
		 rs=stmt.executeQuery(sql);
			if(rs!=null)
			{  
				 System.out.println("Returned");
				 return rs;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	return null;			
	}
	public boolean QutionsAns(HttpServletRequest request,int q,String qtns,String[] result,String type,String maintopic,String subtopicname,String testname) throws IOException, SQLException
	{				
		try
		{
			openConnection();
			String sql = "insert into examqtionans values("+q+",  '"+qtns+"',  '"+result[0]+"' , '"+result[1]+"' ,'"+result[2]+"' ,'"+result[3]+"' ,'"+result[4]+"','"+maintopic+"','"+subtopicname+"','"+testname+"','"+type+"')";
		  
			int i=stmt.executeUpdate(sql);
			if(i!=0)
			{
				
				colseConnection();
				return true;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
			colseConnection();
		return false;				
		
	}
	public ResultSet accessQtion(HttpSession s ,int q,String type)
	{				
		try
		{	
		 
	   	 openConnection();
		 stmt=conn.createStatement();
		 String sql="select qtion,option1,option2,option3,option4,ans from examqtionans where qtionno="+q+" and maintopic='"+s.getAttribute("maintopic")+"' and chapter='"+s.getAttribute("subtopicname")+"' and testname='"+s.getAttribute("testname")+"' and type='"+type+"'";
		
		 rs=stmt.executeQuery(sql);
			if(rs!=null)
			{  
				
				 return rs;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	return null;			
	}
	public int deleteQtionAns(String maintopic,String subtopicname,String testname )
	{				
		try
		{	
		
	   	 openConnection();
		 stmt=conn.createStatement();
		 String sql="delete from examqtionans where maintopic='"+maintopic+"' and chapter='"+subtopicname+"' and testname='"+testname+"'";
		
		 int i=stmt.executeUpdate(sql);
		 return i;	
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	return 0;			
	}
	public boolean stuResult(HttpSession aSesssion,String email,int count,int NofQtions) 
	{				
		try
		{
			String subtopicname=(String) aSesssion.getAttribute("subtopicname");
		 	String testname=(String) aSesssion.getAttribute("testname");
		 	String type = (String) aSesssion.getAttribute("type");
		 	
			openConnection();
			ResultSet rs=checkAttempt(email,subtopicname,testname,type);
			int rightAns=0;
			int attempt=0;
			if(rs!=null)
			{
				while(rs.next())
				{
				rightAns=rs.getInt(1);
				attempt=rs.getInt(2);
				}
				if(rightAns<count){rightAns=count;}		
				attempt=attempt+1;
				if(attempt>3){attempt=3;}
			}
			else {return false;}	
			String sql = "UPDATE studentresult SET rightAns="+rightAns+",attempt="+attempt+" where email='"+email+"' and subtopicname='"+subtopicname+"' and testname='"+testname+"' and type='"+type+"'";
			int j=stmt.executeUpdate(sql);
			if(j!=0)
			{
				
				colseConnection();
				return true;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
			colseConnection();
		return false;				
		
	}
	public ResultSet checkAttempt(String email,String subtopicname,String testname,String type)
	{				
		try
		{	
		 
	   	 openConnection();
		 stmt=conn.createStatement();
		 String sql="select rightAns,attempt,email from studentresult where email='"+email+"' and  subtopicname='"+subtopicname+"' and testname='"+testname+"' and  type='"+type+"'";
		
		 rs=stmt.executeQuery(sql);
			if(rs.next())
			{ rs.beforeFirst(); 
				return rs;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	return null;			
	}
	public boolean insertBeforeResult(String email,String maintopic,int NofQtions,String subtopicname,String testname,String type)
	{				
		try
		{
			openConnection();
			String sql = "insert into studentresult values('"+email+"',0,  "+NofQtions+" ,0,'"+testname+"','"+subtopicname+"','"+type+"','"+maintopic+"')";
			int i=stmt.executeUpdate(sql);
			if(i!=0)
			{ 
				colseConnection();
				return true;
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
			colseConnection();
		return false;				
		
	}
 }






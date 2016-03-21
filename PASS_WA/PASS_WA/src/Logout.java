

import java.io.IOException;
import java.io.Serializable;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*Logout for customer and Organization*/
@WebServlet("/Logout")
public class Logout extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
	HttpSession	 session;  
    
    public Logout() {
        super();
        
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.getSession().invalidate();
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.forward(request, response);

    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javafx.scene.text.Font;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mego
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            String emailVal = request.getParameter("email");
            String passwordVal = request.getParameter("password");
					
            Connection con = null;
                                        
            con = createCon();
                                        
            String sql = "select * from users where email = ? and password = ? "; 
					
            PreparedStatement ps = con.prepareStatement(sql);
                                        
            ps.setString(1, emailVal);
            ps.setString(2, passwordVal);
                                        
            ResultSet result = ps.executeQuery();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            
            if(! result.next()) {
        	
                out.println("<h1> Erroooooooooooooooor</h1>");
                			
            }
            result.beforeFirst();
            
            while( result.next() ){
		
                out.println("<h1>" + result.getString("email") +"</h1>");
                out.println("<h1>" + result.getString("password") +"</h1>");
                
            }
            
            
            out.println("</body>");
            out.println("</html>");
				
            
        }
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    public Connection createCon() {
        
        
        
        Connection con = null;
		
	try{  

            Class.forName("com.mysql.jdbc.Driver"); 
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root",""); 
			  
	}catch(Exception e){ 
            System.out.println(e);
			
	}		
	return con;
    }
}

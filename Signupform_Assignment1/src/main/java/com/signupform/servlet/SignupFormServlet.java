package com.signupform.servlet;

import java.io.IOException;


import com.signupform.dao.UserDao;
import com.signupform.model.SignupForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignupFormServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        String preferredName = request.getParameter("preferredName");
        String name = request.getParameter("name");

        SignupForm sf = new SignupForm();
        sf.setName(userName);
        sf.setUseremail(userEmail);
        sf.setPassword(password);
        sf.setPassword1(password1);
        sf.setPreferredname(preferredName);
        sf.setName(name);

        try {
            UserDao dao = new UserDao();
            int result = dao.register(sf);
            
            if (result > 0) {
                response.sendRedirect("home.jsp");
            } else {
                response.getWriter().println("Error registering user. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Server Error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}

package ru.itis.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    private String error;
    private String success;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("success", this.success);
        req.setAttribute("error", this.error);

        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.error = null;
        this.success = null;

        String captcha = (String) req.getServletContext().getAttribute("captcha");
        String userCaptcha = req.getParameter("captcha");

        if (userCaptcha != null && userCaptcha.equals(captcha)) {
            this.success = "Success";
        } else {
            this.error = "Error";
        }

        resp.sendRedirect("/main");
    }
}

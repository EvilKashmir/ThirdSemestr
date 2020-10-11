package ru.itis.servlets;

import ru.itis.utils.Check;
import ru.itis.utils.MyWriter;
import ru.itis.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        String checkboxOn = req.getParameter("apply");

        if (Check.checkAll(name, email, password, passwordRepeat, checkboxOn)) {
            User user = new User(name, email, password);
            MyWriter myWriter = new MyWriter(user);
            myWriter.write();
        }
        resp.sendRedirect("/main");
    }
}

package ru.itis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        String checkboxOn = req.getParameter("apply");

        if (checkAll(name, email, password, passwordRepeat, checkboxOn)) {
            User user = new User(name, email, password);
            MyWriter myWriter = new MyWriter(user);
            myWriter.write();
        }
        req.getRequestDispatcher("views/register.jsp").forward(req, resp);
    }

    private boolean checkAll(String name, String email, String password, String passwordRepeat, String checkboxOn) {
        if (name != null && email != null && password != null && passwordRepeat != null && checkboxOn != null)
            return Pattern.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", email) &&
                    Pattern.matches(password, passwordRepeat) &&
                    Pattern.matches(checkboxOn, "true");
        return false;
    }
}

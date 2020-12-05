package ru.itis.api;

import com.google.gson.Gson;
import ru.itis.model.Person;
import ru.itis.repository.PersonRepository;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/v1/person")
public class PersonController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        PersonRepository personRepository = (PersonRepository) req.getServletContext().getAttribute("personRepository");

        Person person = personRepository.getPersonById(id);

        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(new Gson().toJson(person));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        PersonRepository personRepository = (PersonRepository) req.getServletContext().getAttribute("personRepository");

        if (!personRepository.deletePersonById(id)) {
            resp.setStatus(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonRepository personRepository = (PersonRepository) req.getServletContext().getAttribute("personRepository");

        String name = req.getParameter("name");
        String stringGender = req.getParameter("gender");

        if (name == null || stringGender == null) {
            resp.setStatus(400);
        } else {
            if (!personRepository.savePerson(new Person(name, stringGender.equals("true")))) {
                resp.setStatus(400);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PersonRepository personRepository = (PersonRepository) req.getServletContext().getAttribute("personRepository");
        Map<String, String> parameters = getParameters(req.getInputStream());

        String name = parameters.get("name");
        String stringGender = parameters.get("gender");
        String id = parameters.get("id");

        if (name == null || stringGender == null || id == null) {
            resp.setStatus(400);
        } else {
            Person person = new Person(name, stringGender.equals("true"));
            if (!personRepository.updatePersonById(Long.parseLong(id), person)) {
                resp.setStatus(400);
            }
        }
    }

    private Map<String, String> getParameters(ServletInputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, String> resultMap = new HashMap<>();

        try {
            while(br.ready()) {
                String line = br.readLine();
                for (String parameter : line.split("&")) {
                    parameter = parameter.replaceAll("%20", " ");
                    String[] parameterSplit = parameter.split("=");
                    resultMap.put(parameterSplit[0], parameterSplit[1]);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return resultMap;
    }

}

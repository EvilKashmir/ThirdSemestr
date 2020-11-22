package ru.itis.controllers;

import ru.itis.utils.RandomString;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);

        Random random = new Random();
        int randomX = random.nextInt(50);
        int randomY = random.nextInt(150);

        Graphics2D graphics = (Graphics2D) image.getGraphics();


        for (int i = 0; i < 250; i += 25) {
            graphics.setPaint(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            graphics.fill(new Rectangle2D.Float(0, i, 255, 25));
        }

        String captchaString = RandomString.getAlphaNumericString(random.nextInt(3) + 4);
        req.getServletContext().setAttribute("captcha", captchaString);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Hello", Font.BOLD, 34));
        graphics.drawString(captchaString, 50 + randomX, 50 + randomY);

        resp.setContentType("image/png");
        ImageIO.write(image, "png", resp.getOutputStream());

    }
}

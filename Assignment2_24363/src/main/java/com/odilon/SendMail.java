package com.odilon;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String userEmail = request.getParameter("email");
        
        if (userEmail == null || userEmail.isEmpty()) {
           
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email is required");
            return;
        }
        
     
        final String username = "na367255@gmail.com";
        final String password = "pmqektlrsoanznks";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with the email server
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Confirmation Email");
            message.setText("Thank you for registering. We have received your email.");

            // Send the email
            Transport.send(message);

            // Redirect to welcome.html after sending the email
            response.sendRedirect("welcome.html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

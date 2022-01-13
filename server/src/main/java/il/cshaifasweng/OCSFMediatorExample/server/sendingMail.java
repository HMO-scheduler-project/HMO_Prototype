package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import java.util.Properties;
import java.time.temporal.ChronoUnit;
import javax.mail.*;
import javax.mail.internet.*;

public class sendingMail {

    private static String from = "brioottova1";
    public static void sendMessage(String toMail, String subject, String msg) {
        sendEmail(msg,subject,toMail,from);
    }

    public static void sendScheduledAppointmentMessage(String toMail, Appointment app) {
        String subject = "Appointment details";
        String message = "Dear "+app.getPatient().getFirstName()+"\nYour appointment information:\n"
                +"Date: "+app.getDate()+"\nTime: "+app.getTime().truncatedTo(ChronoUnit.MINUTES)+"\nClinic: "+
                app.getClinic().getName();
        sendEmail(message,subject,toMail,from);
    }

    public static void sendRemainder(String toMail, Appointment app) {
        String subject = "You have an appointment tomorrow";
        String message =
                "Dear "+app.getPatient().getFirstName()+"You have an appointment scheduled for tommorow\nYour appointment information:\n"
                        +"Date: "+app.getDate()+"\nTime: "+app.getTime().truncatedTo(ChronoUnit.MINUTES)+"\nClinic: "+
                        app.getClinic().getName()+"\nIf you can't came to the appointment, please cancel it.";
        sendEmail(message, subject, toMail, from);
    }


    private static void sendEmail(String content, String subject, String to, String from) {
        String password = "hmoproject";

        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        properties.put("gmail.smtp.host", host);
        properties.put("gmail.smtp.port", "465");
        properties.put("gmail.smtp.ssl.enable", "true");
        properties.put("gmail.smtp.auth", "true");
        properties.put("gmail.smtp.user", from);
        properties.put("gmail.smtp.starttls.enable","true");
        properties.put("gmail.smtp.debug", "true");
        ;

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, password);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(content);

            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}




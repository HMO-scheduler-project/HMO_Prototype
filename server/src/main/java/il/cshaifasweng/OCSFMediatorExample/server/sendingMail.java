package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.Properties;
import java.time.temporal.ChronoUnit;
import javax.mail.*;
import javax.mail.internet.*;

public class sendingMail {
    private static final String from = "brioottova1@gmail.com";

    public static void sendMessage(String toMail, String subject, String msg) {
        sendEmail(msg, subject, toMail);
    }

    public static void sendScheduledAppointmentMessage(String toMail, Appointment app) {
        String subject = "Appointment details";
        String message = "Dear " + app.getPatient().getFirstName() + "\nYour appointment information:\n"
                + "Date: " + app.getDate() + "\nTime: " + app.getTime().truncatedTo(ChronoUnit.MINUTES) + "\nClinic: " +
                app.getClinic().getName();
        sendEmail(message, subject, toMail);
    }

    public static void sendRemainder(String toMail, Appointment app) {
        String subject = "You have an appointment tomorrow";
        String message =
                "Dear " + app.getPatient().getFirstName() + "You have an appointment scheduled for tomorrow\nYour appointment information:\n"
                        + "Date: " + app.getDate() + "\nTime: " + app.getTime().truncatedTo(ChronoUnit.MINUTES) + "\nClinic: " +
                        app.getClinic().getName() + "\nIf you can't came to the appointment, please cancel it.";
        sendEmail(message, subject, toMail);
    }


    private static void sendEmail(String content, String subject, String to) {
        String password = "hmoproject";
        // Get system properties
        Properties properties = System.getProperties();
        properties.put("gmail.smtp.user", sendingMail.from);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sendingMail.from, password);

                    }

                });
//        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sendingMail.from));

            // Set To: header field of the header.
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
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




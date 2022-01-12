package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import java.util.Properties;
import java.time.temporal.ChronoUnit;
import javax.mail.*;
import javax.mail.internet.*;

public class sendingMail {

        private static String from = "brioottova@gmail.com";
        public static void sendMessage(String toMail, String subject, String msg) {
            sendEmail(msg,subject,toMail,from);
        }

        public static void sendScheduledAppointmentMessage(String toMail, Appointment app) {
            String subject = "Appointment details";
            String message = "Dear "+App.getFirst_name()+"\nYour appointment information:\n"
                    +"Date: "+app.getDate()+"\nTime: "+app.getTime().truncatedTo(ChronoUnit.MINUTES)+"\nClinic: "+
                    app.getClinic().getName();
            sendEmail(message,subject,toMail,from);
        }

        public static void sendRemainder(String toMail, Appointment app) {
            String subject = "You have an appointment tomorrow";
            String message =
                    "Dear "+App.getFirst_name()+"You have an appointment scheduled for tommorow\nYour appointment information:\n"
                            +"Date: "+app.getDate()+"\nTime: "+app.getTime().truncatedTo(ChronoUnit.MINUTES)+"\nClinic: "+
                            app.getClinic().getName()+"\nIf you can't came to the appointment, please cancel it.";
            sendEmail(message, subject, toMail, from);
        }


        private static void sendEmail(String message, String subject, String to, String from) {

            String host ="localhost";
//            String password = "hmoproject";

            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
//            properties.setProperty("mail.password", password);
            Session session = Session.getDefaultInstance(properties);
            MimeMessage m = new MimeMessage(session);
            try {
                m.setFrom(new InternetAddress(from));
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(subject);
                m.setText(message);
                Transport.send(m);
                System.out.println("email sending was successful");
            } catch (MessagingException e) {
                e.printStackTrace();
            }





        }

    }

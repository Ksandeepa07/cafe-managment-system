package lk.ijse.cafe_au_lait.util;


//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;


import net.sf.jasperreports.engine.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import java.io.File;
import java.util.Map;
import java.util.Properties;


public class EmailController {
    public static void sendEmail(String recipient, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String username = "cafeaulait460@gmail.com";
        String password = "pxfnelcsiqbyblfi";

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }



///////////////
public static void sendReport(String recipient, String reportName, Map<String, Object> parameters) {
    try {
        // Compile the Jasper Report from the JRXML file
        JasperReport jasperReport = JasperCompileManager.compileReport(reportName);

        // Fill the Jasper Report with data and generate a PDF file
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

        // Set up the email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String username = "cafeaulait460@gmail.com";
        String password = "pxfnelcsiqbyblfi";

        // Create a new email session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a new email message with the PDF file as an attachment
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("Jasper Report - " + reportName);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Please find attached the Jasper Report - " + reportName + ".");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();
        DataSource source = new ByteArrayDataSource(pdfBytes, "application/pdf");
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(reportName + ".pdf");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        // Send the email
        Transport.send(message);
        System.out.println("Jasper Report sent to " + recipient);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}



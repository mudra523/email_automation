package com.cold_email.cold_email.service;

// Java Program to Illustrate Creation Of
// Service implementation class


// Importing required classes
import com.cold_email.cold_email.entity.EmailDetails;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;


    String send="mudra@jobtechit.com";
    String email=
    "<!DOCTYPE html>\n" +
    "<html lang=\"en\">\n" +
    "<head>\n" +
    "    <meta charset=\"UTF-8\">\n" +
    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
    "    <title>Job Application</title>\n" +
    "</head>\n" +
    "<body>\n" +
    "    <p>Hello,</p>\n" +
    "    <p>I hope this email finds you in good spirits.</p>\n" +
    "    <p>My name is Mudra Koradia, and I am writing to express my interest in Software Engineer opportunities within your organization. " +
    "With 4+ years of experience in developing, deploying, and maintaining projects, I am excited about the opportunity to contribute to your team.</p>\n" +
    "    <p>A brief overview of my qualifications:</p>\n" +
    "    <ul>\n" +
    "        <li><strong>Full-Stack Expertise:</strong> Proficient in Java, JavaScript, Python, React.js, Next.js, Node.js, Express.js, Spring Boot, " +
    "alongside database management MySQL, PostgreSQL, and MongoDB.</li>\n" +
    "        <li><strong>Cloud Technologies:</strong> Hands-on experience with AWS and Google Cloud Platform, including services like DynamoDB, EC2, S3, " +
    "Redis, ElasticSearch, and CI/CD Pipelines. I also have experience with Docker, Jenkins, and Kubernetes for streamlined DevOps processes.</li>\n" +
    "        <li><strong>Work Experience:</strong>\n" +
    "            <ul>\n" +
    "                <li><strong>New York City Transit (MTA):</strong> Migrated legacy systems to AWS cloud platforms, designed responsive React.js-based data views " +
    "for transport monitoring, and integrated APIs for real-time outage reporting, enhancing scalability and operational efficiency.</li>\n" +
    "                <li><strong>Mayor’s Office, NYC:</strong> Developed dashboards with Spring Boot and React.js, integrating Jira API for real-time issue management, " +
    "and streamlined workflows, reducing manual updates.</li>\n" +
    "                <li><strong>Intex Technologies (India) Ltd.:</strong> Engineered scalable backend services with Java and Spring Boot, developed responsive front-end " +
    "with React.js, and implemented CI/CD pipelines using Docker and Kubernetes.</li>\n" +
    "            </ul>\n" +
    "        </li>\n" +
    "        <li><strong>Educational Background:</strong> I hold a Master’s degree in Computer Science from Pace University NYC and a Bachelor’s in Computer Engineering " +
    "from India LDRP Institute of Technology.</li>\n" +
    "        <li><strong>Leadership / Community Engagement:</strong> I am the chief of staff for Google Developers Group NYC, a member of Girls Who Code, and AnitaB.org. " +
    "I also organize events like GDG NYC’s DevFest, which focuses on Responsible AI and networking.</li>\n" +
    "    </ul>\n" +
    "    <p>I am drawn to your organization. I am confident that my background and skills align well with your team's requirements, and I am eager to bring my expertise " +
    "in software development and project management to your organization.</p>\n" +
    "    <p>Please find my resume attached for more detailed information about my background. I would welcome the opportunity to discuss how my experiences and skills " +
    "can contribute to your organization's continued success.</p>\n" +
    "    <p>Thank you for considering my application. I look forward to the possibility of discussing this exciting opportunity with you.</p>\n" +
    "    <p>Best Regards,</p>\n" +
    "    <p>\n" +
    "        Mudra Koradia<br>\n" +
    "        (She/ Her/ Hers)<br>\n" +
    "        Phone: +1 (929) 408‑9271<br>\n" +
    "        Email: <a href='mailto:mudra@jobtechit.com'>mudra@jobtechit.com</a><br>\n" +
    "        LinkedIn: <a href='https://linkedin.com/mudra523'>linkedin.com/mudra523</a><br>\n" +
    "        GitHub: <a href='https://github.com/mudra523'>github.com/mudra523</a><br>\n" +
    "        Leetcode: <a href='https://leetcode.com/u/mudra_k'>leetcode.com/u/mudra_k</a>\n" +
    "    </p>\n" +
    "</body>\n" +
    "</html>\n";

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Setting up necessary details
            helper.setFrom(send);
            helper.setTo(details.getRecipient());
            helper.setText(email, true);
            helper.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail" + e;
        }
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Setting up necessary details
            helper.setFrom(send);
            helper.setTo(details.getRecipient());
            helper.setText(email, true);
            helper.setSubject(details.getSubject());


            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            helper.addAttachment(
                    Objects.requireNonNull(file.getFilename()), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

    @Override
    public String sendBulkMailWithAttachment(EmailDetails details) {

        List<String> emailList= sendBulkEmail(details);

        for( String email : emailList){
            details.setRecipient(email);
            sendMailWithAttachment(details);

        }

        return "";
    }

    public List<String> sendBulkEmail(EmailDetails details){

        String csvFilePath = details.getCsv();
        List<String> emailList = extractEmailAddresses(csvFilePath);

        // Print the extracted email IDs
        emailList.forEach(System.out::println);

        return  emailList;
    }


    public static List<String> extractEmailAddresses(String filePath) {
        List<String> emailList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Read the header line
            if (line == null) {
                System.out.println("The CSV file is empty.");
                return emailList;
            }

            String[] headers = line.split(","); // Assuming CSV is comma-separated
            int emailColumnIndex = -1;

            // Find the index of the "email" column
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().equalsIgnoreCase("email")) {
                    emailColumnIndex = i;
                    break;
                }
            }

            if (emailColumnIndex == -1) {
                System.out.println("No 'email' column found in the CSV file.");
                return emailList;
            }

            // Process the remaining lines
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length > emailColumnIndex) {
                    String email = columns[emailColumnIndex].trim();
                    if (isValidEmail(email)) {
                        emailList.add(email);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return emailList;
    }

    // Helper method to validate email addresses
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; // Regex to match email addresses
        return email.matches(emailRegex);
    }
}


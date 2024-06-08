package com.sami.nodejavaconnection;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DocumentController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @PostMapping("/generate-report")
    public ResponseEntity<byte[]> generateReport(@RequestBody Document document) {
        try {
            // Log received document data
            logger.info("Received document data: {}", document);

            // Load the Jasper report file
            InputStream reportStream = getClass().getResourceAsStream("/reports/testing_report.jrxml");
            if (reportStream == null) {
                logger.error("Jasper report template not found");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Create parameters map
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", document.getName());
            parameters.put("email", document.getEmail());

            // Group the recommendations by termTitle and termShortDesc
            Map<String, FireSafetyIssueFlat> groupedIssues = new HashMap<>();
            for (Document.FireSafetyIssue issue : document.getFireSafetyIssues()) {
                String key = issue.getTermTitle() + "|" + issue.getTermShortDesc();
                FireSafetyIssueFlat flat = groupedIssues.getOrDefault(key, new FireSafetyIssueFlat(issue.getTermTitle(), issue.getTermShortDesc(), "", ""));

                String recommendations = issue.getTermRecommendation().stream()
                        .map(rec -> rec.getRecommendation())
                        .collect(Collectors.joining(", "));

                String importants = issue.getTermRecommendation().stream()
                        .map(rec -> rec.getImportant())
                        .collect(Collectors.joining(", "));


//                String important = issue.getTermRecommendation().stream()
//                        .map(rec -> String.valueOf(rec.isImportant()))
//                        .collect(Collectors.joining(", "));

                if (!flat.getRecommendation().isEmpty()) {
                    flat.setRecommendation(flat.getRecommendation() + ", " + recommendations);
                } else {
                    flat.setRecommendation(recommendations);
                }

                if (!flat.getImportant().isEmpty()) {
                    flat.setImportant(flat.getImportant() + ", " + importants);
                } else {
                    flat.setImportant(importants);
                }



//                if (!flat.getImportant()) {
//                    flat.setImportant(Boolean.parseBoolean(flat.getImportant() + ", " + important));
//                } else {
//                    flat.setImportant(Boolean.parseBoolean(important));
//                }

                groupedIssues.put(key, flat);
            }

            List<FireSafetyIssueFlat> flatList = new ArrayList<>(groupedIssues.values());

            System.out.println("Flatlist " + flatList);

//            System.out.println(flatList);

            // Convert flat list to JRBeanCollectionDataSource
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(flatList);

//            System.out.println("datSource " + dataSource);

            // Add the data source to the parameters map
            parameters.put("dataSource", flatList);


            System.out.println("Parameters " + parameters);

            // Fill the report with parameters and empty data source
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            // Export the report to PDF
            byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);

            // Debug log to confirm PDF generation
            logger.info("PDF report generated successfully, size: {} bytes", pdfReport.length);

            if (pdfReport.length == 0) {
                logger.error("Generated PDF report is empty");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Return the PDF as a byte array
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "document_report.pdf");

            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error generating report", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



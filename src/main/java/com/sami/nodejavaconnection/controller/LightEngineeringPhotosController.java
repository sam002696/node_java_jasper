package com.sami.nodejavaconnection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sami.nodejavaconnection.model.LightEngineeringDocument;
import com.sami.nodejavaconnection.model.LightEngineeringPhotos;
import com.sami.nodejavaconnection.model.LightEngineeringQuestions;
import com.sami.nodejavaconnection.model.NewDocument;
import net.sf.jasperreports.engine.*;
        import net.sf.jasperreports.engine.data.JsonDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LightEngineeringPhotosController {

    private static final Logger logger = LoggerFactory.getLogger(LightEngineeringPhotosController.class);

    @PostMapping("/light-engineering-photos-report")
    public ResponseEntity<byte[]> generateReport(@RequestBody LightEngineeringPhotos document) {
        try {
            // Log received document data
            logger.info("Received document data: {}", document);

            // Convert the document object to a JSON string using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(document);
            logger.info("Document as JSON: {}", jsonString);


            // Load the Jasper report file
            InputStream reportStream = getClass().getResourceAsStream("/reports/lePhotosSample.jrxml");
            if (reportStream == null) {
                logger.error("Jasper report template not found");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Prepare parameters map
            Map<String, Object> parameters = new HashMap<>();
            InputStream jsonDataStream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            JsonDataSource jsonDataSource = new JsonDataSource(jsonDataStream);
            parameters.put("jsonData", jsonDataSource);

            System.out.println("parameters " + parameters);

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jsonDataSource);

            // Export the report to PDF
            byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);

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


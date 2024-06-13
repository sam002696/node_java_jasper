package com.sami.nodejavaconnection;
import net.sf.jasperreports.engine.data.JsonDataSource;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import net.sf.jasperreports.engine.JRException;

public class JsonTest {
    public static void main(String[] args) {
        String jsonData = "{\"fireSafetyIssues\": [{\"issue\": \"Fire exit blocked\", \"severity\": \"High\"}, {\"issue\": \"No fire extinguisher\", \"severity\": \"Medium\"}]}";
        try {
            JsonDataSource dataSource = new JsonDataSource(new ByteArrayInputStream(jsonData.getBytes("UTF-8")));
            JsonDataSource subDataSource = dataSource.subDataSource("fireSafetyIssues");
            boolean iterator = subDataSource.next();
            while (iterator) {
                System.out.println(iterator);
            }
        } catch (JRException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

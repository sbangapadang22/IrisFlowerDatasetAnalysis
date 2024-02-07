package com.project.dataanalysis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String csvFile = "data/iris.csv";
        try (FileReader fileReader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : csvParser) {
            	String sepalLength = record.get("sepal_length");
            	String sepalWidth = record.get("sepal_width");
            	String petalLength = record.get("petal_length");
            	String petalWidth = record.get("petal_width");
            	String species = record.get("species");
                
                // Process each record as needed
                System.out.println("Sepal Length: " + sepalLength + ", Species: " + species);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

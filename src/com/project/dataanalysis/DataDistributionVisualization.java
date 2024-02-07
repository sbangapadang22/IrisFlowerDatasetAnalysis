package com.project.dataanalysis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.knowm.xchart.Histogram;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataDistributionVisualization {
    public static void main(String[] args) {
        String csvFile = "data/iris.csv"; // Path to your CSV file
        List<Double> sepalLengths = new ArrayList<>();
        List<Double> sepalWidths = new ArrayList<>();
        List<Double> petalLengths = new ArrayList<>();
        List<Double> petalWidths = new ArrayList<>();

        try (FileReader fileReader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : csvParser) {
                double sepalLength = Double.parseDouble(record.get("sepal_length"));
                double sepalWidth = Double.parseDouble(record.get("sepal_width"));
                double petalLength = Double.parseDouble(record.get("petal_length"));
                double petalWidth = Double.parseDouble(record.get("petal_width"));

                sepalLengths.add(sepalLength);
                sepalWidths.add(sepalWidth);
                petalLengths.add(petalLength);
                petalWidths.add(petalWidth);
            }

            // Create and display histograms for each attribute
            createHistogram("Sepal Length", sepalLengths);
            createHistogram("Sepal Width", sepalWidths);
            createHistogram("Petal Length", petalLengths);
            createHistogram("Petal Width", petalWidths);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createHistogram(String attributeName, List<Double> data) {
        // Create Histogram
        Histogram histogram = new Histogram(data, 20, -0.1, 8.1);
        histogram.setTitle(attributeName + " Distribution");
        histogram.setXAxisTitle(attributeName);
        histogram.setYAxisTitle("Frequency");

        // Customize the histogram
        histogram.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        histogram.getStyler().setLegendVisible(true);
        histogram.getStyler().setLegendBorderColor(Color.BLACK);
        histogram.getStyler().setAxisTickMarkLength(15);

        // Display the histogram
        new SwingWrapper<>(histogram).displayChart();
    }
}

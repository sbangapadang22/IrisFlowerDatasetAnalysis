package com.project.dataanalysis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Summary Statistics
public class Main {
    public static void main(String[] args) {
        String csvFile = "data/iris.csv"; // Path to your CSV file\\
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

            // Calculate and print summary statistics for each attribute
            printSummaryStatistics("Sepal Length", sepalLengths);
            printSummaryStatistics("Sepal Width", sepalWidths);
            printSummaryStatistics("Petal Length", petalLengths);
            printSummaryStatistics("Petal Width", petalWidths);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printSummaryStatistics(String attributeName, List<Double> data) {
        SummaryStatistics stats = new SummaryStatistics(data);
        System.out.println(attributeName + " Statistics:");
        System.out.println("Mean: " + stats.getMean());
        System.out.println("Minimum: " + stats.getMin());
        System.out.println("Maximum: " + stats.getMax());
        System.out.println("Standard Deviation: " + stats.getStandardDeviation());
        System.out.println();
    }
}

class SummaryStatistics {
    private final List<Double> data;

    public SummaryStatistics(List<Double> data) {
        this.data = data;
    }

    public double getMean() {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.size();
    }

    public double getMin() {
        return data.stream().mapToDouble(Double::doubleValue).min().orElse(Double.NaN);
    }

    public double getMax() {
        return data.stream().mapToDouble(Double::doubleValue).max().orElse(Double.NaN);
    }

    public double getStandardDeviation() {
        double mean = getMean();
        double sumOfSquares = 0;
        for (double value : data) {
            sumOfSquares += Math.pow(value - mean, 2);
        }
        return Math.sqrt(sumOfSquares / data.size());
    }
}

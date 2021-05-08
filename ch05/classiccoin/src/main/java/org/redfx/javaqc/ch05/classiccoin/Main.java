package org.redfx.javaqc.ch05.classiccoin;

import java.util.Random;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main {

    private static final int count = 1000;

    private static boolean randomBit() {
        boolean answer = new Random().nextBoolean();
        return answer;
    }

    public static void main(String[] args) {
        int results[] = TwoCoins.calculate(count);
        System.out.println("=================================");
        System.out.println("We did "+count+" experiments.");
        System.out.println("0 0 occured "+results[0]+" times.");
        System.out.println("0 1 occured "+results[1]+" times.");
        System.out.println("1 0 occured "+results[2]+" times.");
        System.out.println("1 1 occured "+results[3]+" times.");
        System.out.println("=================================");
        Platform.startup(() -> showResults(results));
    }

    private static void showResults(int[] results) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Integer> barChart = new BarChart(xAxis, yAxis);
        barChart.setData(getChartData(results));
        barChart.setTitle("Classic probability distribution");
        StackPane root = new StackPane();
        root.getChildren().add(barChart);
        Stage stage = new Stage();
        stage.setTitle("Two coins, classic case");
        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }

    private static ObservableList<XYChart.Series<String, Integer>> getChartData(int[] results) {
        ObservableList<XYChart.Series<String, Integer>> answer = FXCollections.observableArrayList();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("occurences");
        answer.add(series);
        for (int i = 0; i < results.length;i++) {
            series.getData().add(new XYChart.Data<>(getFixedBinaryString(i, (int) (Math.log(results.length)/Math.log(2))), results[i]));
        }
        return answer;
    }

    private static String getFixedBinaryString(int i, int w) {
        StringBuffer buff = new StringBuffer(Integer.toBinaryString(i));
        while (buff.length() < w) {
            buff.insert(0, "0");
        }
        return buff.toString();
    }

}

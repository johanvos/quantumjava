package org.redfx.javaqc.ch01.time;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Function<Double, Double> classic = b -> Math.exp(Math.pow(64. / 9. * b * Math.log(b) * Math.log(b), 1. / 3));
        Function<Double, Double> shor = b -> Math.pow(b, 3.);
        List<Function<Double, Double>> functions = Arrays.asList(classic, shor);
        Chart chart = plotFunction(functions, 0.000001, 20);
        Scene scene = new Scene(chart, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        Stage stage2 = new Stage();
        Chart chart2 = plotFunction(Arrays.asList(classic), 0.000001, 20);
        Scene scene2 = new Scene(chart2, 640, 480);
        scene2.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage2.setScene(scene2);
        stage2.show();
    }

    public static Chart plotFunction(List<Function<Double, Double>> functions, Number xStart, Number xEnd) {
        int div = 500;
        double x0 = xStart.doubleValue();
        double x1 = xEnd.doubleValue();
        double step = 1. / div * (x1 - x0);
        Axis<Number> xAxis = new NumberAxis(x0, x1, .1 * (x1 - x0));
        Axis<Number> yAxis = new NumberAxis();
        ObservableList<XYChart.Series<Number, Number>> series = FXCollections.observableArrayList();
        LineChart<Number, Number> chart = new LineChart(xAxis, yAxis, series);
        chart.setCreateSymbols(false);
        int i = 0;
        for (Function<Double, Double> f : functions) {
            XYChart.Series<Number, Number> mainSeries = new XYChart.Series();
            series.add(mainSeries);
            ObservableList<XYChart.Data<Number, Number>> data = FXCollections.observableArrayList();
            mainSeries.setData(data);
            for (double x = x0; x < x1; x = x + step) {
                final Number y = f.apply(x);
                data.add(new XYChart.Data<>(x, y));
            }
            mainSeries.setName(i == 0 ? "classic" : "shor");
            i++;
        }
        xAxis.setLabel("number of bits");
        yAxis.setLabel("time required to factor");
        chart.setTitle("Time Complexity");
        return chart;
    }

}

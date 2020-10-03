package org.redfx.javaqc.ch05.classiccoin;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

    private static final int count = 1000;

    @Override
    public void start(Stage stage) throws Exception {
        int results[] = TwoCoins.calculate(count);
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Integer> barChart = new BarChart(xAxis, yAxis);
        barChart.setData(getChartData(results));
        StackPane root = new StackPane();
        root.getChildren().add(barChart);
        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }

    private ObservableList<XYChart.Series<String, Integer>> getChartData(int[] results) {
        ObservableList<XYChart.Series<String, Integer>> answer = FXCollections.observableArrayList();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        answer.add(series);
        for (int i = 0; i < results.length;i++) {
            series.getData().add(new XYChart.Data<>(getFixedBinaryString(i, (int) (Math.log(results.length)/Math.log(2))), results[i]));
        }
        return answer;
    }

    private String getFixedBinaryString(int i, int w) {
        StringBuffer buff = new StringBuffer(Integer.toBinaryString(i));
        while (buff.length() < w) {
            buff.insert(0, "0");
        }
        return buff.toString();
    }
}

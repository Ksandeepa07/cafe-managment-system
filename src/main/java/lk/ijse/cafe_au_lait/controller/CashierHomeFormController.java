package lk.ijse.cafe_au_lait.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.cafe_au_lait.dto.Event;
import lk.ijse.cafe_au_lait.model.EventModel;
import lk.ijse.cafe_au_lait.model.OrderDetailModel;
import lk.ijse.cafe_au_lait.util.StageController;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CashierHomeFormController {

    private static final int UPDATE_INTERVAL = 5000; // in milliseconds
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private AnchorPane ancPane;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<?, ?> lineChart;
    private Timeline timeline;

    private int currentIndex;


    @FXML
    private Label dataLbl;

    void setPieChart() {

        try {
            ObservableList<PieChart.Data> data = OrderDetailModel.getPieChartData();
            pieChart.getData().addAll(data);
            pieChart.setTitle("Most Trending Products");
            pieChart.setStartAngle(180);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    void lineChart() {
        lineChart.setTitle("Income Chart 2023");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        XYChart.Series series = new XYChart.Series();
        series.setName("Income Chart");

        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jul", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));

        lineChart.getData().add(series);

    }

    public void eventAnimation() {
        try {
            List<String> data = EventModel.eventData();
            dataLbl.setText(data.get(currentIndex));
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                dataLbl.setText(data.get(currentIndex));
                currentIndex = (currentIndex + 1) % data.size();
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void eventLblClicked(MouseEvent event) {
        Event event1 = EventModel.searchById(dataLbl.getText());
        StageController.changeStage("/view/cashierEvent.fxml", "Event");

    }


    @FXML
    void initialize() {
        eventAnimation();
        setPieChart();
        lineChart();


    }

}

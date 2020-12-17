package ru.itis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;

public class Main extends Application {
    List<String> list = new LinkedList<>();
    int cursor = -1;

    @Override
    public void start(final Stage stage) {
        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(3);

        final WebView browser = new WebView();
        browser.setPrefHeight(1000);
        final WebEngine webEngine = browser.getEngine();

        //Load button
        Button buttonURL = new Button("Load Page");
        buttonURL.setOnAction(event -> {
            String url = textArea.getText();
            list.add(url);
            cursor++;
            webEngine.load(url);
        });

        //Previous page button
        Button buttonBack = new Button("Back");
        buttonBack.setOnAction(event -> {
            webEngine.load(list.get(cursor - 1));
            cursor--;
        });

        //Next page button
        Button buttonNext = new Button("Next");
        buttonNext.setOnAction(event -> {
            webEngine.load(list.get(cursor + 1));
            cursor++;
        });

        Label stateLabel = new Label();
        stateLabel.setTextFill(Color.GREEN);
        ProgressBar progressBar = new ProgressBar();

        // A Worker load the page
        Worker<Void> worker = webEngine.getLoadWorker();

        // Bind the progress property of ProgressBar
        // with progress property of Worker
        progressBar.progressProperty().bind(worker.progressProperty());

        FlowPane buttonFlowPane = new FlowPane();
        buttonFlowPane.setAlignment(CENTER);
        buttonFlowPane.getChildren().addAll(buttonBack, buttonURL, buttonNext);
        buttonFlowPane.setHgap(50);
        buttonFlowPane.setVgap(5);

        FlowPane barFlowPane = new FlowPane();
        barFlowPane.setAlignment(CENTER);
        barFlowPane.setOrientation(Orientation.HORIZONTAL);
        barFlowPane.setHgap(10);
        barFlowPane.getChildren().addAll(progressBar, stateLabel);

        VBox box = new VBox();
        box.setPadding(new Insets(5));
        box.setSpacing(10);
        box.getChildren().addAll(textArea, buttonFlowPane, browser, barFlowPane);

        Scene scene = new Scene(box);

        stage.setTitle("Browser");
        stage.setScene(scene);
        stage.setWidth(1280);
        stage.setHeight(720);

        stage.show();

        worker.stateProperty().addListener((observable, oldValue, newValue) -> {
            stateLabel.setText("Loading state: " + newValue.toString().toLowerCase());
            if (newValue == Worker.State.SUCCEEDED) {
                stage.setTitle(webEngine.getLocation());
                stateLabel.setText("Finish!");
            } else if (newValue == Worker.State.FAILED) {
                stage.setTitle(webEngine.getLocation());
                stateLabel.setText("Error!");
                list.remove(cursor);
                cursor--;
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

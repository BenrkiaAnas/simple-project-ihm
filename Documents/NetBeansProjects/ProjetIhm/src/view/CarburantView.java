/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import service.VehiculeTypeCarburantService;
import util.AlertUtil;

/**
 *
 * @author Admin
 */
public class CarburantView extends Application {
        AlertUtil alertUtil = new AlertUtil();

    VehiculeTypeCarburantService carburantService = new VehiculeTypeCarburantService();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Type Carburant");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 700, 500);
        // Start Element Node 
        HBox hBox = new HBox();
        Text text = new Text("Type Carburant");
        Button menu = new Button("Menu");
        Label label = new Label("Type Carburant");
        TextField textField = new TextField();
        Button button = new Button("Inserer");
        // End Element Node 
        // Start Style Element //
        root.setStyle("-fx-background-color: BEIGE; -fx-padding :  0 30px");
        root.setVgap(30);
        root.setHgap(15);
        hBox.setAlignment(Pos.CENTER);
        text.setFont(Font.font("Edwardian Script ITC", 80));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setUnderline(true);
        menu.setMinSize(100, 30);
        menu.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white; ; ");
        label.setStyle("-fx-font: normal bold 20px 'serif' ");
        textField.setMinSize(80, 30);
        textField.setPromptText("Entrer Type De Carburant");
        button.setMinSize(100, 30);
        button.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        // End Style Element //

        hBox.getChildren().add(text);
        root.add(hBox, 1, 0, 1, 1);
        root.addRow(1, menu);
        root.addRow(5, label, textField);
        root.add(button, 1, 6, 1, 1);
        primaryStage.setScene(scene);
        primaryStage.show();
        // Start The Action Part //
        menu.setOnAction((event) -> {
        });
        button.setOnAction((event) -> {
        boolean b=verifie(textField);
        if(b==true){
             String carburant = textField.getText();
            carburantService.creeCarburant(carburant);
            alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", "Type Carburant Crée Avec Succés");
            textField.clear();
            textField.setStyle("-fx-background-color: white;");
        }else{
             alertUtil.showAlert(Alert.AlertType.ERROR, "erroor", "erroorr");
             textField.clear();
             textField.setStyle("-fx-background-color: white;");
        }
           
        });
        // End The Action Part //
    }
    
     private boolean verifie(TextField field) {
        if (field.getText().isEmpty() || field.getText().equals("")) {
            field.setStyle("-fx-background-color: red;");
            return false;

        } else {
            field.setStyle("-fx-background-color: green;");
            return true;
        }
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Vehicule;
import bean.Vehiculekilometrage;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import service.VehiculeKilometrageService;
import service.VehiculeService;
import util.AlertUtil;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public class KilometrageView extends Application {

    Stage vehiculeALL;
    VehiculeKilometrageService kilometrageService = new VehiculeKilometrageService();
    VehiculeService vehiculeService = new VehiculeService();
    DateUtil dateUtil = new DateUtil();
    AlertUtil alertUtil = new AlertUtil();
    private List<Vehicule> vehicules;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        initComponents();
        initComBoBoxMarque();
    }
    // Element 
    Text text;
    Button menu;
    Label matriculeVehicl;
    ComboBox<String> matricule;
    Label kilometrage;
    TextField kilomField;
    Label dateDebut;
    TextField dateDubtK;
    Label dateFin;
    TextField dateFinK;

    private void initComponents() {
        text = new Text("Kilometrage Vihecule");
        menu = new Button("Menu");
        matriculeVehicl = new Label("Matricule Vehicule");
        matricule = new ComboBox<>();
        kilometrage = new Label("Kilometrage Vehicule");
        kilomField = new TextField();
        dateDebut = new Label("Date Debut Kilometrage");
        dateDubtK = new TextField();
        dateFin = new Label("Date Fin Kilometrage");
        dateFinK = new TextField();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Kilometrage Vehicule");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 900, 600);
        // Start Element Node 
        HBox hBox = new HBox();

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
        matriculeVehicl.setStyle("-fx-font: normal bold 20px 'serif' ");
        kilometrage.setStyle("-fx-font: normal bold 20px 'serif' ");
        dateDebut.setStyle("-fx-font: normal bold 20px 'serif' ");
        dateFin.setStyle("-fx-font: normal bold 20px 'serif' ");

        matricule.setMaxSize(500, 50);
        kilomField.setMaxSize(500, 50);
        dateDubtK.setMaxSize(500, 50);
        dateFinK.setMaxSize(500, 50);

        kilomField.setPromptText("Entrer Kilometrage");
        dateDubtK.setPromptText("DD-MM-AAAA");
        dateFinK.setPromptText("DD-MM-AAAA");

        matricule.setPromptText("--------------------------------------------SELECT------------------------------------------");
        button.setMinSize(100, 30);
        button.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        // End Style Element //

        hBox.getChildren().add(text);
        root.add(hBox, 1, 0, 1, 1);
        root.addRow(1, menu);
        root.addRow(5, matriculeVehicl, matricule);
        root.addRow(6, kilometrage, kilomField);
        root.addRow(7, dateDebut, dateDubtK);
        root.addRow(8, dateFin, dateFinK);
        root.add(button, 1, 9, 1, 1);
        primaryStage.setScene(scene);
        primaryStage.show();
        vehiculeALL = primaryStage;

        //Element Action
        menu.setOnAction((event) -> {
            Insertion insertion = new Insertion();
            vehiculeALL.close();
            try {
                insertion.start(new Stage());
            } catch (Exception ex) {
            }
        });
        button.setOnAction((event) -> {
            boolean kiometrageV = verifie(kilomField);
            boolean dateDebutV = verifie(dateDubtK);
            boolean dateFinV = verifie(dateFinK);
            if (matricule.getSelectionModel().getSelectedIndex() < 0 || kiometrageV == false || dateDebutV == false || dateFinV == false) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "il completer les informtion");
                kilomField.clear();
                dateDubtK.clear();
                dateFinK.clear();
                kilomField.setStyle("-fx-background-color: white;");
                dateDubtK.setStyle("-fx-background-color: white;");
                dateFinK.setStyle("-fx-background-color: white;");
            } else {
                Date dbut = dateUtil.parse(dateDubtK.getText());
                Date dFin = dateUtil.parse(dateFinK.getText());
                double kilo = new Double(kilomField.getText());
                Vehiculekilometrage vehiculekilometrage = kilometrageService.findKilometrageByMatricule(vehicules.get(matricule.getSelectionModel().getSelectedIndex()).getMatricule());
                if (vehiculekilometrage == null) {
                    Vehicule vehicule = vehicules.get(matricule.getSelectionModel().getSelectedIndex());
                    if (dbut.before(dFin)) {
                        Vehiculekilometrage vehiculekilometrage1 = kilometrageService.creeKilometrage(kilo, dbut, dFin, vehicules.get(matricule.getSelectionModel().getSelectedIndex()));
                        if (vehiculekilometrage1 == null) {
                            alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "eroooooorr");
                        } else {
                            alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " Kilometrage Crée Avec Succés");
                        }
                    } else {
                        alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "il faut regler les dates");
                    }
                } else {
                    alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "kilometrage existe deja ");
                }
                kilomField.clear();
                dateDubtK.clear();
                dateFinK.clear();
                kilomField.setStyle("-fx-background-color: white;");
                dateDubtK.setStyle("-fx-background-color: white;");
                dateFinK.setStyle("-fx-background-color: white;");
            }

        });
    }

    private void initComBoBoxMarque() {
        vehicules = vehiculeService.findAll();
        vehicules.forEach((m) -> {
            matricule.getItems().add(m.getMatricule());
        });
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

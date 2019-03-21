/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Vehicule;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import service.VehiculeKilometrageService;
import service.VehiculeService;
import service.VehiculeVidengeService;
import util.AlertUtil;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public final class Modification extends Application implements EventHandler<ActionEvent> {

    AlertUtil alertUtil = new AlertUtil();
    VehiculeService vehiculeService = new VehiculeService();
    VehiculeKilometrageService vehiculeKilometrageService = new VehiculeKilometrageService();
    VehiculeVidengeService videngeService = new VehiculeVidengeService();
    //VehiculeVidengeService vehiculeVidengeService = new VehiculeVidengeService();
    List<Vehicule> vehicules;
    //VehiculeVidengeHelper vv;

  
    public static void main(String[] args) {
        launch(args);
    }

      public Modification() throws Exception{
          init();
      }
    @Override
    public void init() throws Exception{
        initComponents();
        initMatriculeComBox1();
        initMatriculeComBox2();
        initMatriculeComBox3();

    }
    // Element //
    Text text;
    Button menu;
    Label matricule;
    ComboBox<String> mrtcComBox;
    Button supprime1;
    Label matricule2;
    ComboBox<String> mrtc2ComBox;
    Button supprime2;
    Label kilometLabel;
    TextField kilometrageField;
    Label dDebut;
    TextField dDebutField;
    Label dFin;
    TextField dFinField;
    Button modify;
    Label matricule3;
    ComboBox<String> mrtcComBox3;
    Button supprime3;

    private void initComponents() {
        text = new Text("Modification");
        menu = new Button("menu");
        matricule = new Label("Matricule");
        mrtcComBox = new ComboBox<>();
        supprime1 = new Button("Supprimer");
        matricule2 = new Label("Matricule");
        mrtc2ComBox = new ComboBox<>();
        supprime2 = new Button("Supprimer");
        kilometLabel = new Label("Kilometrage");
        kilometrageField = new TextField();
        dDebut = new Label("Date Debut");
        dDebutField = new TextField();
        dFin = new Label("Date Fin");
        dFinField = new TextField();
        modify = new Button("Modifier");
        matricule3 = new Label("Matricule");
        mrtcComBox3 = new ComboBox<>();
        supprime3 = new Button("Supprimer");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Recherche");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 700, 650);
        GridPane gridPane1 = new GridPane();
        GridPane gridPane2 = new GridPane();
        GridPane gridPane3 = new GridPane();
        GridPane gridPane4 = new GridPane();
        TabPane tabpane = new TabPane();
        Tab vehicule = new Tab();
        Tab kilometrage = new Tab();
        Tab vidange = new Tab();
        HBox hBox = new HBox();
        root.setStyle("-fx-background-color: BEIGE; -fx-padding :  0 30px");
        gridPane2.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-padding :  0 30px");
        gridPane3.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-padding :  0 30px");
        gridPane4.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-padding :  0 30px");
        root.setVgap(30);
        root.setHgap(15);
        gridPane2.setVgap(30);
        gridPane2.setHgap(15);
        gridPane3.setVgap(30);
        gridPane3.setHgap(15);
        gridPane4.setVgap(30);
        gridPane4.setHgap(15);
        hBox.setAlignment(Pos.CENTER);
        text.setFont(Font.font("Edwardian Script ITC", 80));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setUnderline(true);
        menu.setMinSize(100, 30);
        menu.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white; ; ");
        matricule.setStyle("-fx-font: normal bold 20px 'serif' ");
        matricule2.setStyle("-fx-font: normal bold 20px 'serif' ");
        kilometLabel.setStyle("-fx-font: normal bold 20px 'serif' ");
        dDebut.setStyle("-fx-font: normal bold 20px 'serif' ");
        dFin.setStyle("-fx-font: normal bold 20px 'serif' ");
        matricule3.setStyle("-fx-font: normal bold 20px 'serif' ");
        kilometrageField.setMaxSize(300, 50);
        kilometrageField.setPromptText("Entrer Kilometrage");
        dDebutField.setMaxSize(300, 50);
        dDebutField.setPromptText("Entrer Date Debut");
        dFinField.setMaxSize(300, 50);

        dFinField.setPromptText("Entrer Date Fin");
        mrtcComBox.setMaxSize(300, 50);
        mrtc2ComBox.setMaxSize(300, 50);
        mrtcComBox3.setMaxSize(300, 50);
        mrtcComBox.setPromptText("-----------------------SELECT----------------------");
        mrtc2ComBox.setPromptText("-----------------------SELECT----------------------");
        mrtcComBox3.setPromptText("-----------------------SELECT----------------------");
        dDebutField.setPromptText("DD-MM-AAAA");
        dFinField.setPromptText("DD-MM-AAAA");
        supprime1.setMinSize(100, 30);
        supprime1.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        supprime2.setMinSize(100, 30);
        supprime2.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        supprime3.setMinSize(100, 30);
        supprime3.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        modify.setMinSize(100, 30);
        modify.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        tabpane.setMinSize(600, 400);
        vehicule.setText("Vehicule");
        kilometrage.setText("Kilometrage");
        vidange.setText("Vidange");
        // End Element Node
        hBox.getChildren().add(text);

        root.addRow(0, gridPane1);

        gridPane1.add(hBox, 1, 0, 1, 1);
        gridPane1.addRow(1, menu);
        gridPane2.addRow(1, matricule, mrtcComBox);
        gridPane2.add(supprime1, 1, 2, 1, 1);
        gridPane3.addRow(1, matricule2, mrtc2ComBox, supprime2);
        gridPane3.addRow(2, kilometLabel, kilometrageField);
        gridPane3.addRow(3, dDebut, dDebutField);
        gridPane3.addRow(4, dFin, dFinField);
        gridPane3.add(modify, 1, 5, 1, 1);
        gridPane4.addRow(1, matricule3, mrtcComBox3);
        gridPane4.add(supprime3, 1, 2, 1, 1);
        vehicule.setContent(gridPane2);
        vidange.setContent(gridPane4);
        kilometrage.setContent(gridPane3);
        tabpane.getTabs().add(vehicule);
        tabpane.getTabs().add(kilometrage);
        tabpane.getTabs().add(vidange);
        root.addRow(1, tabpane);

        // Element Action
        supprime1.setOnAction((ActionEvent event) -> {
            if (mrtcComBox.getSelectionModel().getSelectedIndex() < 0) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "Error", "il faut selectionner une matricule");
            } else {
                String matricule = vehicules.get(mrtcComBox.getSelectionModel().getSelectedIndex()).getMatricule();
                int res = vehiculeService.deleteVehiculeByMatricule(matricule);
                if (res == 1) {
                    alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " vehicule supprimer avec succee");
                } else {
                    alertUtil.showAlert(Alert.AlertType.ERROR, "Error", "Error  Supprimé Vehicule ");
                }
            }

        });
        supprime2.setOnAction((ActionEvent event) -> {
            if (mrtc2ComBox.getSelectionModel().getSelectedIndex() < 0) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "Error", "il faut selectionner une matricule");
            } else {
                Vehicule vehicule55 = vehicules.get(mrtc2ComBox.getSelectionModel().getSelectedIndex());
                int res = vehiculeKilometrageService.deleteKilometrageByMatricul(vehicule55.getMatricule());
                if (res > 0) {
                    alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " Kilometrage Supprimé Avec Succés");
                } else {
                    alertUtil.showAlert(Alert.AlertType.ERROR, "Error", "Error  Supprimé Kilometrage ");
                }
            }

        });

        supprime3.setOnAction((ActionEvent event) -> {
            if (mrtcComBox3.getSelectionModel().getSelectedIndex() < 0) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "Error", "il faut selectionner une matricule");
            } else {
                Vehicule vehiculeR = vehicules.get(mrtcComBox3.getSelectionModel().getSelectedIndex());
                int i = videngeService.supprimerVidengeByMatricule(vehiculeR.getMatricule());

                if (i == 1) {
                    alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " videngee Supprimé Avec Succés");
                } else if (i == -1) {
                    alertUtil.showAlert(Alert.AlertType.ERROR, "Error", "la vehicule n'a pas de videnge ");
                }
            }

        });

        modify.setOnAction((ActionEvent event) -> {
            boolean dateDebuV = verifie(dDebutField);
            boolean dateFinV = verifie(dFinField);
            boolean kilometrageV = verifie(kilometrageField);
            if (mrtc2ComBox.getSelectionModel().getSelectedIndex() < 0 || dateDebuV == false || dateFinV == false || kilometrageV == false) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "Error", "Il faut saisir toutes les informations");
                dDebutField.clear();
                dFinField.clear();
                kilometrageField.clear();
                dDebutField.setStyle("-fx-background-color: white;");
                dFinField.setStyle("-fx-background-color: white;");
                kilometrageField.setStyle("-fx-background-color: white;");

            } else {
                DateUtil dateUtil = new DateUtil();
                Date datDebut = dateUtil.parse(dDebutField.getText());
                Date datFin = dateUtil.parse(dFinField.getText());
                String kil = kilometrageField.getText();

                int i = vehiculeKilometrageService.modifierKilometrageByMatricule(new Double(kilometrageField.getText()), datDebut, datFin, mrtc2ComBox.getSelectionModel().getSelectedItem());
                if (datDebut.before(datFin)) {
                    if (i < 0) {
                        alertUtil.showAlert(Alert.AlertType.ERROR, "Error", "la vehicule n'a pas de kilometrage à modifier");

                    } else {
                        alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", "LE KILOMETRAGE EST MODIFIER AVAEC SUCCEE");

                    }

                } else {
                    alertUtil.showAlert(Alert.AlertType.ERROR, "Error", "il faut regler les dates");

                }
                dDebutField.clear();
                dFinField.clear();
                kilometrageField.clear();
                dDebutField.setStyle("-fx-background-color: white;");
                dFinField.setStyle("-fx-background-color: white;");
                kilometrageField.setStyle("-fx-background-color: white;");
            }

        });

        primaryStage.setScene(scene);
        primaryStage.show();
        //ACTION
         menu.setOnAction((event) -> {
            primaryStage.close();
            try {
                Menu menu1  = new Menu();
                menu1.start(new Stage());

            } catch (Exception ex) {
                System.out.println("erore" + ex);
            }
        });
    }

    private void initMatriculeComBox1() {
        vehicules = vehiculeService.findAll();
        vehicules.forEach((veh1) -> {
            mrtcComBox.getItems().add(veh1.getMatricule());
        });
    }

    private void initMatriculeComBox2() {
        vehicules = vehiculeService.findAll();
        vehicules.forEach((veh2) -> {
            mrtc2ComBox.getItems().add(veh2.getMatricule());
        });
    }

    private void initMatriculeComBox3() {
        vehicules = vehiculeService.findAll();
        vehicules.forEach((veh3) -> {
            mrtcComBox3.getItems().add(veh3.getMatricule());
        });
    }

    @Override
    public void handle(ActionEvent event) {
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

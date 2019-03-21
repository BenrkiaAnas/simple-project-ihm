/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Vehicule;
import bean.VehiculeTypeCarburant;
import bean.VehiculeMarque;
import bean.VehiculeModele;
import helper.vehiculeHelper;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import service.VehiculeMarqueServive;
import service.VehiculeModelService;
import service.VehiculeService;
import service.VehiculeTypeCarburantService;
import util.AlertUtil;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public final class VehiculeView extends Application implements EventHandler<ActionEvent> {

    Stage vehiculeALL;
    AlertUtil alertUtil = new AlertUtil();
    DateUtil dateUtil = new DateUtil();

    VehiculeModelService modelService = new VehiculeModelService();
    VehiculeMarqueServive marqueServive = new VehiculeMarqueServive();
    VehiculeTypeCarburantService carburantService = new VehiculeTypeCarburantService();
    VehiculeService vehiculeService = new VehiculeService();
    private List<VehiculeModele> models;
    private List<VehiculeMarque> marques;
    private List<VehiculeTypeCarburant> carburants;
    List<Vehicule> vehicules;
    vehiculeHelper helper;
   

    public static void main(String[] args) {
        launch(args);
    }
     public VehiculeView() throws Exception{
         init();
     }

    @Override
    public void init() throws Exception{
        initComponents();
        initComBoBoxMarque();
        initComboBoxModel();
        initComboBoxCarburant();
        initHelper();
    }
    // Element //
    Text text;
    Button menu;
    Label matricules;
    TextField textFieldMtr;
    Label dateAchat;
    TextField datAchatVh;
    Label puissance;
    TextField textPuissance;
    Label poids;
    TextField poidVh;
    Label nmbrPlace;
    TextField nbrPlaceVh;
    Label marque;
    ComboBox<String> marqueVh;
    Label model;
    ComboBox<String> modelVh;
    Label dateModel;

    Label carburant;
    ComboBox<String> carburantVh;
    Button button;

    TableView table;

    private void initComponents() {
        text = new Text("Vihecule");
        menu = new Button("Menu");
        matricules = new Label("Matricule");
        textFieldMtr = new TextField();
        dateAchat = new Label("Date Achat");
        datAchatVh = new TextField();
        puissance = new Label("Puissance");
        textPuissance = new TextField();
        poids = new Label("Poids");
        poidVh = new TextField();
        nmbrPlace = new Label("Nombre De Place");
        nbrPlaceVh = new TextField();
        nmbrPlace = new Label("Nombre De Place");
        nbrPlaceVh = new TextField();
        marque = new Label("Marque");
        marqueVh = new ComboBox<>();
        model = new Label("Model");
        modelVh = new ComboBox<>();
        dateModel = new Label("Date De Model");

        carburant = new Label("Carburant");
        carburantVh = new ComboBox<>();
        button = new Button("Inserer");
        table = new TableView();
    }

    public void customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / view.getColumns().size()));
            });
        }

        AtomicLong hight = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Vehicule");
        GridPane root1 = new GridPane();
        GridPane root = new GridPane();

        root.addColumn(0, root1);
        root.addColumn(1, table);

        table.setColumnResizePolicy((param) -> true);
        Platform.runLater(() -> customResize(table));

        table.setPrefWidth(600);
        table.setMaxHeight(400);

        Scene scene = new Scene(root, 1100, 650);
        HBox hBox = new HBox();

        // Start Style Element //
        root.setStyle("-fx-background-color: BEIGE; -fx-padding :  0 30px");
        root.setHgap(20);
        root1.setVgap(20);
        root1.setHgap(15);
        hBox.setAlignment(Pos.CENTER);
        text.setFont(Font.font("Edwardian Script ITC", 80));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setUnderline(true);
        menu.setMinSize(100, 30);
        menu.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white; ; ");
        matricules.setStyle("-fx-font: normal bold 20px 'serif' ");
        dateAchat.setStyle("-fx-font: normal bold 20px 'serif' ");
        puissance.setStyle("-fx-font: normal bold 20px 'serif' ");
        poids.setStyle("-fx-font: normal bold 20px 'serif' ");
        nmbrPlace.setStyle("-fx-font: normal bold 20px 'serif' ");
        marque.setStyle("-fx-font: normal bold 20px 'serif' ");
        model.setStyle("-fx-font: normal bold 20px 'serif' ");
        dateModel.setStyle("-fx-font: normal bold 20px 'serif' ");
        carburant.setStyle("-fx-font: normal bold 20px 'serif' ");
        textFieldMtr.setPromptText("Entrer Matricule");
        textPuissance.setPromptText("Entrer Puissance");
        poidVh.setPromptText("Entrer Poids");
        nbrPlaceVh.setPromptText("Entrer Nombre De Place");
        marqueVh.setMaxSize(400, 50);
        modelVh.setMaxSize(400, 50);
        carburantVh.setMaxSize(400, 50);

        datAchatVh.setMaxSize(400, 50);
        marqueVh.setPromptText("--------------------------------SELECT-------------------------");
        modelVh.setPromptText("--------------------------------SELECT-------------------------");
        carburantVh.setPromptText("--------------------------------SELECT-------------------------");
        datAchatVh.setPromptText("DD-MM-AAAA");

        button.setMinSize(100, 30);
        button.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        // End Element
        hBox.getChildren().add(text);
        root1.add(hBox, 1, 0, 1, 1);
        root1.addRow(1, menu);
        root1.addRow(3, matricules, textFieldMtr);
        root1.addRow(4, dateAchat, datAchatVh);
        root1.addRow(5, puissance, textPuissance);
        root1.addRow(6, poids, poidVh);
        root1.addRow(7, nmbrPlace, nbrPlaceVh);
        root1.addRow(8, marque, marqueVh);
        root1.addRow(9, model, modelVh);

        root1.addRow(10, carburant, carburantVh);
        root1.add(button, 1, 12, 1, 1);
        primaryStage.setScene(scene);
        primaryStage.show();
        vehiculeALL = primaryStage;
        // Element Action
        menu.setOnAction((event) -> {
            Insertion insertion = new Insertion();
            vehiculeALL.close();
            try {
                insertion.start(new Stage());
            } catch (Exception ex) {
            }
        });

        button.setOnAction((event) -> {
            boolean mrtcV = verifie(textFieldMtr);
            boolean dateAchatV = verifie(datAchatVh);
            boolean puissV = verifie(textPuissance);
            boolean poidV = verifie(poidVh);
            boolean nbrPlaceV = verifie(nbrPlaceVh);
            if (carburantVh.getSelectionModel().getSelectedIndex() < 0 || modelVh.getSelectionModel().getSelectedIndex() < 0 || carburantVh.getSelectionModel().getSelectedIndex()<0 || mrtcV == false || dateAchatV == false || puissV == false || poidV == false || nbrPlaceV == false) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "il saisire toutes les informations");
                textFieldMtr.clear();
                datAchatVh.clear();
                textPuissance.clear();
                poidVh.clear();
                nbrPlaceVh.clear();
                textFieldMtr.setStyle("-fx-background-color: white;");
                datAchatVh.setStyle("-fx-background-color: white;");
                textPuissance.setStyle("-fx-background-color: white;");
                poidVh.setStyle("-fx-background-color: white;");
                nbrPlaceVh.setStyle("-fx-background-color: white;");

            } else {
                String mrtc = textFieldMtr.getText();
                Date dateAchat33 = dateUtil.parse(datAchatVh.getText());
                String puiss = textPuissance.getText();
                String poid = poidVh.getText();
                String nbrPlace = nbrPlaceVh.getText();

                VehiculeTypeCarburant carburant = carburants.get(carburantVh.getSelectionModel().getSelectedIndex());
                VehiculeModele modele = models.get(modelVh.getSelectionModel().getSelectedIndex());
                System.out.println(model);
                System.out.println(carburant);
                //   vehiculeService.insererVheicule(mrtc, dateAchat33, new Long(puiss),new Long(poid), new Integer(nbrPlace),carburant,modele );
                bean.Vehicule v = new bean.Vehicule(mrtc, dateAchat33, new Long(puiss), new Long(poid), new Integer(nbrPlace), carburant, modele);
                vehiculeService.create(v);
                helper.create(v);
                alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", "Vehicule crée avec succés");
                 textFieldMtr.clear();
                datAchatVh.clear();
                textPuissance.clear();
                poidVh.clear();
                nbrPlaceVh.clear();
                textFieldMtr.setStyle("-fx-background-color: white;");
                datAchatVh.setStyle("-fx-background-color: white;");
                textPuissance.setStyle("-fx-background-color: white;");
                poidVh.setStyle("-fx-background-color: white;");
                nbrPlaceVh.setStyle("-fx-background-color: white;");

            }

        });

    }

//mrtc, dateAchat33, new Long(puiss), new Long(poid),new Integer(nbrPlace), carburants.get(carburantVh.getSelectionModel().getSelectedIndex()), models.get(modelVh.getSelectionModel().getSelectedIndex()), marques.get(marqueVh.getSelectionModel().getSelectedIndex())
    private void initComBoBoxMarque() {
        marques = marqueServive.findAll();
        marques.forEach((marque2) -> {
            marqueVh.getItems().add(marque2.getMarque());
        });
        marqueVh.valueProperty().addListener((e) -> initComboBoxModel());

    }

    private void initComboBoxModel() {
        modelVh.getItems().clear();
        int i = marqueVh.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            models = modelService.findModelByMarque(marques.get(i).getMarque());
            models.forEach((model) -> {
                modelVh.getItems().add(model.getModele());
            });
        }
    }

    private void initComboBoxCarburant() {
        carburants = carburantService.findAll();
        carburants.forEach((carburant2) -> {
            carburantVh.getItems().add(carburant2.getType());
        });

    }

    @Override
    public void handle(ActionEvent event) {
    }

    private void initHelper() {
        vehicules = vehiculeService.findAll();
        helper = new vehiculeHelper(table, vehicules);

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Vehicule;
import bean.VehiculeMarque;
import bean.VehiculeModele;
import bean.VehiculeTypeCarburant;
import bean.VehiculeVidenge;
import bean.Vehiculekilometrage;
import helper.KilometrageHelper;
import helper.VidengeHelper;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import service.VehiculeKilometrageService;
import service.VehiculeMarqueServive;
import service.VehiculeModelService;
import service.VehiculeService;
import service.VehiculeTypeCarburantService;
import service.VehiculeVidengeService;
import util.AlertUtil;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public final class FindElement extends Application implements EventHandler<ActionEvent> {

    AlertUtil alertUtil = new AlertUtil();
    DateUtil dateUtil = new DateUtil();
    VehiculeModelService modelService = new VehiculeModelService();
    VehiculeTypeCarburantService carburantService = new VehiculeTypeCarburantService();
    VehiculeMarqueServive marqueServive = new VehiculeMarqueServive();
    VehiculeService vehiculeService = new VehiculeService();
    VehiculeKilometrageService kilometrageService = new VehiculeKilometrageService();
    VehiculeVidengeService vehiculeVidengeService = new VehiculeVidengeService();
    vehiculeHelper vehiculeHelper;
    KilometrageHelper kilometrageHelper;
    VidengeHelper videngeHelper;
    private List<VehiculeModele> models;
    private List<VehiculeTypeCarburant> carburants;
    private List<VehiculeMarque> marques;
    List<Vehicule> vehicules;
    List<Vehiculekilometrage> vehiculekilometrages;
    List<VehiculeVidenge> vehiculeVidenges;

    public static void main(String[] args) {
        launch(args);
    }
    public FindElement() throws Exception {
        init();
    }

    @Override
    public void init() throws Exception{
        initComponents();
        initModelCombobox();
        initCarburantComboBox();
        initMatriculeComboBox();
        initHelperVehicule();
        initHelperKilometrage();
        initVidengeHelper();
    }
    // Element //
    Text text;
    Button menu;
    Label rchrchModel;
    ComboBox<String> mdl;
    Label rchrchCarburant;
    ComboBox<String> crbrant;
    Button rchrch;
    Label rchKlMax;
    TextField kiloMax;
    Label rchrchMin;
    TextField kloMin;
    Button rchrch2;
    Label mtrcl;
    ComboBox<String> matrclBox;
    Label dateDebut;
    TextField dbMtrc;
    Label dateFin;
    TextField dFMtrc;
    Button rchrch3;
    TableView tableVehicule;
    TableView tableKilometrage;
    TableView tableVidenge;

    private void initComponents() {

        text = new Text("Recherche");
        menu = new Button("menu");
        rchrchModel = new Label("Recherche Par Modele");
        mdl = new ComboBox<>();
        rchrchCarburant = new Label("Recherche Par Carburant");
        crbrant = new ComboBox<>();
        rchrch = new Button("Rechercher");
        rchKlMax = new Label("Kilometrage Max");
        kiloMax = new TextField();
        rchrchMin = new Label("Kilometrage Min");
        kloMin = new TextField();
        rchrch2 = new Button("Rechercher");
        mtrcl = new Label("Matricule");
        matrclBox = new ComboBox<>();
        dateDebut = new Label("Date Debut");
        dbMtrc = new TextField();
        dateFin = new Label("Date Fin");
        dFMtrc = new TextField();
        rchrch3 = new Button("Rechercher");

        tableKilometrage = new TableView();
        tableVehicule = new TableView();
        tableVidenge = new TableView();
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
        tableVehicule.setColumnResizePolicy((param) -> true);
        Platform.runLater(() -> customResize(tableVehicule));
        tableKilometrage.setColumnResizePolicy((param) -> true);
        Platform.runLater(() -> customResize(tableKilometrage));
        tableVidenge.setColumnResizePolicy((param) -> true);
        Platform.runLater(() -> customResize(tableVidenge));
        tableKilometrage.setMinSize(530, 200);
        tableKilometrage.setMaxHeight(200);

        tableVidenge.setMinSize(530, 200);
        tableVidenge.setMaxHeight(150);

        tableVehicule.setMaxHeight(150);
        primaryStage.setTitle("Recherche");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 700, 650);

        GridPane gridPaneRoot3 = new GridPane();
        gridPaneRoot3.setVgap(20);
        gridPaneRoot3.setHgap(10);

        // Start Element Node 
        GridPane gridPaneRoot2 = new GridPane();
        gridPaneRoot2.setVgap(20);
        gridPaneRoot2.setHgap(10);
        // gridPaneRoot2.setMaxWidth(600);

        GridPane gridPaneRoot1 = new GridPane();
        gridPaneRoot1.setVgap(20);
        gridPaneRoot1.setHgap(10);

        GridPane gridPane1 = new GridPane();
        GridPane gridPane2 = new GridPane();
        GridPane gridPane3 = new GridPane();
        GridPane gridPane4 = new GridPane();

        gridPaneRoot3.addRow(0, gridPane4);
        gridPaneRoot3.addRow(1, tableVidenge);

        gridPaneRoot2.addRow(0, gridPane3);
        gridPaneRoot2.addRow(1, tableKilometrage);

        gridPaneRoot1.addRow(0, gridPane2);
        gridPaneRoot1.addRow(1, tableVehicule);
        TabPane tabpane = new TabPane();
        Tab vehicule = new Tab();
        Tab kilometrage = new Tab();
        Tab vidange = new Tab();
        HBox hBox = new HBox();
        root.setStyle("-fx-background-color: BEIGE; -fx-padding :  0 30px");
        gridPaneRoot1.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-padding :  0 30px");
        gridPaneRoot2.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-padding :  0 30px");
        gridPaneRoot3.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-padding :  0 30px");
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
        rchrchModel.setStyle("-fx-font: normal bold 20px 'serif' ");
        rchrchCarburant.setStyle("-fx-font: normal bold 20px 'serif' ");
        rchKlMax.setStyle("-fx-font: normal bold 20px 'serif' ");
        rchrchMin.setStyle("-fx-font: normal bold 20px 'serif' ");
        mtrcl.setStyle("-fx-font: normal bold 20px 'serif' ");
        dateDebut.setStyle("-fx-font: normal bold 20px 'serif' ");
        dateFin.setStyle("-fx-font: normal bold 20px 'serif' ");
        kiloMax.setMaxSize(300, 50);
        kiloMax.setPromptText("Entrer Marque");
        kloMin.setMaxSize(300, 50);
        kloMin.setPromptText("Entrer Marque");
        dbMtrc.setMaxSize(300, 50);
        dbMtrc.setPromptText("Entrer Marque");
        dFMtrc.setMaxSize(300, 50);
        dFMtrc.setPromptText("Entrer Marque");
        mdl.setMaxSize(300, 50);
        crbrant.setMaxSize(300, 50);
        matrclBox.setMaxSize(300, 50);
        mdl.setPromptText("-----------------------SELECT----------------------");
        crbrant.setPromptText("-----------------------SELECT----------------------");
        matrclBox.setPromptText("-----------SELECT--------");
        dbMtrc.setPromptText("DD-MM-AAAA");
        dFMtrc.setPromptText("DD-MM-AAAA");
        kiloMax.setPromptText("Entre Kilometrage Max");
        kloMin.setPromptText("Entre Kilometrage Min");
        rchrch.setMinSize(100, 30);
        rchrch.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        rchrch2.setMinSize(100, 30);
        rchrch2.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        rchrch3.setMinSize(100, 30);
        rchrch3.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        tabpane.setMinSize(600, 400);
        vehicule.setText("Vehicule");
        kilometrage.setText("Kilometrage");
        vidange.setText("Vidange");
        // End Element Node
        hBox.getChildren().add(text);

        root.addRow(0, gridPane1);

        gridPane1.add(hBox, 1, 0, 1, 1);
        gridPane1.addRow(1, menu);
        gridPane2.addRow(1, rchrchModel, mdl);
        gridPane2.addRow(2, rchrchCarburant, crbrant);
        gridPane2.add(rchrch, 1, 3, 1, 1);
        gridPane3.addRow(1, rchKlMax, kiloMax);
        gridPane3.addRow(2, rchrchMin, kloMin);
        gridPane3.add(rchrch2, 1, 3, 1, 1);
        gridPane4.addRow(1, mtrcl, matrclBox);
        gridPane4.addRow(2, dateDebut, dbMtrc);
        gridPane4.addRow(3, dateFin, dFMtrc);
        gridPane4.add(rchrch3, 1, 4, 1, 1);
        vehicule.setContent(gridPaneRoot1);
        vidange.setContent(gridPaneRoot3);
        kilometrage.setContent(gridPaneRoot2);
        tabpane.getTabs().add(vehicule);
        tabpane.getTabs().add(kilometrage);
        tabpane.getTabs().add(vidange);
        root.addRow(1, tabpane);

        primaryStage.setScene(scene);
        primaryStage.show();

        // Action Element 
        menu.setOnAction((event) -> {
            primaryStage.close();
            try {
                Menu menu1  = new Menu();
                menu1.start(new Stage());

            } catch (Exception ex) {
                System.out.println("erore" + ex);
            }
        });
        // 1er Recherche
        rchrch.setOnAction((ActionEvent event) -> {
            int index1 = mdl.getSelectionModel().getSelectedIndex();
            int index2 = crbrant.getSelectionModel().getSelectedIndex();
            if (index1 >= 0 && index2 < 0) {
                List<Vehicule> vehiculeSearch = vehiculeService.findVehiculeBymodele(models.get(index1).getModele());
                vehiculeHelper.setList(vehiculeSearch);
            } else if (index1 < 0 && index2 >= 0) {
                List<Vehicule> vehiculeSearch = vehiculeService.findVehiculeByCarburant(carburants.get(index2).getType());
                vehiculeHelper.setList(vehiculeSearch);
            } else if (index1 >= 0 && index2 >= 0) {
                List<Vehicule> vehiculeSearch = vehiculeService.findVehiculesbyTout(models.get(index1).getModele(), carburants.get(index2).getType());
                vehiculeHelper.setList(vehiculeSearch);
            }

        });
        // 2eme Recherche
        rchrch2.setOnAction((event) -> {
            boolean kiloMaxV = verifie(kiloMax);
            boolean kiloMinV = verifie(kloMin);
            if (kiloMaxV == false || kiloMinV == false) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "il saisire toutes les informations");
                kiloMax.clear();
                kloMin.clear();
                kiloMax.setStyle("-fx-background-color: white;");
                kloMin.setStyle("-fx-background-color: white;");
            } else {
                List<Vehiculekilometrage> kilometrageSearch = kilometrageService.findKilometrageByKilometrage(new Double(kiloMax.getText()), new Double(kloMin.getText()));
                kilometrageHelper.setList(kilometrageSearch);
                kiloMax.clear();
                kloMin.clear();
                kiloMax.setStyle("-fx-background-color: white;");
                kloMin.setStyle("-fx-background-color: white;");
            }

        });
        // 3eme Recherche 
        rchrch3.setOnAction((event) -> {
            if (matrclBox.getSelectionModel().getSelectedIndex() < 0) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "il selecionner une matricule");
            } else {
                Date dateMin = dateUtil.parse(dbMtrc.getText());
                Date dateMax = dateUtil.parse(dFMtrc.getText());

                List<VehiculeVidenge> videngeSearch = vehiculeVidengeService.searchVidengeByDate(vehicules.get(matrclBox.getSelectionModel().getSelectedIndex()).getMatricule(), dateMax, dateMin);
                videngeHelper.setList(videngeSearch);

            }

        });
    }

    private void initModelCombobox() {
        models = modelService.findAll();
        models.forEach((mdl2) -> {
            mdl.getItems().add(mdl2.getVehiculeMarque().getMarque() + " " + mdl2.getModele());
        });
    }

    private void initCarburantComboBox() {
        carburants = carburantService.findAll();
        carburants.forEach((carbut) -> {
            crbrant.getItems().add(carbut.getType());
        });
    }

    private void initMatriculeComboBox() {
        vehicules = vehiculeService.findAll();
        vehicules.forEach((mrq) -> {
            matrclBox.getItems().add(mrq.getMatricule());
        });
    }

    @Override
    public void handle(ActionEvent event) {
    }

    private void initHelperVehicule() {
        vehicules = vehiculeService.findAll();
        vehiculeHelper = new vehiculeHelper(tableVehicule, vehicules);

    }

    private void initHelperKilometrage() {
        vehiculekilometrages = kilometrageService.findAll();
        kilometrageHelper = new KilometrageHelper(tableKilometrage, vehiculekilometrages);

    }

    private void initVidengeHelper() {
        vehiculeVidenges = vehiculeVidengeService.findAll();
        videngeHelper = new VidengeHelper(tableVidenge, vehiculeVidenges);

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

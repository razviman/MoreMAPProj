package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import a4.Repo.RepoFile;
import a4.domain.ActFizica;
import a4.domain.Aectivitate;
import a4.service.Service;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class Activitati extends Application {



    @Override
    public void start(Stage stage) throws Exception {


        RepoFile repProdus = new RepoFile("src/main/java/a4/activitate.txt");
        Service service = new Service(repProdus);


        HBox mainBox = new HBox();

        HBox leftSideBox = new HBox();

        ObservableList<Aectivitate> ActivitatiList = FXCollections.observableArrayList(service.getAll());


        ListView<Aectivitate> ActivitatiListView = new ListView<>();
        ActivitatiListView.setItems(ActivitatiList);

        ActivitatiListView.setMinWidth(275);
        ActivitatiListView.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(ActivitatiListView, Priority.ALWAYS);



        leftSideBox.getChildren().add(ActivitatiListView);
        leftSideBox.setPadding(new Insets(10, 10, 10, 10));



        mainBox.getChildren().add(leftSideBox);


        VBox CenterBox = new VBox();

        GridPane labelsAndFieldsPane = new GridPane();
        labelsAndFieldsPane.setVgap(3.5);
        labelsAndFieldsPane.setHgap(3.5);

        Label dataLabel = new Label("data");
        Label pasiLabel = new Label("pasi");
        Label somnLabel = new Label("somn");
        Label listaLabel = new Label("activitate");
        Label timpLabel = new Label("timp");
        TextField dataTextField = new TextField();
        TextField pasiTextField = new TextField();
        TextField somnTextField = new TextField();
        TextField listaTextField = new TextField();
        TextField timpTextField = new TextField();

        labelsAndFieldsPane.add(dataLabel, 1, 1);
        labelsAndFieldsPane.add(dataTextField, 1, 2);
        labelsAndFieldsPane.add(pasiLabel, 1, 3);
        labelsAndFieldsPane.add(pasiTextField, 1, 4);
        labelsAndFieldsPane.add(somnLabel, 1, 5);
        labelsAndFieldsPane.add(somnTextField, 1, 6);
        labelsAndFieldsPane.add(listaLabel, 1, 7);
        labelsAndFieldsPane.add(listaTextField, 1, 8);
        labelsAndFieldsPane.add(timpLabel, 1, 9);
        labelsAndFieldsPane.add(timpTextField, 1, 10);



        CenterBox.getChildren().add(labelsAndFieldsPane);


        HBox buttonBox = new HBox();
        Button addButton = new Button("Add");
        Button findButton = new Button("Filtreaza");


        buttonBox.getChildren().add(addButton);
        buttonBox.getChildren().add(findButton);

        buttonBox.setAlignment(Pos.BASELINE_CENTER);
        buttonBox.setSpacing(10);


        CenterBox.getChildren().add(buttonBox);

        CenterBox.setSpacing(15);
        CenterBox.setPadding(new Insets(10, 10, 10, 10));

        mainBox.getChildren().add(CenterBox);

        VBox RightSideBox = new VBox();
        RightSideBox.setPadding(new Insets(10, 10, 10, 10));

        RightSideBox.setSpacing(5);


        Label searchLabel = new Label("Minimul de minute");
        TextField searchField = new TextField();
        Label search1Label = new Label("Maximul de minute");
        TextField search1Field = new TextField();


        RightSideBox.getChildren().add(searchLabel);
        RightSideBox.getChildren().add(searchField);
        RightSideBox.getChildren().add(search1Label);
        RightSideBox.getChildren().add(search1Field);

        mainBox.getChildren().add(RightSideBox);

        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try{
                    String data = dataTextField.getText();
                    int pasi = Integer.parseInt(pasiTextField.getText());
                    int somn = Integer.parseInt(somnTextField.getText());
                    String lista = listaTextField.getText();
                    int timp = Integer.parseInt(timpTextField.getText());
                    Date date;
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        date = formatter.parse(data);
                        ArrayList<ActFizica> list = new ArrayList<>();
                        ArrayList<String> descrieri = new ArrayList<>();
                        int ok = 0;

                        BufferedReader br = new BufferedReader(new FileReader("src/main/java/a4/settings.properties"));
                        String line = br.readLine();

                        while (line != null) {
                            String[] split = line.split(",");
                            String[] split2 = split[0].split("=");

                            descrieri.add(split2[1].trim());
                            for (int i = 1; i < split.length; i++) {
                                descrieri.add(split[i].trim());
                            }

                            line = br.readLine();
                        }


                        for (String de : descrieri) {
                            if (de.equals(lista)) {
                                ok = 1;
                                break;
                            }
                        }

                        if (ok == 0) {
                            throw new Exception("Nu exista activitatea");
                        }

                        ActFizica ac = new ActFizica(lista, timp);
                        list.add(ac);


                        if(service.find(date)){
                            service.update(date, pasi, somn);
                        }
                        else {
                            service.add(date, pasi, somn, list);
                        }
                        ActivitatiList.setAll(service.getAll());
                    } catch (DateTimeParseException e) {
                        System.out.println("Format de data invalida!");
                    }

                }
                catch (Exception e ){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText("A aparut o eroare: "+e.getMessage());
                    alert.show();
                }
            }
        });


        findButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{int min;
                    int max;
                    if (searchField.getText().isEmpty()) min =0;
                    else min = Integer.parseInt(searchField.getText());
                    if (search1Field.getText().isEmpty()) max =999;
                    else max = Integer.parseInt(search1Field.getText());

                    if(search1Field.getText().isEmpty() && searchField.getText().isEmpty()){
                        throw new Exception("Nu ati completat nici minimul, nici maximul!");
                    }

                    ActivitatiList.setAll(service.search(min, max));
                }
                catch (Exception e ){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cautare Esuata");
                    alert.setContentText("Motiv: " + e.getMessage());
                    alert.show();
                }
            }
        });



        Scene scene = new Scene(mainBox, 750, 400);
        stage.setTitle("Activitati");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }

}

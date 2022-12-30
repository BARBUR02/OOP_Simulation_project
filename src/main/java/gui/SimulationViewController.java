package gui;

import classes.AbstractMap;
import classes.Animal;
import classes.SimulationEngine;
import classes.Vector2d;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.SortedSet;

public class SimulationViewController {
    @FXML
    private AnchorPane gridPlace;


    @FXML
    private Label animalCountLabel;

    @FXML
    private Label grassCountLabel;
    @FXML
    private Label freeFieldsCountLabel;

    @FXML
    private Label mostPopularGonetypeLabel;

    @FXML
    private Label averageLifeLengthLabel;

    @FXML
    private Label averageEnergyLabel;
    @FXML
    private Button threadBtn;
    private GridPane simulationGrid= new GridPane();

    private SimulationEngine engine;
    private double gridHeight;
    private double gridWidth;
    private AbstractMap map;

    public void setMap(AbstractMap map) {
        this.map = map;
    }


    public void setEngine(SimulationEngine engine){
        this.engine=engine;
    }

    public void fillGridWithData(){
//        simulationGrid.getChildren().clear();
//        simulationGrid.setGridLinesVisible(false);
//        simulationGrid.getColumnConstraints().clear();
//        simulationGrid.getRowConstraints().clear();
//
//
        this.animalCountLabel.setText(""+this.map.getAnimalsCount());
        this.grassCountLabel.setText(""+this.map.getGrassCount());
        this.freeFieldsCountLabel.setText(""+this.map.getStatistics().getCurrFreeFieldsCount());
//        this.mostPopularGonetypeLabel.setText(""+this.map.getStatistics().stringMostPopularGenome()); not working
        this.averageEnergyLabel.setText(""+this.map.getStatistics().getAverageEnergy());
        this.averageLifeLengthLabel.setText(""+this.map.getStatistics().getAverageLifeSpan());
        int mapHeight = this.map.getRightUp().y+1;
        int mapWidth = this.map.getRightUp().x+1;

        double singleCellHeight = 400 / mapHeight;
        double singleCellWidth = 400 / mapWidth;
//
//        RowConstraints rc = new RowConstraints(singleCellHeight);
//        for (int i = 0; i < mapHeight; i++) {
//            simulationGrid.getRowConstraints().add(rc);
//        }
//
//        ColumnConstraints cc = new ColumnConstraints(singleCellWidth);
//        for (int i = 0; i < mapWidth; i++) {
//            simulationGrid.getColumnConstraints().add(cc);
//        }
//        simulationGrid.setGridLinesVisible(true);
//


//        System.out.println("Mapa poczatkowa: ");
//        System.out.println(this.map);
//        System.out.println("||| We got num of Animals: "+this.map.getAnimalsCount());
//        System.out.println("||| We got num of Bushes: "+this.map.getGrassCount()+"\n\n");
        for (int i=0;i<mapWidth;i++){
            for ( int j=0; j<mapHeight;j++){
                if (this.map.grassAt(new Vector2d(i,j)) ==null){
                    Rectangle rect =new Rectangle(singleCellWidth,singleCellHeight);
                    rect.setStroke(Color.GREENYELLOW);
                    rect.setFill(Color.GREENYELLOW);
                    simulationGrid.add(rect,i,mapHeight-j-1);
                    GridPane.setHalignment(rect, HPos.CENTER);
                }
                else {
//                    System.out.println("Trawa na pozycji : "+new Vector2d(i,j)+" Indeksy :"
//                            +i+" "+ j);
                    Rectangle rect =new Rectangle(singleCellWidth,singleCellHeight);
                    rect.setStroke(Color.GREEN);
                    rect.setFill(Color.GREEN);
                    simulationGrid.add(rect,i,mapHeight-j-1);
                    GridPane.setHalignment(rect, HPos.CENTER);
                }
                if (this.map.animalsAt(new Vector2d(i,j))!= null ){
//                    System.out.println("Zwierze na pozycji : "+new Vector2d(i,j)+" Indeksy :"
//                    +i+" "+ j);
//                    Label label=new Label("zw");
                    Circle circle = new Circle(Math.min(singleCellHeight,singleCellWidth)/2);
                    SortedSet<Animal> currEnergy=  this.map.animalsAt(new Vector2d(i,j));
                    int energy= currEnergy.first().getEnergy();
                    int threshold = currEnergy.first().getHealthyThreshold();
                    int numOfBrightening = threshold<=energy ? 0 : (int) ( ((double)energy/(double)threshold)*10) ;
//                    System.out.println((int) ((energy/threshold)*10) );
//                    System.out.println((int) ((energy/threshold)*100 ));
//                    System.out.println("Energy :"+ energy + "Threshold :" +threshold + "proportion: "+numOfBrightening );
                    Color color = switch (numOfBrightening){
                        case 0 -> Color.DARKORANGE;
                        case 1,2,3 -> Color.LIGHTYELLOW;
                        case 4,5,6 -> Color.YELLOW;
                        case 7,8 -> Color.ORANGE.brighter();
                        default -> Color.ORANGERED;
                    };
                    circle.setFill(color);
                    simulationGrid.add(circle,i,mapHeight-j-1);
                    GridPane.setHalignment(circle, HPos.CENTER);
                }
            }
        }
//        System.out.println("Gridpane Filled with data!");
    }

    public void paintGrid() {
        simulationGrid.getChildren().clear();
        simulationGrid.setGridLinesVisible(false);
        simulationGrid.getColumnConstraints().clear();
        simulationGrid.getRowConstraints().clear();

        simulationGrid=new GridPane();
        this.gridWidth=400;
        this.gridHeight=400;
        simulationGrid.setMinSize(400,400);
        simulationGrid.setPrefSize(400,400);
        simulationGrid.setMaxSize(400,400);
        int mapHeight = this.map.getRightUp().y+1;
        int mapWidth = this.map.getRightUp().x+1;
//        simulationGrid.setGridLinesVisible(false);
//        simulationGrid.setGridLinesVisible(true);

//        this.simulationGrid.maxHeight(this.simulationGrid.getHeight());
//        this.simulationGrid.maxWidth(this.simulationGrid.getWidth());

        double singleCellHeight = 400 / mapHeight;
        double singleCellWidth = 400 / mapWidth;

        RowConstraints rc = new RowConstraints(singleCellHeight);
        for (int i = 0; i < mapHeight; i++) {
            simulationGrid.getRowConstraints().add(rc);
        }

        ColumnConstraints cc = new ColumnConstraints(singleCellWidth);
        for (int i = 0; i < mapWidth; i++) {
            simulationGrid.getColumnConstraints().add(cc);
        }
        fillGridWithData();
        gridPlace.getChildren().add(this.simulationGrid);
//        simulationGrid.setGridLinesVisible(true);
    }

    public void rePaint(){
        Platform.runLater(()-> {
//            System.out.println("Jestesmy w resetowaniu grida w controllerze!");
//            this.fillGridWithData();
            this.paintGrid();
//            System.out.println(this.simulationGrid);
//            System.out.println("Zakonczylismy edytowac grida w controllerze!");
        });
    }

    public void threadAction(){
        System.out.println("Klikniety start/stop");
        System.out.println(this.threadBtn.getText());
        if (this.threadBtn.getText().equals("Stop")){
            System.out.println("STOPPING");
            this.threadBtn.setText("Start");
            engine.stopThread();
        }
        else {
            this.threadBtn.setText("Stop");
            System.out.println("Rerunning!!!");
            engine.rerunThread();
        }

    }



}



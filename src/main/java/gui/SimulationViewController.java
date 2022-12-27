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
    private GridPane simulationGrid= new GridPane();

    private SimulationEngine engine;
    private double gridHeight;
    private double gridWidth;
    private AbstractMap map;

    public void setMap(AbstractMap map) {
        this.map = map;
    }




    public void fillGridWithData(){
//        simulationGrid.getChildren().clear();
//        simulationGrid.setGridLinesVisible(false);
//        simulationGrid.getColumnConstraints().clear();
//        simulationGrid.getRowConstraints().clear();
//
//
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
//                    rect.setStroke(Color.GREEN);
                    rect.setFill(Color.GREENYELLOW);
                    simulationGrid.add(rect,i,mapHeight-j-1);
                    GridPane.setHalignment(rect, HPos.CENTER);
                }
                else {
//                    System.out.println("Trawa na pozycji : "+new Vector2d(i,j)+" Indeksy :"
//                            +i+" "+ j);
                    Rectangle rect =new Rectangle(singleCellWidth,singleCellHeight);
//                    rect.setStroke(Color.GREEN);
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
                    System.out.println("Energy :"+ energy + "Threshold :" +threshold + "proportion: "+numOfBrightening );
                    Color color = Color.ORANGERED;
                    for (int z=0;i<numOfBrightening;z++){
                        color=color.brighter();
                        if (z==3) break;
//                        System.out.println("Brither :" + color);
                    }
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

}



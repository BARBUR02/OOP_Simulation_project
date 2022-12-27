package gui;

import classes.ConfigObject;
import classes.SimulationEngine;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

//    public static void main(String[] args) {
//        launch(args);
//    }
//    @FXML
//    private TextField mapWidth;
//    @FXML
//    private TextField mapHeight;
//    @FXML
//    private TextField startingGrassCount;
//    @FXML
//    private TextField startingAnimalCount;
//    @FXML
//    private TextField grassEnergyBoost;
//    @FXML
//    private TextField dailyNewGrass;
//    @FXML
//    private TextField healthyAnimalThreshold;
//
//    @FXML
//    private TextField reproductionEnergyCost;
//    @FXML
//    private TextField animalStartingEnergy;
//    @FXML
//    private TextField minimalMutationCount;
//    @FXML
//    private TextField maximalMutationCount;
//    @FXML
//    private TextField animalGenomeLength;
//
//    @FXML
//    private Button startBtn;

    private SimulationViewController simulation;

    private MenuAppController menuController;



    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(getClass());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App.fxml")) ;
        Scene scene = new Scene(fxmlLoader.load());
        MenuAppController menuController= fxmlLoader.getController();
        primaryStage.setTitle("Simulation Manager");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.resizableProperty().set(false);
        primaryStage.show();
//        Label label = new Label("Lecymy durr!");

        menuController.getStartBtn().setOnAction(event->{
//            ConfigObject config = new ConfigObject(
//                    Integer.parseInt(mapWidth.getText()),
//                    Integer.parseInt(mapHeight.getText()),
//                    Integer.parseInt(startingGrassCount.getText()),
//                    Integer.parseInt(startingAnimalCount.getText()),
//                    Integer.parseInt(grassEnergyBoost.getText()),
//                    Integer.parseInt(dailyNewGrass.getText()),
//                    Integer.parseInt(healthyAnimalThreshold.getText()),
//                    Integer.parseInt(reproductionEnergyCost.getText()),
//                    Integer.parseInt(animalStartingEnergy.getText()),
//                    Integer.parseInt(minimalMutationCount.getText()),
//                    Integer.parseInt(maximalMutationCount.getText()),
//                    Integer.parseInt(animalGenomeLength.getText())
//            );
//            Stage newStage = new Stage();
//            SimulationApp app= new SimulationApp();
//            try {
//                app.createApp(newStage,config);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
////        primaryStage.setScene(new Scene(new Label("essa"),400,400));
//        primaryStage.setScene(scene);
//        primaryStage.centerOnScreen();
//        primaryStage.resizableProperty().set(false);
//        primaryStage.show();
            try {
                Stage newStage = new Stage();
                SimulationApp app= new SimulationApp();
                ConfigObject config= menuController.getConfig();
                app.createApp(newStage,config);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
    }}

//    public void displayDataToTerminal() throws IOException {
//        System.out.println("Map width: "+mapWidth.getText());
//        System.out.println("Map height: "+mapHeight.getText());
//        System.out.println("Starting Grass Count: "+startingGrassCount.getText());
//        System.out.println("Starting Animal Count: "+startingAnimalCount.getText());
//        System.out.println("Grass Energy Boost: "+grassEnergyBoost.getText());
//        System.out.println("Daily New Grass:  "+dailyNewGrass.getText());
//        System.out.println("Healthy Animal Threshold:  "+healthyAnimalThreshold.getText());
//        System.out.println("Reproduction Energy Cost:  "+reproductionEnergyCost.getText());
//        System.out.println("Animal Starting Energy:  "+animalStartingEnergy.getText());
//        System.out.println("Minimal Mutation Count:  "+minimalMutationCount.getText());
//        System.out.println("Maximal Mutation Count:  "+maximalMutationCount.getText());
//        System.out.println("Animal Genome Length:  "+animalGenomeLength.getText());
//
////        SimulationEngine engine = new SimulationEngine(
////                Integer.parseInt(mapHeight.getText()),Integer.parseInt(mapWidth.getText()),
////                Integer.parseInt(startingGrassCount.getText()),
////                Integer.parseInt(startingAnimalCount.getText()),
////                Integer.parseInt(grassEnergyBoost.getText()),3,
////                Integer.parseInt(animalStartingEnergy.getText()),
////                Integer.parseInt(healthyAnimalThreshold.getText()),
////                Integer.parseInt(reproductionEnergyCost.getText()),
////                Integer.parseInt(minimalMutationCount.getText()),
////                Integer.parseInt(maximalMutationCount.getText()),
////                Integer.parseInt(animalGenomeLength.getText()));
//
////                SimulationViewController simulationController = new SimulationViewController(
////                        Integer.parseInt(mapHeight.getText()),
////                        Integer.parseInt(mapWidth.getText()),
////                        Integer.parseInt(startingGrassCount.getText()),
////                        Integer.parseInt(startingAnimalCount.getText()),
////                        Integer.parseInt(grassEnergyBoost.getText()),
////                        Integer.parseInt(dailyNewGrass.getText()),
////                        Integer.parseInt(animalStartingEnergy.getText()),
////                        Integer.parseInt(healthyAnimalThreshold.getText()),
////                        Integer.parseInt(reproductionEnergyCost.getText()),
////                        Integer.parseInt(minimalMutationCount.getText()),
////                        Integer.parseInt(maximalMutationCount.getText()),
////                        Integer.parseInt(animalGenomeLength.getText())
////                );
//        ConfigObject config = new ConfigObject(
//                Integer.parseInt(mapWidth.getText()),
//                Integer.parseInt(mapHeight.getText()),
//                Integer.parseInt(startingGrassCount.getText()),
//                Integer.parseInt(startingAnimalCount.getText()),
//                Integer.parseInt(grassEnergyBoost.getText()),
//                Integer.parseInt(dailyNewGrass.getText()),
//                Integer.parseInt(healthyAnimalThreshold.getText()),
//                Integer.parseInt(reproductionEnergyCost.getText()),
//                Integer.parseInt(animalStartingEnergy.getText()),
//                Integer.parseInt(minimalMutationCount.getText()),
//                Integer.parseInt(maximalMutationCount.getText()),
//                Integer.parseInt(animalGenomeLength.getText())
//        );
//                Stage newStage = new Stage();
//                SimulationApp app= new SimulationApp();
//                app.createApp(newStage,config);
//
//    }
//
//}

package gui;

import classes.AbstractMap;
import classes.ConfigObject;
import classes.ISimulationEngine;
import classes.SimulationEngine;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;

public class SimulationApp {

    private AbstractMap map;
    private boolean runningState=false;
    private SimulationEngine engine;
    private Thread thread;
    private SimulationViewController simulationController;
    public void createApp(Stage primStage, ConfigObject config) throws IOException, InterruptedException {
//        this.engine.run();
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/SimulationView.fxml"));
        engine = new SimulationEngine(this,config);
        Scene scene = new Scene(fxmlLoader.load());
        primStage.setTitle("Simulation window");
        primStage.setResizable(false);
        primStage.setScene(scene);
//        primStage.setScene(scene);
        simulationController = fxmlLoader.getController();
        simulationController.setMap(engine.getMap());
        simulationController.setEngine(engine);

//        System.out.println("Wywolanie showinfo w SimulationApp: ");
//        simulationController.showInfo();
//        thread = new Thread(engine);
//        thread.setDaemon(true);
//        thread.start();
        startThread();
        primStage.show();
        primStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
//            thread.interrupt();
            engine.stopThread();
//            System.exit(0);
            // Save file
        });
//        Timer timer = new Timer();
//        try {
//            timer.wait(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        primStage.show();
//        simulationController.paintGrid();
//        thread.setDaemon(true);
//        thread.run();
//        thread.sleep(1000);
//        Platform.runLater(thread);

//        simulationController.paintGrid();

    }

    public void startThread(){
        thread = new Thread(engine);
//        thread.setDaemon(true);
        thread.start();
    }

    public void refreshDay(){
            simulationController.rePaint();
        }

}

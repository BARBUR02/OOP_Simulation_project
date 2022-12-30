package gui;

import classes.ConfigObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuAppController {
    @FXML
    private TextField mapWidth;
    @FXML
    private TextField mapHeight;
    @FXML
    private TextField startingGrassCount;
    @FXML
    private TextField startingAnimalCount;
    @FXML
    private TextField grassEnergyBoost;
    @FXML
    private TextField dailyNewGrass;
    @FXML
    private TextField healthyAnimalThreshold;

    @FXML
    private TextField reproductionEnergyCost;
    @FXML
    private TextField animalStartingEnergy;
    @FXML
    private TextField minimalMutationCount;
    @FXML
    private TextField maximalMutationCount;
    @FXML
    private TextField animalGenomeLength;

    @FXML
    private Button startBtn;

    @FXML
    private TextField moveDelay;
    public Button getStartBtn() {
        return startBtn;
    }

    public ConfigObject getConfig(){
        ConfigObject config = new ConfigObject(
                    Integer.parseInt(mapWidth.getText()),
                    Integer.parseInt(mapHeight.getText()),
                    Integer.parseInt(startingGrassCount.getText()),
                    Integer.parseInt(startingAnimalCount.getText()),
                    Integer.parseInt(grassEnergyBoost.getText()),
                    Integer.parseInt(dailyNewGrass.getText()),
                    Integer.parseInt(healthyAnimalThreshold.getText()),
                    Integer.parseInt(reproductionEnergyCost.getText()),
                    Integer.parseInt(animalStartingEnergy.getText()),
                    Integer.parseInt(minimalMutationCount.getText()),
                    Integer.parseInt(maximalMutationCount.getText()),
                    Integer.parseInt(animalGenomeLength.getText()),
                Integer.parseInt(moveDelay.getText())
            );
        return config;
    }
}

package classes;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ConfigObject {

    private int mapWidth;

    private int mapHeight;

    private int startingGrassCount;

    private int startingAnimalCount;

    private int grassEnergyBoost;

    private int dailyNewGrass;

    private int healthyAnimalThreshold;


    private int reproductionEnergyCost;

    private int animalStartingEnergy;

    private int minimalMutationCount;

    private int maximalMutationCount;

    private int animalGenomeLength;

    private int moveDelay;
    public ConfigObject(int mapWidth,
                        int mapHeight,
                        int startingGrassCount,
                        int startingAnimalCount,
                        int grassEnergyBoost,
                        int dailyNewGrass,
                        int healthyAnimalThreshold,
                        int reproductionEnergyCost,
                        int animalStartingEnergy,
                        int minimalMutationCount,
                        int maximalMutationCount, int animalGenomeLength, int moveDelay) {
        this.moveDelay=moveDelay;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.startingGrassCount = startingGrassCount;
        this.startingAnimalCount = startingAnimalCount;
        this.grassEnergyBoost = grassEnergyBoost;
        this.dailyNewGrass = dailyNewGrass;
        this.healthyAnimalThreshold = healthyAnimalThreshold;
        this.reproductionEnergyCost = reproductionEnergyCost;
        this.animalStartingEnergy = animalStartingEnergy;
        this.minimalMutationCount = minimalMutationCount;
        this.maximalMutationCount = maximalMutationCount;
        this.animalGenomeLength = animalGenomeLength;
    }

    public int getMoveDelay() {
        return moveDelay;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getStartingGrassCount() {
        return startingGrassCount;
    }

    public int getStartingAnimalCount() {
        return startingAnimalCount;
    }

    public int getGrassEnergyBoost() {
        return grassEnergyBoost;
    }

    public int getDailyNewGrass() {
        return dailyNewGrass;
    }

    public int getHealthyAnimalThreshold() {
        return healthyAnimalThreshold;
    }

    public int getReproductionEnergyCost() {
        return reproductionEnergyCost;
    }

    public int getAnimalStartingEnergy() {
        return animalStartingEnergy;
    }

    public int getMinimalMutationCount() {
        return minimalMutationCount;
    }

    public int getMaximalMutationCount() {
        return maximalMutationCount;
    }

    public int getAnimalGenomeLength() {
        return animalGenomeLength;
    }
}
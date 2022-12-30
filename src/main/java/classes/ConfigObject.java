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

    private boolean csvLoad;
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
                        int maximalMutationCount, int animalGenomeLength, int moveDelay, boolean
                        csvLoad) {
        this.csvLoad=csvLoad;
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
        validation();
    }

    public boolean isCsvLoad() {
        return csvLoad;
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

    public void validation(){
        if (mapHeight <= 0){
            throw new IllegalArgumentException("Illegal height");
        }
        if (mapWidth <= 0){
            throw new IllegalArgumentException("Illegal width");
        }
        if (startingAnimalCount <= 0){
            throw new IllegalArgumentException("Illegal number of animals");
        }
        if (startingGrassCount > mapWidth*mapHeight || startingGrassCount < 0){
            throw new IllegalArgumentException("Illegal number of grass");
        }
        if (dailyNewGrass < 0 || dailyNewGrass > mapHeight*mapWidth){
            throw new IllegalArgumentException("Illegal daily grass grow");
        }
        if (reproductionEnergyCost < 0){
            throw new IllegalArgumentException("Illegal reproduction energy cost");
        }
        if (animalStartingEnergy <= 0){
            throw new IllegalArgumentException("Illegal starting energy for animal");
        }
        if (minimalMutationCount < 0){
            throw new IllegalArgumentException("Illegal minimum mutation count");
        }
        if (maximalMutationCount < 0){
            throw new IllegalArgumentException("Illegal minimum mutation count");
        }
        if (maximalMutationCount < minimalMutationCount){
            throw new IllegalArgumentException("Maximum mutation count should be more than minimal one!");
        }
        if (animalGenomeLength <= 0){
            throw new IllegalArgumentException("Illegal animal genome length");
        }
        if (moveDelay<0){
            throw new IllegalArgumentException("Illegal time refresh value!");
        }
    }
}

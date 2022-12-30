package classes;

import gui.SimulationApp;
import gui.SimulationViewController;

import java.util.Timer;

public class SimulationEngine implements Runnable, ISimulationEngine {

    //simulation statistics
    private int height;
    private int width;
    private int startingGrassCount;
    private int startingAnimalCount;
    private int energyViaGrass;
    private int dailyGrassGrowth;
    private int startingAnimalEnergy;
    private int reproductionEnergyCost;
    private int minimalMutationCount;
    private int maximalMutationCount;
    private int animalGenomeLentgh;

    private int moveDelay;
    private int healthyAnimalThreshhold;
    private AbstractMap map;

    private boolean running = false;
    private SimulationApp observer;

    private Statistics statistics;

    public AbstractMap getMap() {
        return map;
    }

    public SimulationEngine(SimulationApp observer, ConfigObject config) {
        this.observer=observer;
        this.startingGrassCount = config.getStartingGrassCount();
        this.startingAnimalCount = config.getStartingAnimalCount();
        this.energyViaGrass = config.getGrassEnergyBoost();
        this.dailyGrassGrowth = config.getDailyNewGrass();
        this.startingAnimalEnergy = config.getAnimalStartingEnergy();
        this.healthyAnimalThreshhold=config.getHealthyAnimalThreshold();
        this.reproductionEnergyCost = config.getReproductionEnergyCost();
        this.minimalMutationCount = config.getMinimalMutationCount();
        this.maximalMutationCount = config.getMaximalMutationCount();
        this.animalGenomeLentgh = config.getAnimalGenomeLength();
        this.moveDelay=config.getMoveDelay();
        this.running=true;
        this.width = config.getMapWidth();
        this.height = config.getMapHeight();
        this.statistics = new Statistics(animalGenomeLentgh);
        this.map = new GrassField(config.getMapWidth(), config.getMapHeight(),
                this.startingGrassCount,this.animalGenomeLentgh
        ,this.startingAnimalCount, this.startingAnimalEnergy,this.healthyAnimalThreshhold ,this.reproductionEnergyCost,
                this.energyViaGrass,this.dailyGrassGrowth, this.minimalMutationCount,this.maximalMutationCount,this.statistics);
    }



    // controls each life cycle of map (each day)
    @Override
    public void run(){
//        int i=0;
        while(this.running) {

            System.out.println("Mamy dzien: "+this.map.getDay());
            this.map.manageDead(statistics);
            this.map.clearDayInfo();
            this.map.manageAnimalMoves();
            this.map.manageEating();
            this.map.manageReproduction();
            this.map.renderGrass(dailyGrassGrowth);
            this.map.day++;

            this.statistics.updateCurrAnimalCount(map.animals.size());
            this.statistics.updateCurrGrassCount(map.getGrassCount());
            this.statistics.updateCurrFreeFieldsCount(this.height*this.width-map.getGrassCount());
            this.statistics.updateMostPopularGenome(map.animals);
            this.statistics.updateAverageEnergy(map.animals);
            this.statistics.updateCSVFile();

            try{
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e){
//                Thread.dumpStack();
//                Thread.interrupted();
//                Thread.currentThread().interrupt();
                System.out.println("We interrupted the thread!");
                e.printStackTrace();
            }

            this.observer.refreshDay();

//
//            try {
//                timer.wait(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }


//            System.out.println(this.map);
//            System.out.println("||| We got num of Animals: "+this.map.getAnimalsCount());
//            System.out.println("||| We got num of Bushes: "+this.map.getGrassCount()+"\n\n");
//            if (i>=28) System.out.println(this.map);
//            this.map.setDay(this.map.getDay()+1);
//            i++;
        }


    }
    public void stopThread(){
        running=false;
    }

    public void rerunThread(){
        running=true;
        this.observer.startThread();
    }


}

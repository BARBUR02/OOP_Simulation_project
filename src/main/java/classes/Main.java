package classes;

import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        SimulationEngine engine = new SimulationEngine(
                7,7,6,
                17,3,3,
                4,3,2,1,
                3,7);
        engine.run();
        // staring parameters
//        int height, int width, int startingGrassCount,
//        int startingAnimalCount, int energyViaGrass,
//        int dailyGrassGrowth, int startingAnimalEnergy,
//        int reproductionEnergyCost, int minimalMutationCount,
//        int maximalMutationCount, int animalGenomeLentgh
    }
}

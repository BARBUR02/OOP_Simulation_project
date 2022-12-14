package classes;

public class SimulationEngine {

    //statystki symulacji
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

    private GrassField map;

    public SimulationEngine(int height, int width, int startingGrassCount, int startingAnimalCount, int energyViaGrass, int dailyGrassGrowth, int startingAnimalEnergy, int reproductionEnergyCost, int minimalMutationCount, int maximalMutationCount, int animalGenomeLentgh) {
        this.startingGrassCount = startingGrassCount;
        this.startingAnimalCount = startingAnimalCount;
        this.energyViaGrass = energyViaGrass;
        this.dailyGrassGrowth = dailyGrassGrowth;
        this.startingAnimalEnergy = startingAnimalEnergy;
        this.reproductionEnergyCost = reproductionEnergyCost;
        this.minimalMutationCount = minimalMutationCount;
        this.maximalMutationCount = maximalMutationCount;
        this.animalGenomeLentgh = animalGenomeLentgh;
        this.map = new GrassField(width, height ,startingGrassCount);
        init(this.startingAnimalCount);
    }

    private void init(int n){
        for (int i=0;i<n;i++){

        }
    }

    // controls each life cycle of map (each day)
    public void run(){

    }
}

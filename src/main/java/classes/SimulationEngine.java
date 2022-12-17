package classes;

public class SimulationEngine implements ISimulationEngine {

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

    private IMap map;

    public SimulationEngine(int height, int width, int startingGrassCount,
                            int startingAnimalCount, int energyViaGrass,
                            int dailyGrassGrowth, int startingAnimalEnergy,
                            int reproductionEnergyCost, int minimalMutationCount,
                            int maximalMutationCount, int animalGenomeLentgh) {
        this.startingGrassCount = startingGrassCount;
        this.startingAnimalCount = startingAnimalCount;
        this.energyViaGrass = energyViaGrass;
        this.dailyGrassGrowth = dailyGrassGrowth;
        this.startingAnimalEnergy = startingAnimalEnergy;
        this.reproductionEnergyCost = reproductionEnergyCost;
        this.minimalMutationCount = minimalMutationCount;
        this.maximalMutationCount = maximalMutationCount;
        this.animalGenomeLentgh = animalGenomeLentgh;
        this.map = new GrassField(width, height ,startingGrassCount,animalGenomeLentgh
        ,startingAnimalCount, startingAnimalEnergy, reproductionEnergyCost,
                energyViaGrass,dailyGrassGrowth);
    }



    // controls each life cycle of map (each day)
    public void run(){
        for (int i=0;i<50;i++) {
            this.map.manageDead();
            this.map.clearDayInfo();
            this.map.manageAnimalMoves();
            this.map.manageEating();
            this.map.manageReproduction();
            this.map.renderGrass(dailyGrassGrowth);
            System.out.println(this.map);
            System.out.println("||| We got num of Animals: "+this.map.getAnimalsCount());
            System.out.println("||| We got num of Bushes: "+this.map.getGrassCount()+"\n\n");
            this.map.setDay(this.map.getDay()+1);
        }
    }
}

package classes;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GrassField extends AbstractMap{

    //Fields sorted by likelihood of growing grass on them
    private PriorityQueue<Vector2d> freeFields = new PriorityQueue<>(this::comp);
    //map to store deaths on every field
    private Map<Vector2d, Integer> deadsOnFields = new HashMap<>();


    public GrassField(int width, int height, int grassNum, int genomeLength, int animalNum,
                      int startingEnergy, int healthyAnimalThreshhold,int reproductionEnergy, int energyBoost, int dailyGrass,
                      int minimalMutationCount, int maximalMutationCount) {
        this.day = 0;
        this.width = width;
        this.height = height;
        this.bushEnergyBoost = energyBoost;
        this.genomeLength = genomeLength;
        this.dailyGrass = dailyGrass;
        this.startingEnergy = startingEnergy;
        this.healthyAnimalThreshhold=healthyAnimalThreshhold;
        this.reproductionEnergyThreshhold = reproductionEnergy;
        this.minimalMutationChangesNum=minimalMutationCount;
        this.maximalMutationChangesNum=maximalMutationCount;

        this.initBushes(grassNum);
        this.initFreeFields();
//        for (Vector2d pos : bushes.keySet()) {
//            System.out.println(pos);
//        }
        this.initAnimals(animalNum);
//        for (Animal animal : animals) {
//            System.out.println(animal.getPosition());
//        }
    }

    private void initFreeFields(){
        List<Vector2d> temp = new LinkedList<>();
        for (int i=0; i<=height; i++){
            for (int j=0; j<=width; j++){
                temp.add(new Vector2d(i,j));
            }
        }
        for (Vector2d vec: bushes.keySet()){
            temp.remove(vec);
        }
        Collections.shuffle(temp);
        this.freeFields.addAll(temp);
    }

    public int comp(Vector2d a,Vector2d b){
        Integer x = this.deadsOnFields.get(a);
        Integer y = this.deadsOnFields.get(b);
        if (x != null && y != null){
            return x - y;
        }
        else if (x == null && y != null){
            return -1;
        }
        else if (x != null && y == null){
            return 1;
        }
        else {
            return 0;
    }
    }

    @Override
    public void renderGrass(int bushNum) {
        deadsOnFields.forEach((key, value)
                -> System.out.print(key +" "+ value+" "));
        System.out.println();
        freeFields.forEach(System.out::println);
        for (int i = 0; i < bushNum; i++) {
            Vector2d newPos = freeFields.poll();
            if (newPos == null){
                break;
            }
            this.bushes.put(newPos, new Grass(newPos));
        }
    }

    @Override
    public void moveAtMap(Animal animal, Vector2d position) {
        Vector2d newPos;
        if (width < position.x || position.x < 0 || height < position.y || position.y < 0) {
//            we create new random position for animal and add element to our map
//            of current state positions
            newPos = randomVectorGenerator();
        } else {
            newPos = position;
        }
        SortedSet<Animal> newSet = currAnimalPos.get(newPos);
        if (newSet == null) {
            // we create Treeset from AbstractMap with custom Comparator
            newSet = this.createAnimalSet();
            currAnimalPos.put(newPos, newSet);
        }
        newSet.add(animal);
        animal.setPosition(newPos);
    }

    @Override
    public void killAnimal(Animal animal) {
        super.killAnimal(animal);
        Integer currentCount = this.deadsOnFields.get(animal.getPosition());
        if (currentCount == null) {
            this.deadsOnFields.put(animal.getPosition(),1);
        }
        else {
            currentCount++;
            deadsOnFields.remove(animal.getPosition());
            deadsOnFields.put(animal.getPosition(), currentCount);
        }
    }

    public void manageEating() {
        for (Vector2d position : currAnimalPos.keySet()) {
            // Set should never be empty due to the fact
            // We create it each time we add something,
            // and reset map state completely each time
            SortedSet<Animal> singlePositionAnimals = currAnimalPos.get(position);
            //first one is our strongest animal (due to custom sortedmap comparator)
            Animal eater = singlePositionAnimals.first();
            Grass bush = this.grassAt(position);
            if (bush != null) {
                eater.setEnergy(eater.getEnergy() + bushEnergyBoost);
                this.bushes.remove(bush.getPosition());
                this.freeFields.add(bush.getPosition());
                System.out.println("BUSH EATEN!");
            }
        }
    }
}

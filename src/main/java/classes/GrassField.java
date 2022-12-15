package classes;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GrassField extends AbstractMap {

    // Maybe we will use it in 'toksyczne trupy' task
    private Map<Vector2d, Integer> objectCount = new HashMap<>();


    public GrassField(int width, int height, int grassNum, int genomeLength, int animalNum,
                      int startingEnergy, int reproductionEnergy, int energyBoost, int dailyGrass) {
        this.day = 0;
        this.width = width;
        this.height = height;
        this.bushEnergyBoost = energyBoost;
        this.genomeLength = genomeLength;
        this.dailyGrass = dailyGrass;
        this.startingEnergy = startingEnergy;
        this.reproductionEnergyThreshhold = reproductionEnergy;
        this.initBushes(grassNum);
//        for (Vector2d pos : bushes.keySet()) {
//            System.out.println(pos);
//        }
        this.initAnimals(animalNum);
//        for (Animal animal : animals) {
//            System.out.println(animal.getPosition());
//        }
    }


    @Override
    public void renderGrass(int bushNum) {
        //narazie bez wariantu zadania (powtorzona init grass)
        for (int i = 0; i < bushNum; i++) {
            if (this.bushes.size() == width * height) {
                break;
            }
            Vector2d newPos = this.randomVectorGenerator();
            while (this.grassAt(newPos) != null) {
                newPos = this.randomVectorGenerator();
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


}

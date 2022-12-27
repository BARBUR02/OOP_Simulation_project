package classes;

import org.w3c.dom.NodeList;

import java.util.*;

public abstract class AbstractMap implements IMap {

    // simulation stats and variables for each simulation variation
    protected int day;
    protected int width;
    protected int height;
    protected int bushEnergyBoost;
    protected int dailyGrass;
    protected int startingEnergy;
    protected int reproductionEnergyThreshhold;
    protected int healthyAnimalThreshhold;
    protected int genomeLength;

    protected int minimalMutationChangesNum;

    protected int maximalMutationChangesNum;
    // list of animals in the map (at current state)
    protected LinkedList<Animal> animals = new LinkedList<>();

    // active map state (positions with animals prepared in valid order to
    // further actions)
    protected Map<Vector2d, SortedSet<Animal>> currAnimalPos = new HashMap<>();

    // map of bushes in active map state
    protected Map<Vector2d, Grass> bushes = new HashMap<>();


    @Override
    public void initBushes(int bushNumber) {
        for (int i = 0; i < bushNumber; i++) {
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
    public void initAnimals(int animalNum) {
        for (int i = 0; i < animalNum; i++) {
            Vector2d newPos = this.randomVectorGenerator();
//            System.out.println("Initing animals with starting energu of :"+this.startingEnergy);
            this.animals.add(new Animal(this.genomeLength, this, this.startingEnergy, newPos, this.healthyAnimalThreshhold));
        }
    }


    // methods specific for certain variation (need to be implemented
    // in extending class)
    @Override
    public abstract void renderGrass(int bushNum);

    @Override
    public abstract void moveAtMap(Animal animal, Vector2d position);


    // methods that remain same for each project view
    // described in project requirements
    @Override
    public Vector2d getRightUp() {
        return new Vector2d(this.width, this.height);
    }

    @Override
    public Vector2d getLeftBottom() {
        return new Vector2d(0, 0);
    }

    @Override
    public SortedSet<Animal> animalsAt(Vector2d position) {
        return this.currAnimalPos.get(position);
    }

    @Override
    public Grass grassAt(Vector2d position) {
        return bushes.get(position);
    }

    @Override
    public void place(Animal animal, Vector2d position) {
        if (this.currAnimalPos.get(position) == null) {
            this.currAnimalPos.put(position, createAnimalSet());
        }
        this.currAnimalPos.get(position).add(animal);
        this.animals.add(animal);
    }



    @Override
    public void clearDayInfo() {
        this.currAnimalPos = new HashMap<>();
    }

    // some getters and setters of properties (maybe important for futher statistics
    // development)

    @Override
    public int getAnimalsCount() {
        return animals.size();
    }

    @Override
    public int getGrassCount() {
        return bushes.size();
    }
    @Override
    public int getDay() {
        return this.day;
    }

    @Override
    public void setDay(int day) {
        this.day = day;
    }


    // functions that control the flow of animation ( each describes
    // one required simulation step (day-phase))
    @Override
    public void manageAnimalMoves() {
        for (Animal animal : this.animals) {
            animal.move();
//            System.out.println("Energia zwierzaka : " + animal.getEnergy());
        }
    }

    public void killAnimal(Animal animal) {
        animals.remove(animal);
        SortedSet<Animal> anim = currAnimalPos.get(animal.getPosition());
        anim.remove(animal);
    }

    @Override
    public void manageDead() {
        LinkedList<Animal> toRemove = new LinkedList<>();
        for (Animal animal : this.animals) {
            if (animal.getEnergy() <= 0) {
                toRemove.add(animal);
            }
        }

        for (Animal animal : toRemove) {
            killAnimal(animal);
//            System.out.println("ANIMAL DEAD!");
        }
    }

    @Override
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
//                System.out.println("BUSH EATEN!");
            }
        }
    }
    @Override
    public void manageReproduction() {
        for (Vector2d position : currAnimalPos.keySet()) {
            SortedSet<Animal> singlePositionAnimals = currAnimalPos.get(position);
            // 1 case: 1 animal on position => no one to breed
            if (singlePositionAnimals.size() == 1) {
                continue;
            }
            // 2 case: at least 2 animals on position => we check wheter they can breed
            // and if so manage breeding
            ArrayList<Animal> animalsArr = new ArrayList<>(singlePositionAnimals);
            Animal strongerAnimal = animalsArr.get(0);
            Animal weakerAnimal = animalsArr.get(1);
            // unnecessary to check strongest then
            if (weakerAnimal.getEnergy() < this.healthyAnimalThreshhold) {
                continue;
            }
            int[] strongerGenome = strongerAnimal.getGenome();
            int[] weakerGenome = weakerAnimal.getGenome();
            float proportion = strongerAnimal.getEnergy() / (strongerAnimal.getEnergy() + weakerAnimal.getEnergy());
            strongerAnimal.setEnergy(strongerAnimal.getEnergy() - reproductionEnergyThreshhold);
            weakerAnimal.setEnergy(weakerAnimal.getEnergy() - reproductionEnergyThreshhold);
            strongerAnimal.setChildCount(strongerAnimal.getChildCount() + 1);
            weakerAnimal.setChildCount(weakerAnimal.getChildCount() + 1);
            int[] childGenome = GenomeModifier.fuseGenoms(strongerGenome, weakerGenome, proportion);
            childGenome=GenomeModifier.mutateGenome(childGenome,minimalMutationChangesNum,maximalMutationChangesNum);
//            System.out.println("ANIMAL CREATED!");
//            this.animals.add(new Animal(childGenome, (GrassField) this, startingEnergy, position));
            this.place(new Animal(childGenome,  this, 2*reproductionEnergyThreshhold, position,this.healthyAnimalThreshhold),position);
        }
    }

    // help function that is used in many methods
    protected Vector2d randomVectorGenerator() {
        return new Vector2d((int) (Math.random() * (this.width + 1)), (int) (Math.random() * (this.height + 1)));
    }

    // shortcut method to create sorted set with
    // comparator of belowly implemented class
    SortedSet<Animal> createAnimalSet() {
        return new TreeSet<>(new AnimalSort());
    }

    class AnimalSort implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Animal animal1 = (Animal) o1;
            Animal animal2 = (Animal) o2;
            if (animal1.getEnergy() > animal2.getEnergy()) {
                return -1;
            } else if (animal1.getEnergy() < animal2.getEnergy()) {
                return 1;
            } else {
                if (animal1.getAge() > animal2.getAge()) {
                    return -1;
                } else if (animal1.getAge() < animal2.getAge()) {
                    return 1;
                } else {
                    if (animal1.getChildCount() > animal2.getChildCount()) {
                        return -1;
                    }
                    if (animal1.getChildCount() < animal2.getChildCount()) {
                        return 1;
                    } else {
                        return Math.random() < 0.5 ? 1 : -1;
                    }
                }
            }
        }
    }


    @Override
    public String toString() {
        return new MapVisualizer(this).draw(getLeftBottom(), getRightUp());
    }
}

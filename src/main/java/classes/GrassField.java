package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GrassField {
    private int bushNumber;

    private int animalNumber;
    private int width;
    private int height;
    private Map<Vector2d,Grass> bushes = new HashMap<>();

//    Now we have list of animas for each coordinates
    private Map<Vector2d, ArrayList <Animal>> animals = new HashMap<>();

    public Map <Vector2d,ArrayList <Animal>>  getAnimals() {
        return this.animals;
    }

    public Vector2d getLeftBottom() {
        return new Vector2d(0,0);
    }

    public Vector2d getRightUp() {
        return new Vector2d(width,height);
    }

    public GrassField(int width ,int height,int bushNumber) {
        this.bushNumber = bushNumber;
        this.width=width;
        this.height=height;
        initBushes(bushNumber);
    }


    public void initBushes(int bushNumber) {
        if (bushNumber == 0) return;
        this.bushes = new HashMap<Vector2d,Grass>();
        for (int i = 0; i < bushNumber; i++) {
            Vector2d position=getNewPosition();
            Grass grass=new Grass(position);
            bushes.put(position,grass);
        }
    }

    public Vector2d getNewPosition() {
        int[] coordinates = randomCoordinates(this.bushNumber);
        Vector2d candidateVector = new Vector2d(coordinates[0], coordinates[1]);
        while (isOccupied(candidateVector)) {
            coordinates = randomCoordinates(bushNumber);
            candidateVector = new Vector2d(coordinates[0], coordinates[1]);
        }
//        bushes.add(new Grass(candidateVector));
        return candidateVector;
    }


    public int[] randomCoordinates(int n) {
        Random generator = new Random();
        return new int[]{generator.nextInt((int) Math.sqrt(n * 10) + 1),
                generator.nextInt((int) Math.sqrt(n * 10) + 1)};
    }

    public Map<Vector2d,Grass> getBushes() {
        return bushes;
    }

    public void moveGrass(Vector2d position) {
        Grass toChange;
        if (bushes.get(position) != null ) {
            toChange = bushes.get(position);
            Vector2d vector=getNewPosition();
            toChange.setPosition(vector);
            bushes.remove(position);
            bushes.put(vector,toChange);
        }

    }

    // We have to change this
    public boolean canMoveTo(Vector2d position){
        Object mapElement = objectAt(position);
        if ((mapElement instanceof Animal[])) return false;
        if ((mapElement instanceof Grass)) {
//            System.out.println("PRZED:\n-----\n"+this);
//            System.out.println("Pozycja przed: " + position);
//            animals.remove(animal.getPosition());
            Vector2d vector=getNewPosition();
            bushes.remove(position);
            Grass grassElement = (Grass) mapElement;
            grassElement.setPosition(vector);
            bushes.put(vector,grassElement);

//            System.out.println("PO:\n----\n"+this);
//            System.out.println("Zmieniona pozycja: "+grassElement.getPosition());
        }
        return true;
    }



    public Object objectAt(Vector2d position) {
        if (animals.get(position)!=null){
            return animals.get(position);
        }

        if (bushes.get(position)!=null){
            return bushes.get(position);
        }

        return null;
    }





    public boolean place(Animal animal) throws IllegalArgumentException{
        if (canMoveTo(animal.getPosition())) {
            if (animals.get(animal.getPosition())==null)
//                animals.put(animal.getPosition(),animal);
                animals.get(animal.getPosition()).add(animal);
            return true;
        }
        throw new IllegalArgumentException("Bledne pole : "+animal.getPosition());
    }

    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) == null) return false;
        return true;
    }


}

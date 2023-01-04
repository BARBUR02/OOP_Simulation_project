package classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class Animal {
    //0-7 (8 wartosci co 45*)

    private Vector2d position;

//    private int orient;
    // tych bedziemy uzywac podczas rozstrzygania ktore je
    // i ktore sie rozmnaza
    private int childCount;

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public int getChildCount() {
        return childCount;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    public Integer deathDay = null;

    public int grassCount = 0;
    public int orient;
    private int energy;
    public int[] genome;
//    private int[] genome;
    private int healthyThreshold;
    private int currentGenomeIndex;

    private IMap map;

    public int[] getGenome() {
        return genome;
    }

    public Animal(){
    }

    // n is length of genome /constructing first Animals
    public Animal(int n, IMap map, int energy, Vector2d position,int healthyThreshold){
        this.genome= GenomeModifier.createGenome(n);
        this.energy=energy;
        this.position=position;
        this.orient = GenomeModifier.randomOrient();
        this.map=map;
        this.age=0;
        this.childCount=0;
        this.healthyThreshold=healthyThreshold;
    }

    public int getHealthyThreshold() {
        return healthyThreshold;
    }

    //    constructor when we have array of genes
    public Animal(int[] genes, IMap map, int energy, Vector2d position, int healthyThreshold){
        this.map = map;
        this.genome=genes;
        this.energy=energy;
        this.position=position;
        this.childCount=0;
        this.age=0;
        this.orient = GenomeModifier.randomOrient();
        this.healthyThreshold=healthyThreshold;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void  changeCurrentGenomeIndex(){
        this.currentGenomeIndex = GenomeModifier.changeGenomeIndex(genome.length);
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void setPosition(Vector2d position){ this.position=position;}

    private void rotate() {
        this.orient = (this.orient + this.genome[this.currentGenomeIndex]) % 8;
        // wariant nieco szalenstwa
        if (Math.random()<0.2){
            changeCurrentGenomeIndex();
        }
        else {
            this.currentGenomeIndex = (currentGenomeIndex + 1) % genome.length;
        }
    }

    public void move() {
        rotate();
        Vector2d wantedPos;
        switch (this.orient) {
            case 0 -> wantedPos = this.position.add(new Vector2d(0, 1));
            case 1 -> wantedPos = this.position.add(new Vector2d(1, 1));
            case 2 -> wantedPos = this.position.add(new Vector2d(1, 0));
            case 3 -> wantedPos = this.position.add(new Vector2d(1, -1));
            case 4 -> wantedPos = this.position.add(new Vector2d(0, -1));
            case 5 -> wantedPos = this.position.add(new Vector2d(-1, -1));
            case 6 -> wantedPos = this.position.add(new Vector2d(-1, 0));
            case 7 -> wantedPos = this.position.add(new Vector2d(-1, 1));
            default -> wantedPos = this.position;
        }
        map.moveAtMap(this,wantedPos);

        // each time animal moves the new day comes
        this.energy--;
        this.age+=1;
//        this.position = wantedPos;
    }
}

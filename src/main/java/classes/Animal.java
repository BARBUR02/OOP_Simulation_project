package classes;

import java.util.Vector;

public class Animal {
    //0-7 (8 wartosci co 45*)

    private Vector2d position;

    private int orient;
    private int energy;
    private int[] genome;
    private int currentGenomeIndex;

    private GrassField map;

    // n is length of genome /constructing first Animals
    public Animal(int n, GrassField map, int energy, Vector2d position){
        this.genome= GenomeModifier.createGenome(n);
        this.energy=energy;
        this.position=position;
        this.orient = GenomeModifier.randomOrient();
    }

//    constructor when we have array of genes
    public Animal(int[] genes, GrassField map, int energy, Vector2d position){
        this.genome=genes;
        this.energy=energy;
        this.position=position;
        this.orient = GenomeModifier.randomOrient();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void  changeCurrentGenomeIndex(){
        this.currentGenomeIndex = GenomeModifier.changeGenomeIndex();
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void setPosition(Vector2d position){ this.position=position;}

    private void rotate() {
        this.orient = (this.orient + this.genome[this.currentGenomeIndex]) % 8;
        this.currentGenomeIndex++;
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
        this.position = wantedPos;
    }
}

package classes;

public class Animal {
    //0-7 (8 wartosci co 45*)

    private Vector2d position;

    private int orient;
    private int energy;
    private int[] genome;
    private int currentGenomeIndex;

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
        this.currentGenomeIndex= GenomeModifier.changeGenomeIndex();
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void setPosition(Vector2d position){ this.position=position;}

}

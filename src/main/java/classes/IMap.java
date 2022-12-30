package classes;

import java.util.SortedSet;

public interface IMap {

    // specific for Map type (Simulation task variations)
    void renderGrass(int bushNum);

    // function updates Map based on Animal move
    void moveAtMap(Animal animal,Vector2d position);

    // methods implemented in common abstract class: AbstractMap

    // general purpose => later statistics may use those
    int getGrassCount();
    int getAnimalsCount();

    int getDay();

    void setDay(int day);

    // inits bushes on freshly created map
    void initBushes(int bushNumber);

    // inits animals on freshly created map
    void initAnimals(int animalNum);

    // used in drawing testing (console testing -> later GUI)
    Vector2d getRightUp();

    // used in drawing testing (console testing ->later GUI)
    Vector2d getLeftBottom();

    // returns animal SortedSet At position (or null if none)
    SortedSet<Animal> animalsAt(Vector2d position);

//    returns grass at position (or null if none)
    Grass grassAt(Vector2d position);

    // used only when creating new Animal
    void place(Animal animal, Vector2d position);

    // function simulating phase of moves
    // (moves each animal on map)
    void manageAnimalMoves();

    // function clears map from dead animals in each day cycle
    void manageDead(Statistics stats);

    // function resolves conflicts (when more than 1 animal appears on
    // one position) and feeds chosen Animal (for all positions occupied by animal each day)
    void manageEating();

    // function manages reproduction process when there is more than 1 animal on
    // certain position (for all positions occupied by animal each day)
    void manageReproduction();

    // function used to clear certain day info
    // and to reset data structure for upcoming day
    void clearDayInfo();

}

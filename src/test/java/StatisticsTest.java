import classes.Animal;
import classes.GrassField;
import classes.IMap;
import classes.Statistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class StatisticsTest {

    @Test
    void assertMostPolularGenome(){
        Statistics stats = new Statistics(5, false);
        LinkedList<Animal> animals = new LinkedList<>();

        Assertions.assertNull(stats.getMostPopularGenome());

        Animal animal;
        for (int i=0; i<7; i++){
            animal = new Animal();
            animal.genome = new int[]{1,1,1,1,1};
            animals.add(animal);
        }
        animal = new Animal();
        animal.genome = new int[] {1,2,3,4,5};
        animals.add(animal);
        stats.updateCurrAnimalCount(animals.size());
        stats.updateMostPopularGenome(animals);

        Assertions.assertArrayEquals(new int[]{1, 1, 1, 1, 1}, stats.getMostPopularGenome());
    }

    @Test
    void assertAverageEnergy(){
        Statistics stats = new Statistics(5, false);
        LinkedList<Animal> animals = new LinkedList<>();
        Animal animal;
        float average = 0;
        for (int i=0; i<7; i++){
            animal = new Animal();
            animal.setEnergy(i+1);
            average += i+1;
            animals.add(animal);
        }
        average /= animals.size();
        stats.updateAverageEnergy(animals);
        Assertions.assertTrue(Math.abs(stats.getAverageEnergy() - average) < 0.001);
    }

    @Test
    void assertAverageLifeSpan(){
        Statistics stats = new Statistics(5, false);
        float average = 0;
        for (int i = 0; i < 8; i++){
            average += i+1;
            stats.updateAverageLifeSpan(i+1);
        }
        average /= 8;
        Assertions.assertTrue(Math.abs(average - stats.getAverageLifeSpan()) < 0.001);
    }
}

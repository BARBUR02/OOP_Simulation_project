package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Statistics {

    private String path;
    private int currAnimalCount;
    private int currGrassCount;
    private int currFreeFieldsCount;
    private int[] mostPopularGenome;
    private final int genomeLength;
    private int averageEnergy;
    private float averageLifeSpan = 0;
    private int numOfDeadAnimals = 0;

    public Statistics(int genomeLength){
        this.genomeLength = genomeLength;
        initCSVFile();
    }

    private void initCSVFile(){
        int i = 0;
        File tmp;
        do {
            this.path = "StatisticFiles/stats" + i + ".csv";
            tmp = new File(path);
            i++;
        } while (tmp.exists());
        try (FileWriter file = new FileWriter(path,true)){
            file.append("Number of animals;Number of grass;Number of free fields;Most popular genome;Average energy;Average life span of animals\n");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateCSVFile(){
        try (FileWriter file = new FileWriter(path,true)){
            String arg = currAnimalCount+";"+currGrassCount+","+currFreeFieldsCount+";";
            arg += "[";
            for (int i = 0; i < genomeLength; i++){
                arg += mostPopularGenome[i] + ";";
            }
            arg += "];";
            arg += averageEnergy + ";";
            arg += averageLifeSpan;
            arg += "\n";
            file.append(arg);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateCurrAnimalCount(int n){
        this.currAnimalCount = n;
    }

    public void updateCurrGrassCount(int n){
        this.currGrassCount = n;
    }

    public void updateCurrFreeFieldsCount(int n){
        this.currFreeFieldsCount = n;
    }



    public void updateMostPopularGenome(LinkedList<Animal> animals){
        int[][] genomes = new int[this.currAnimalCount][this.genomeLength];
        int[] popularity = new int[this.currAnimalCount];
        for (Animal animal: animals){
            for (int i = 0; i < this.currAnimalCount; i++){
                if (popularity[i] == 0){
                    genomes[i] = animal.genome;
                    popularity[i]++;
                    break;
                }
                else if (Arrays.equals(genomes[i], animal.genome)){
                    popularity[i]++;
                }
            }
        }
        int max_i = 0;
        for (int i = 0; i < currAnimalCount; i++){
            if (popularity[i] == 0){
                break;
            }
            if (popularity[i] > popularity[max_i]){
                max_i = i;
            }
        }
        this.mostPopularGenome = genomes[max_i];
    }

    public void updateAverageEnergy(LinkedList<Animal> animals){
        int average = 0;
        for (Animal animal: animals){
            average += animal.getEnergy();
        }
        if (animals.size() == 0){
            this.averageEnergy = 0;
            return;
        }
        this.averageEnergy = average/animals.size();
    }

    public void updateAverageLifeSpan(float lifeSpan){
        averageLifeSpan = (numOfDeadAnimals*averageLifeSpan)/(numOfDeadAnimals+1) + (lifeSpan)/(numOfDeadAnimals+1);
        numOfDeadAnimals++;
    }

    public int getCurrFreeFieldsCount() {
        return currFreeFieldsCount;
    }

    public String stringMostPopularGenome() {
        String temp = "[";
        for (int gen: mostPopularGenome){
            temp += gen + ",";
        }
        temp += "]";
        return temp;
    }

    public int getAverageEnergy() {
        return averageEnergy;
    }

    public float getAverageLifeSpan() {
        return averageLifeSpan;
    }
}

package classes;

import java.util.Arrays;
import java.util.LinkedList;

public class GenomeModifier {

    private static final int posCount = 8;

    public static int randomOrient() {
        return (int) (Math.random() * posCount);
    }

    public static int[] createGenome(int n) {
        int[] genomeArr = new int[n];
        for (int i = 0; i < n; i++) {
            genomeArr[i] = (int) (Math.random() * posCount);
        }
        return genomeArr;
    }

    public static int[] mutateGenome(int[] genome, int minTimes, int maxTimes) {
        if (maxTimes == 0) {
            return genome;
        }
        System.out.println("Starting genome :");
        for (int num : genome) {
            System.out.print(num + " ");
        }
        System.out.print("\n");

        int internalMutationNum = (maxTimes - minTimes);
        internalMutationNum = Math.min(internalMutationNum, genome.length);
        int[] externalMutationIndexes = getExternalIndexes(genome.length, minTimes, internalMutationNum);
        for (int i=0;i<externalMutationIndexes.length;i++){
            int index=externalMutationIndexes[i];
            genome[index] = Math.random() < 0.5 ?( genome[index]==0 ? posCount-1 :(genome[index] - 1) ) : (genome[index] + 1)%posCount;
        }
        System.out.println("\nMutated genome :");
        for (int num : genome) {
            System.out.print(num + " ");
        }
        System.out.print("\n");
        return genome;
    }

    public static int[] getExternalIndexes(int n, int times, int possiblyMore) {
        LinkedList<Integer> indexes = new LinkedList<>();
        LinkedList <Integer> tmp = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            indexes.add(i);
        }
        for (int i = 0; i < times; i++) {
            tmp.add(indexes.remove((int) (Math.random() * (indexes.size()))) );
        }
        for (int i=0;i<possiblyMore;i++){
            if (Math.random()<0.5){
                tmp.add(indexes.remove((int) (Math.random() * (indexes.size()))) );
            }
        }
        int[] result = new int[tmp.size()];
        for (int i=0;tmp.size()>0;i++){
            result[i]=tmp.remove();
        }
        Arrays.sort(result);
        return result;
    }

    ;

    public static int changeGenomeIndex(int n) {
        return (int) (Math.random() * n);
    }

    // function creating child genome based od calculated proportion
    // and parent genomes (stronger as first param/ weaker as following)
    public static int[] fuseGenoms(int[] stronger, int[] weaker, double proportion) {
        int genomeLength = stronger.length;
        int greaterNum = (int) (proportion * genomeLength);
        int[] resultGenome = new int[genomeLength];
        int scenario = (int) (Math.random() * 2);
        //wiekszosc z lewej strony
        if (scenario == 0) {
            for (int i = 0; i < genomeLength; i++) {
                if (i < greaterNum) {
                    resultGenome[i] = stronger[i];
                } else {
                    resultGenome[i] = weaker[i];
                }
            }
        }
        //odwrotnie
        else {
            for (int i = 0; i < genomeLength; i++) {
                if (i >= genomeLength - greaterNum) {
                    resultGenome[i] = stronger[i];
                } else {
                    resultGenome[i] = weaker[i];
                }
            }
        }
        return resultGenome;
    }


}

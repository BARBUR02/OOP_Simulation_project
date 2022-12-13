package classes;

public class GenomeModifier {

    private static final int posCount=8;

    public static int[] createGenome(int n){
        int[] genomeArr= new int[n];
        for ( int i=0;i<n;i++){
            genomeArr[i]=(int) (Math.random()*posCount);
        }
        return genomeArr;
    }

    public static int[] mutateGenome(int[] genome){
        for (int i=0;i<genome.length;i++){
            // each single gene may change (50% chance)
            if (Math.random()<0.5){
//                When it changes, then we realize our variation
            genome[i]= Math.random()<0.5 ? genome[i]-1 : genome[i]+i;
        }
        }
        return genome;
    }

    public static int changeGenomeIndex(){
        return (int) (Math.random()*posCount);
    }

    // function creating child genome based od calculated proportion
    // and parent genomes (stronger as first param/ weaker as following)
    public static int[] fuseGenoms(int[] stronger, int[] weaker, float proportion){
        int greaterNum = (int)( proportion*posCount );
        int[] resultGenome = new int[posCount];
        int scenario=(int) (Math.random()*2);
        //wiekszosc z lewej strony
        if (scenario==0)
        {
            for ( int i=0; i<posCount;i++){
                if(i<greaterNum){
                    resultGenome[i]=stronger[i];
                }
                else{
                    resultGenome[i]=weaker[i];
                }
            }
        }
        //odwrotnie
        else{
            for ( int i=0; i<posCount;i++){
                if(i>=greaterNum){
                    resultGenome[i]=stronger[i];
                }
                else{
                    resultGenome[i]=weaker[i];
                }
            }
        }
        return resultGenome;
    }


}

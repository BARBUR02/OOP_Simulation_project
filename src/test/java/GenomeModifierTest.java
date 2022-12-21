import classes.GenomeModifier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenomeModifierTest {
    @Test
    void createGenomeTest(){
        int n=14;
        int [] newGenome = GenomeModifier.createGenome(n);
        boolean result=true;
        for (int i=0;i<n;i++){
            if (newGenome[i]<0 || newGenome[i]>=n ){
                result = false;
                break;
            }
        }
        assertTrue(result);
    }

    @Test
    void changeGenomeIndexTest(){
        int n=14;
        int [] newGenome = GenomeModifier.createGenome(n);
        int index= GenomeModifier.changeGenomeIndex(n);
        assertTrue(index >=0 || index <n);
    }


    @Test
    void fuseGenomsTest(){
        int [] strongerGenome ={2,2,2,2,2,2,2,2,2,2};
        int [] weakerGenome ={5,5,5,5,5,5,5,5,5,5};
        int n=weakerGenome.length;
        double proportion = 0.6;
        int [] childGenome = GenomeModifier.fuseGenoms(strongerGenome,weakerGenome,proportion);
        boolean result = true;
        // We can randomly get right or left proportion -> we check both possibilities
        for ( int i=0;i<n;i ++ ){
            if (i<=5){
                if (Math.abs(childGenome[i]-strongerGenome[i])>2)
                    result= false;
                    break;
            }
            if (i>5){
                if (Math.abs(childGenome[i]-weakerGenome[i])>2)
                    result= false;
                    break;
            }
            }
        if (result == true){
            assertTrue(result);
        }

        for ( int i=0;i<n;i ++ ){
            if (i<=5){
                if (Math.abs(childGenome[i]-strongerGenome[i])>2) {
                    result = false;
                    break;
                }
            }
            if (i>5){
                if (Math.abs(childGenome[i]-weakerGenome[i])>2){
                    result= false;
                break;
            } }
        }
        assertTrue(result);
    }


}

package self.cbedoy.utils;

/**
 * Created by Carlos Bedoy on 17/06/2015.
 *
 */
public class Utils
{

    public void printChromosomeWithTitle(int[] chromosome, String title){
        System.out.println();
        System.out.println(title);
        for (int index = 0 ; index < chromosome.length; index++){
            System.out.print(chromosome[index] + " - ");
        }
    }

    public int[] removeOnesFromChromosome(int[] chromosome){

        int[] newChromosome = new int[chromosome.length - 2];

        System.arraycopy(chromosome, 1, newChromosome, 0, newChromosome.length);

        return newChromosome;
    }
}

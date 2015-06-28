package self.cbedoy.services;

import java.util.Random;

/**
 * Created by Carlos Bedoy on 17/06/2015.
 */
public class MutationService
{
    private int MUTATION_BIT_ONE = 8;
    private int MUTATION_BIT_TWO = 16;

    public int[] mutateChromosome(int[] chromosome){

        //MUTATION_BIT_ONE = new Random().nextInt(24) + 1;
        //MUTATION_BIT_TWO = new Random().nextInt(24) + 1;

        int BIT_ONE = chromosome[MUTATION_BIT_ONE];
        int BIT_TWO = chromosome[MUTATION_BIT_TWO];

        chromosome[MUTATION_BIT_ONE] = BIT_TWO;

        chromosome[MUTATION_BIT_TWO] = BIT_ONE;

        return chromosome;
    }
}

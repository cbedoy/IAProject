package self.cbedoy.services;

/**
 * Created by Carlos Bedoy on 17/06/2015.
 */
public class MutationService
{
    private int MUTATION_BIT_ONE = 8;
    private int MUTATION_BIT_TWO = 18;

    public int[] mutateChromosome(int[] chromosome){
        int BIT_ONE = chromosome[MUTATION_BIT_ONE];
        int BIT_TWO = chromosome[MUTATION_BIT_TWO];

        chromosome[MUTATION_BIT_ONE] = BIT_TWO;

        chromosome[MUTATION_BIT_TWO] = BIT_ONE;

        return chromosome;
    }
}

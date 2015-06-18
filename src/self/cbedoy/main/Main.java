package self.cbedoy.main;

import self.cbedoy.builders.CityBuilder;
import self.cbedoy.builders.InitialPopulation;
import self.cbedoy.builders.PMXBuilder;
import self.cbedoy.services.MutationService;
import self.cbedoy.services.SelectionService;
import self.cbedoy.utils.Utils;

/**
 * Created by Carlos Bedoy on 17/06/2015.
 *
 */
public class Main
{
    public static void main(String[]args)
    {
        Utils utils = new Utils();

        //Load Cities and manage
        CityBuilder cityBuilder = new CityBuilder();

        //Create the initial population
        InitialPopulation initialPopulation = new InitialPopulation();
        initialPopulation.setCityBuilder(cityBuilder);
        initialPopulation.createPopulation();

        //Get initial population
        int[][] population = initialPopulation.getPopulation();

        //Get initial distances vector by obtain the best chromosome
        float[] distancesVector = initialPopulation.getDistancesVector();

        //Process by selected the two best chromosomes
        SelectionService selectionService = new SelectionService();
        selectionService.setDistanceVector(distancesVector);
        selectionService.setPopulationMatrix(population);
        selectionService.initSelection();

        //Obtain best Parent One
        int[] bestParentOne = selectionService.getBestParentOne();

        //Obtain best Parent Two
        int[] bestParentTwo = selectionService.getBestParentTwo();

        //Print chromosome
        utils.printChromosomeWithTitle(bestParentOne, "Best Parent One before cut point");
        utils.printChromosomeWithTitle(bestParentTwo, "Best Parent Two before cut point");

        int[] bestParentOneToEvaluate = utils.removeOnesFromChromosome(bestParentOne);
        int[] bestParentTwoToEvaluate = utils.removeOnesFromChromosome(bestParentTwo);

        //Determine and generate cut point
        PMXBuilder pmxBuilder = new PMXBuilder(bestParentOneToEvaluate, bestParentTwoToEvaluate);

        //Obtain offspring One
        int[] offspringOne = pmxBuilder.get_offspring1();
        //Obtain offspring Two
        int[] offspringTwo = pmxBuilder.get_offspring2();

        //Print chromosome
        utils.printChromosomeWithTitle(offspringOne, "Offspring One Generated with PMX");
        utils.printChromosomeWithTitle(offspringOne, "Offspring Two Generated with PMX");

        //Mutate service
        MutationService mutationService = new MutationService();

        //Mutate first chromosome
        int[] mutateChromosomeOne = mutationService.mutateChromosome(offspringOne);

        //Mutate second chromosome
        int[] mutateChromosomeTwo = mutationService.mutateChromosome(offspringTwo);

        utils.printChromosomeWithTitle(mutateChromosomeOne, "Mutated chromosome One");

        utils.printChromosomeWithTitle(mutateChromosomeTwo, "Mutated chromosome Two");
    }
}

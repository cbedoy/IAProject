package self.cbedoy.main;

import self.cbedoy.builders.CityBuilder;
import self.cbedoy.builders.InitialPopulationBuilder;
import self.cbedoy.builders.PMXBuilder;
import self.cbedoy.services.MutationService;
import self.cbedoy.services.SelectionService;
import self.cbedoy.utils.Utils;

import java.util.Scanner;

/**
 * Created by Carlos Bedoy on 17/06/2015.
 *
 */
public class Main
{
    public static void main(String[]args)
    {

        Scanner scanner = new Scanner(System.in);
        int nGeneration = 1;
        int currentGeneration = 0;

        //System.out.println("Please input the number of generations");
        //nGeneration = scanner.nextInt();


        //Load Cities and manage
        CityBuilder cityBuilder = new CityBuilder();

        //Create the initial population
        InitialPopulationBuilder initialPopulationBuilder = new InitialPopulationBuilder();
        initialPopulationBuilder.setCityBuilder(cityBuilder);
        initialPopulationBuilder.createPopulation();

        //Get initial population
        int[][] initialPopulation = initialPopulationBuilder.getPopulation();
        //Get initial distances vector by obtain the best chromosome
        float[] distancesVector = initialPopulationBuilder.getDistancesVector();

        //Process by selected the two best chromosomes
        SelectionService selectionService = new SelectionService();
        selectionService.setDistanceVector(distancesVector);
        selectionService.setPopulationMatrix(initialPopulation);
        selectionService.initSelection();

        //Obtain best Parent One
        int[] bestParentOne = selectionService.getBestParentOne();

        //Obtain best Parent Two
        int[] bestParentTwo = selectionService.getBestParentTwo();

        //Print chromosome
        Utils.printChromosomeWithTitle(bestParentOne, "Best Parent One before cut point");
        Utils.printChromosomeWithTitle(bestParentTwo, "Best Parent Two before cut point");

        bestParentOne = Utils.removeOnesFromChromosome(bestParentOne);
        bestParentTwo = Utils.removeOnesFromChromosome(bestParentTwo);

        //Determine and generate cut point
        PMXBuilder pmxBuilder = new PMXBuilder(bestParentOne, bestParentTwo);

        //Obtain offspring One
        int[] offspringOne = pmxBuilder.getOffspringOne();
        //Obtain offspring Two
        int[] offspringTwo = pmxBuilder.getOffspringTwo();

        offspringOne = Utils.fillWithLessOnesToChromosome(offspringOne);
        offspringTwo = Utils.fillWithLessOnesToChromosome(offspringTwo);

        //Print chromosome
        Utils.printChromosomeWithTitle(offspringOne, "Offspring One Generated with PMX");
        Utils.printChromosomeWithTitle(offspringTwo, "Offspring Two Generated with PMX");

        //Mutate service
        MutationService mutationService = new MutationService();

        //Mutate first chromosome
        int[] mutateChromosomeOne = mutationService.mutateChromosome(offspringOne);

        //Mutate second chromosome
        int[] mutateChromosomeTwo = mutationService.mutateChromosome(offspringTwo);

        Utils.printChromosomeWithTitle(mutateChromosomeOne, "Mutated chromosome One");

        Utils.printChromosomeWithTitle(mutateChromosomeTwo, "Mutated chromosome Two");
    }
}

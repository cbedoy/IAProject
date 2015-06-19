package self.cbedoy.main;

import self.cbedoy.builders.CityBuilder;
import self.cbedoy.builders.InitialPopulationBuilder;
import self.cbedoy.builders.PMXBuilder;
import self.cbedoy.services.EvaluationService;
import self.cbedoy.services.MutationService;
import self.cbedoy.services.NewPopulationService;
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


        //System.out.println("Please input the number of generations");
        //nGeneration = scanner.nextInt();


        int GENERATION = 0;
        int NUMBER_OF_GENERATIONS = 50;



        /**Load Cities**/
        CityBuilder cityBuilder = new CityBuilder();

        /**Create the initial population**/
        InitialPopulationBuilder initialPopulationBuilder = new InitialPopulationBuilder();
        initialPopulationBuilder.setCityBuilder(cityBuilder);
        initialPopulationBuilder.createPopulation();

        /**Get initial population**/
        int[][] population = initialPopulationBuilder.getPopulation();
        /**Get initial distances vector by obtain the best chromosome**/
        float[] distances = initialPopulationBuilder.getDistancesVector();

        NewPopulationService newPopulationService;

        while (GENERATION < NUMBER_OF_GENERATIONS)
        {
            /**Process by selected the two best chromosomes**/
            SelectionService selectionService = new SelectionService();
            selectionService.setDistanceVector(distances);
            selectionService.setPopulationMatrix(population);
            selectionService.initSelection();

            /**Obtain best Parent One**/
            int[] bestParentOne = selectionService.popChromosome();
            /**Obtain best Parent Two**/
            int[] bestParentTwo = selectionService.popChromosome();

            /**Print Best Choromoso**/
            Utils.printChromosomeWithTitleAndDistance(bestParentOne, "Best Chromosome!!", cityBuilder);
            //Utils.printChromosomeWithTitle(bestParentOne, "Best Parent One before cut point");
            //Utils.printChromosomeWithTitle(bestParentTwo, "Best Parent Two before cut point");

            bestParentOne = Utils.removeOnesFromChromosome(bestParentOne);
            bestParentTwo = Utils.removeOnesFromChromosome(bestParentTwo);

            /**Determine and generate cut point**/
            PMXBuilder pmxBuilder = new PMXBuilder(bestParentOne, bestParentTwo);

            /**Obtain offspring One and Two**/
            int[] offspringOne = pmxBuilder.getOffspringOne();
            int[] offspringTwo = pmxBuilder.getOffspringTwo();

            offspringOne = Utils.fillWithLessOnesToChromosome(offspringOne);
            offspringTwo = Utils.fillWithLessOnesToChromosome(offspringTwo);

            bestParentOne = Utils.fillWithLessOnesToChromosome(bestParentOne);
            bestParentTwo = Utils.fillWithLessOnesToChromosome(bestParentTwo);

            /**Print chromosomes**/
            //Utils.printChromosomeWithTitle(offspringOne, "Offspring One Generated with PMX");
            //Utils.printChromosomeWithTitle(offspringTwo, "Offspring Two Generated with PMX");

            MutationService mutationService = new MutationService();

            newPopulationService = new NewPopulationService();
            newPopulationService.setMutationService(mutationService);
            newPopulationService.setCityBuilder(cityBuilder);
            newPopulationService.initNewPopulation();
            newPopulationService.addChromosome(bestParentOne);
            newPopulationService.addChromosome(bestParentTwo);
            newPopulationService.addChromosome(offspringOne);
            newPopulationService.addChromosome(offspringTwo);

            while (selectionService.hasChromosomes())
            {
                int[] nextChromosomeOne = selectionService.popChromosome();
                int[] nextChromosomeTwo = selectionService.popChromosome();

                newPopulationService.addChromosome(nextChromosomeOne);
                newPopulationService.addChromosome(nextChromosomeTwo);

                nextChromosomeOne = Utils.removeOnesFromChromosome(nextChromosomeOne);
                nextChromosomeTwo = Utils.removeOnesFromChromosome(nextChromosomeTwo);

                PMXBuilder pmx = new PMXBuilder(nextChromosomeOne, nextChromosomeTwo);

                int[] nextOffspringOne = pmx.getOffspringOne();
                int[] nextOffspringTwo = pmx.getOffspringTwo();

                nextOffspringOne = Utils.fillWithLessOnesToChromosome(nextOffspringOne);
                nextOffspringTwo = Utils.fillWithLessOnesToChromosome(nextOffspringTwo);

                newPopulationService.addChromosome(nextOffspringOne);
                newPopulationService.addChromosome(nextOffspringTwo);
            }

            newPopulationService.mutateAnyChromosome();
            newPopulationService.initDistancesVector();

            distances = newPopulationService.getNewDistances();

            population = newPopulationService.getNewPopulation();

            GENERATION++;
        }
    }
}

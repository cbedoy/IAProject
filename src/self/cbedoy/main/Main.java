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
            Utils.printChromosomeWithTitleAndDistance(bestParentOne, "Best Chromosome on Generation ("+GENERATION + 1+") !!", cityBuilder);
            //Utils.printChromosomeWithTitle(bestParentOne, "Best Parent One before cut point");
            //Utils.printChromosomeWithTitle(bestParentTwo, "Best Parent Two before cut point");

            bestParentOne = Utils.removeOnesFromChromosome(bestParentOne);
            bestParentTwo = Utils.removeOnesFromChromosome(bestParentTwo);

            /**Determine and generate cut point**/
            PMXBuilder pmxBuilder;

            pmxBuilder = new PMXBuilder(bestParentOne, bestParentTwo);

            /**Obtain offspring One and Two**/
            int[] offspringA = pmxBuilder.getOffspringOne();
            int[] offspringB = pmxBuilder.getOffspringTwo();

            pmxBuilder = new PMXBuilder(bestParentOne, bestParentTwo);

            /**Obtain offspring One and Two**/
            int[] offspringC = pmxBuilder.getOffspringOne();
            int[] offspringD = pmxBuilder.getOffspringTwo();

            offspringA = Utils.fillWithLessOnesToChromosome(offspringA);
            offspringB = Utils.fillWithLessOnesToChromosome(offspringB);
            offspringC = Utils.fillWithLessOnesToChromosome(offspringC);
            offspringD = Utils.fillWithLessOnesToChromosome(offspringD);


            MutationService mutationService = new MutationService();

            newPopulationService = new NewPopulationService();
            newPopulationService.setMutationService(mutationService);
            newPopulationService.setCityBuilder(cityBuilder);
            newPopulationService.initNewPopulation();
            newPopulationService.addChromosome(offspringA);
            newPopulationService.addChromosome(offspringB);
            newPopulationService.addChromosome(offspringC);
            newPopulationService.addChromosome(offspringD);

            while (selectionService.hasChromosomes())
            {
                int[] nextChromosomeOne = selectionService.popChromosome();
                int[] nextChromosomeTwo = selectionService.popChromosome();

                nextChromosomeOne = Utils.removeOnesFromChromosome(nextChromosomeOne);
                nextChromosomeTwo = Utils.removeOnesFromChromosome(nextChromosomeTwo);

                PMXBuilder pmx;

                pmx = new PMXBuilder(nextChromosomeOne, nextChromosomeTwo);

                int[] nextOffspringA = pmx.getOffspringOne();
                int[] nextOffspringB = pmx.getOffspringTwo();

                pmx = new PMXBuilder(nextChromosomeOne, nextChromosomeTwo);

                int[] nextOffspringC = pmx.getOffspringOne();
                int[] nextOffspringD = pmx.getOffspringTwo();

                nextOffspringA = Utils.fillWithLessOnesToChromosome(nextOffspringA);
                nextOffspringB = Utils.fillWithLessOnesToChromosome(nextOffspringB);
                nextOffspringC = Utils.fillWithLessOnesToChromosome(nextOffspringC);
                nextOffspringD = Utils.fillWithLessOnesToChromosome(nextOffspringD);

                newPopulationService.addChromosome(nextOffspringA);
                newPopulationService.addChromosome(nextOffspringB);
                newPopulationService.addChromosome(nextOffspringC);
                newPopulationService.addChromosome(nextOffspringD);
            }

            newPopulationService.mutateAnyChromosome();
            newPopulationService.initDistancesVector();

            distances = newPopulationService.getNewDistances();

            population = newPopulationService.getNewPopulation();

            GENERATION++;
        }
    }
}

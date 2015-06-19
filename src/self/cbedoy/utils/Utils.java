package self.cbedoy.utils;

import self.cbedoy.builders.CityBuilder;
import self.cbedoy.models.City;

/**
 * Created by Carlos Bedoy on 17/06/2015.
 *
 */
public class Utils
{

    public static void printChromosomeWithTitle(int[] chromosome, String title){
        System.out.println();
        System.out.println(title);
        for (int index = 0 ; index < chromosome.length; index++){
            System.out.print(chromosome[index] + " - ");
        }
    }

    public static void printChromosomeWithTitleAndDistance(int[] chromosome, String title, CityBuilder cityBuilder){
        printChromosomeWithTitle(chromosome, title);
        float totalDistance = 0;
        for (int index = 0 ; index < chromosome.length - 1; index++){
            City cityA = cityBuilder.getCityWithId(chromosome[index]);
            City cityB = cityBuilder.getCityWithId(chromosome[index + 1]);

            float distanceTo = cityA.distanceTo(cityB);

            totalDistance += distanceTo;
        }
        System.out.print(" Distance --> "+totalDistance);

    }

    public static int[] removeOnesFromChromosome(int[] chromosome){

        int[] newChromosome = new int[chromosome.length - 2];

        System.arraycopy(chromosome, 1, newChromosome, 0, newChromosome.length);

        return newChromosome;
    }

    public static int[] fillWithLessOnesToChromosome(int[] chromosome) {

        int[] newChromosome = new int[chromosome.length + 2];

        newChromosome[0] = 1;

        System.arraycopy(chromosome, 0, newChromosome, 1, newChromosome.length - 1 - 1);

        newChromosome[newChromosome.length - 1] = 1;

        return newChromosome;
    }


}

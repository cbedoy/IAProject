package self.cbedoy.services;

import self.cbedoy.builders.CityBuilder;
import self.cbedoy.models.City;
import self.cbedoy.utils.Utils;

import java.util.Random;

/**
 * Created by Carlos Bedoy on 18/06/2015.
 */
public class NewPopulationService
{
    private int[][] POPULATION_MATRIX;
    private float[] DISTANCES_VECTOR;
    private final int POPULATION_SIZE = 100;
    private int POPULATION_COUNT = 0;

    private CityBuilder cityBuilder;

    private MutationService mutationService;

    public void setMutationService(MutationService mutationService) {
        this.mutationService = mutationService;
    }

    public void setCityBuilder(CityBuilder cityBuilder) {
        this.cityBuilder = cityBuilder;
    }

    public void initNewPopulation(){
        System.out.println();
        System.out.println();
        System.out.println("Creating new Population....");
        POPULATION_MATRIX = new int[POPULATION_SIZE][cityBuilder.getSize() + 1];
    }

    public void addChromosome(int[] chromosome)
    {
        for(int i=0; i<chromosome.length; i++){
            POPULATION_MATRIX[POPULATION_COUNT][i] = chromosome[i];
        }
        POPULATION_COUNT++;
    }

    public void initDistancesVector()
    {
        DISTANCES_VECTOR = new float[POPULATION_SIZE];

        for(int i=0; i< POPULATION_MATRIX.length; i++)
        {
            DISTANCES_VECTOR[i] = 0;
            for(int j=0; j< POPULATION_MATRIX[i].length - 1; j++)
            {
                City cityA = cityBuilder.getCityWithId(POPULATION_MATRIX[i][j]);
                City cityB = cityBuilder.getCityWithId(POPULATION_MATRIX[i][j+1]);

                float distance = cityA.distanceTo(cityB);

                DISTANCES_VECTOR[i] += distance;
            }
        }
    }

    public int[][] getNewPopulation() {
        return POPULATION_MATRIX;
    }

    public float[] getNewDistances() {
        return DISTANCES_VECTOR;
    }

    public void mutateAnyChromosome() {
        int mutateIndex = new Random().nextInt(100);

        POPULATION_MATRIX[mutateIndex] = mutationService.mutateChromosome(POPULATION_MATRIX[mutateIndex]);
    }

    public void mutateAllChromosomes() {
        for (int i=0; i<POPULATION_MATRIX.length; i++){
            POPULATION_MATRIX[i] = mutationService.mutateChromosome(POPULATION_MATRIX[i]);
        }
    }
}

package self.cbedoy.builders;

import self.cbedoy.models.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Carlos Bedoy on 17/06/2015.
 *
 */
public class InitialPopulationBuilder
{
    //FINAL VARIABLES
    private final int   POPULATION_SIZE     = 100;
    private final int   CITIES              = 25;
    private final int   numGeneration       = 50;
    private int         currentGeneration   = 0;

    private int[][] INITIAL_POPULATION_MATRIX;
    private float[] INITIAL_DISTANCES_VECTOR;

    private CityBuilder cityBuilder;

    public void setCityBuilder(CityBuilder cityBuilder) {
        this.cityBuilder = cityBuilder;
    }

    public void createPopulation(){
        initInitialPopulation();
        initInitialDistancesVector();
    }

    private void initInitialDistancesVector()
    {
        INITIAL_DISTANCES_VECTOR = new float[POPULATION_SIZE];

        for(int i=0; i<INITIAL_POPULATION_MATRIX.length; i++)
        {
            INITIAL_DISTANCES_VECTOR[i] = 0;
            for(int j=0; j<INITIAL_POPULATION_MATRIX[i].length - 1; j++)
            {
                City cityA = cityBuilder.getCityWithId(INITIAL_POPULATION_MATRIX[i][j]);
                City cityB = cityBuilder.getCityWithId(INITIAL_POPULATION_MATRIX[i][j+1]);

                float distance = cityA.distanceTo(cityB);

                INITIAL_DISTANCES_VECTOR[i] += distance;
            }
        }
    }

    private void initInitialPopulation(){

        INITIAL_POPULATION_MATRIX = new int[POPULATION_SIZE][CITIES + 1];

        for (int i=0; i< INITIAL_POPULATION_MATRIX.length; i++)
        {
            List<Integer> integerList = new ArrayList<Integer>();

            for (int j = 2; j<=25; j++)
            {
                integerList.add(j);
            }

            Collections.shuffle(integerList);

            INITIAL_POPULATION_MATRIX[i][0] = 1;

            for (int j=1, k=0; j< INITIAL_POPULATION_MATRIX[i].length - 1; j++, k++)
            {
                INITIAL_POPULATION_MATRIX[i][j] = integerList.get(k);
            }
            INITIAL_POPULATION_MATRIX[i][INITIAL_POPULATION_MATRIX[i].length -1] = 1;
        }

        printPopulationMatrix();
    }

    private void printPopulationMatrix() {
        System.out.println();
        System.out.println("Poblacion Inicial");
        System.out.println();
        for (int i = 0 ; i< INITIAL_POPULATION_MATRIX.length; i++){
            System.out.println();
            for (int j=0; j< INITIAL_POPULATION_MATRIX[i].length; j++){
                System.out.print("("+ INITIAL_POPULATION_MATRIX[i][j]+") ");
            }
        }
    }

    public int[][] getPopulation(){
        return INITIAL_POPULATION_MATRIX;
    }

    public float[] getDistancesVector(){
        return INITIAL_DISTANCES_VECTOR;
    }

    public boolean isAllGenerationCreated(){
        return  currentGeneration == numGeneration;
    }
}

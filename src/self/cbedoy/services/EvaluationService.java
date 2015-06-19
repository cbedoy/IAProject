package self.cbedoy.services;

import self.cbedoy.builders.CityBuilder;
import self.cbedoy.models.City;

/**
 * Created by Carlos Bedoy on 18/06/2015.
 */
public class EvaluationService {
    private float[][] DISTANCES_MATRIX;
    private float[] TOTAL_DISTANCE;
    private int[][] POPULATION_MATRIX;
    private CityBuilder cityBuilder;

    public void setPopulation(int[][] POPULATION_MATRIX) {
        this.POPULATION_MATRIX = POPULATION_MATRIX;
    }


    public void calculateDistanceMatrix() {

        int size = cityBuilder.getSize();

        DISTANCES_MATRIX = new float[size][size];

        TOTAL_DISTANCE = new float[size];

        for (int i = 0; i < DISTANCES_MATRIX.length; i++) {
            TOTAL_DISTANCE[i] = 0;
            for (int j = 0; j < DISTANCES_MATRIX[i].length; j++) {
                if (i == j) {
                    DISTANCES_MATRIX[i][j] = 0;
                } else {
                    City cityA = cityBuilder.getCityWithId(i+1);
                    City cityB = cityBuilder.getCityWithId(j+1);

                    float distanceTo = cityA.distanceTo(cityB);

                    DISTANCES_MATRIX[i][j] = distanceTo;


                    TOTAL_DISTANCE[i]+= distanceTo;
                }
            }
        }
        printDistanceMat(DISTANCES_MATRIX);
    }

    private void printDistanceMat(float[][] mat){
        System.out.println();
        System.out.println("Distance Matrix....");
        System.out.println();
        for (int i = 0 ; i<mat.length; i++)
        {
            System.out.println();
            for (int j=0; j<mat[i].length; j++)
            {
                System.out.print("("+mat[i][j] + ") ");
            }
            System.out.print("Total Distance: " + TOTAL_DISTANCE[i]);
        }
    }

    public void setCityBuilder(CityBuilder cityBuilder) {
        this.cityBuilder = cityBuilder;
    }
}

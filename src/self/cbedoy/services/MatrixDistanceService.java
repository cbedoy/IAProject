package self.cbedoy.services;


import self.cbedoy.models.City;
import self.cbedoy.builders.CityBuilder;

/**
 * Created by Carlos Bedoy on 18/06/2015.
 *
 */
public class MatrixDistanceService
{
    private int citySize;
    private float[][] distances;
    private float[] totalDistance;
    private CityBuilder cityBuilder;

    public void setCitySize(int citySize) {
        this.citySize = citySize;
    }

    public void setCityBuilder(CityBuilder cityBuilder) {
        this.cityBuilder = cityBuilder;
    }

    public void calculateDistanceMatrix()
    {
        distances = new float[citySize][citySize];

        totalDistance = new float[citySize];

        for (int i = 0; i < distances.length; i++)
        {
            float total = 0;
            for (int j = 0; j < distances[i].length; j++)
            {
                if (i == j)
                {
                    distances[i][j] = 0;
                }
                else
                {
                    City cityA = cityBuilder.getCityWithId(i + 1);
                    City cityB = cityBuilder.getCityWithId(j + 1);

                    float distanceTo = cityA.distanceTo(cityB);

                    distances[i][j] = (int) distanceTo;

                    total += distanceTo;

                    totalDistance[i] = (int) total;
                }
            }
        }
        System.out.println();
        System.out.println("DISTANCES");
        printDistanceMat(distances, totalDistance);
    }

    private void printDistanceMat(float[][] mat, float[]totals){
        System.out.println();
        for (int i = 0 ; i<mat.length; i++)
        {
            System.out.println();
            for (int j=0; j<mat[i].length; j++)
            {
                System.out.print("("+mat[i][j] + ") ");
            }
            System.out.print("Total Distance: " + totals[i]);
        }
    }
}

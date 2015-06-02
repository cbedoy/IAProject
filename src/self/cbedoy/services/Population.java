package self.cbedoy.services;

import self.cbedoy.models.City;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Carlos Bedoy on 29/05/2015.
 */
public class Population
{
    //FINAL VARIABLES
    private final int   POPULATION_SIZE     = 100;
    private final int   CITIES              = 25;
    private final int   numGeneration       = 100;
    private int         generation          = 0;

    //SORTED MATRIX
    private float [][]  distancesSorted;
    private float[]     totalDistanceSorted;

    //CALCULATION MATRIX
    private int[][]     population;
    private float [][]  distances;

    //TOTAL DISTANCES
    private float[]     totalDistance;

    //CITIES LIST
    private List<City>  cityList;

    //UTILS
    private Map<Float, float[]> distanceMap;


    public Population(){

        generation = 0;
        CityBuilder cityBuilder = new CityBuilder();
        cityList = cityBuilder.getCityList();


        while (generation < numGeneration) {

            initPopulation();
            calculateDistanceMatrix();

            writeMatrixToFile();

            sortMatriz();

        }
    }

    private void sortMatriz()
    {
        distanceMap = new TreeMap<Float, float[]>();

        for(int i = 0; i< distances.length; i++)
        {
            distanceMap.put(totalDistance[i], distances[i]);
        }


        for (float key : distanceMap.keySet()){

        }
    }

    public void calculateDistanceMatrix()
    {
        distances = new float[CITIES][CITIES];

        totalDistance = new float[CITIES];

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
                    City cityA = cityList.get(i);
                    City cityB = cityList.get(j);

                    float distanceTo = cityA.distanceTo(cityB);

                    distances[i][j] = distanceTo;

                    total += distanceTo;

                    totalDistance[i] = total;
                }
            }
        }
        System.out.println("                DISTANCES               ");
        printDistanceMat(distances);
    }



    private void initPopulation()
    {
        population = new int[POPULATION_SIZE][CITIES + 1];

        for (int i=0; i<population.length; i++)
        {
            List<Integer> integerList = new ArrayList<Integer>();

            for (int j = 2; j<=25; j++)
            {
                integerList.add(j);
            }

            Collections.shuffle(integerList);

            population[i][0] = 1;

            for (int j=1, k=0; j<population[i].length - 1; j++, k++)
            {
                population[i][j] = integerList.get(k);
            }
            population[i][population[i].length -1] = 1;
        }

        System.out.println("            POPULATION              ");
        printMat(population);

    }


    public void writeMatrixToFile() {

        String OUTPUT_FILENAME = "TSP_FILE_GENERATION$GENERATION.txt";

        OUTPUT_FILENAME = OUTPUT_FILENAME.replace("$GENERATION", String.valueOf(generation));

        BufferedWriter bufferedWriter = null;
        try
        {
            bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_FILENAME));
            for (int i = 0; i < CITIES; i++)
            {
                for (int j = 0; j < CITIES-1; j++)
                {
                    bufferedWriter.write(distances[i][j] + " ");
                }
                bufferedWriter.write(distances[i][CITIES - 1] + "\n");
            }
        }
        catch (IOException e)
        {
            System.err.println("\tERROR: Input file not found");
        }
        finally
        {
            try
            {

                if (bufferedWriter != null)
                {
                    bufferedWriter.close();
                }
                generation++;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    private void printMat(int[][] mat){
        for (int i = 0 ; i<mat.length; i++){
            System.out.println();
            for (int j=0; j<mat[i].length; j++){
                System.out.print("("+mat[i][j] + ") ");
            }
        }
    }

    private void printDistanceMat(float[][] mat){
        for (int i = 0 ; i<mat.length; i++)
        {
            System.out.println();
            for (int j=0; j<mat[i].length; j++)
            {
                System.out.print("("+mat[i][j] + ") ");
            }
            System.out.print("Total Distance: " + totalDistance[i]);
        }
    }

    public float[][] getDistances() {
        return distances;
    }

    public int[][] getPopulation() {
        return population;
    }
}

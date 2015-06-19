package self.cbedoy.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Carlos Bedoy on 17/06/2015.
 *
 */
public class SelectionService
{
    private int[][] POPULATION_MATRIX;
    private float[] DISTANCE_VECTOR;

    private List<KeyValue> keyValueList;

    public void setDistanceVector(float[] DISTANCE_VECTOR) {
        this.DISTANCE_VECTOR = DISTANCE_VECTOR;
    }

    public void setPopulationMatrix(int[][] POPULATION_MATRIX) {
        this.POPULATION_MATRIX = POPULATION_MATRIX;
    }

    public void initSelection(){
        initBestDistanceSelection();
    }

    private void initBestDistanceSelection() {
        List<KeyValue> listToSort = new ArrayList<KeyValue>();
        for (int index = 0 ; index<DISTANCE_VECTOR.length; index++){
            KeyValue keyValue = new KeyValue();
            keyValue.distance = DISTANCE_VECTOR[index];
            keyValue.chromosome = POPULATION_MATRIX[index];
            listToSort.add(keyValue);
        }
        Collections.sort(listToSort, new Comparator<KeyValue>() {
            @Override
            public int compare(KeyValue o1, KeyValue o2) {
                return o1.distance.compareTo(o2.distance);
            }
        });
        keyValueList = new ArrayList<KeyValue>();
        for(int index = 0; index < DISTANCE_VECTOR.length / 2; index++) {
            keyValueList.add(listToSort.get(index));
        }
    }

    public int[] popChromosome(){
        if(keyValueList.size() > 0){
            KeyValue keyValue = keyValueList.get(0);
            int[] chromosome = keyValue.chromosome;
            keyValueList.remove(0);
            return chromosome;
        }
        return null;
    }

    private class KeyValue{
        public Float distance;
        public int[] chromosome;
    }

    public boolean hasChromosomes(){
        return keyValueList.size() > 0;
    }
}

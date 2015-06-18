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

    private int[] bestParentOne;
    private int[] bestParentTwo;

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
        List<KeyValue> keyValueList = new ArrayList<KeyValue>();
        for (int index = 0 ; index<DISTANCE_VECTOR.length; index++){
            KeyValue keyValue = new KeyValue();
            keyValue.distance = DISTANCE_VECTOR[index];
            keyValue.index = index;
            keyValueList.add(keyValue);
        }
        Collections.sort(keyValueList, new Comparator<KeyValue>() {
            @Override
            public int compare(KeyValue o1, KeyValue o2) {
                return o1.distance.compareTo(o2.distance);
            }
        });
        KeyValue keyValueA = keyValueList.get(0);
        KeyValue keyValueB = keyValueList.get(1);

        bestParentOne = POPULATION_MATRIX[keyValueA.index];

        bestParentTwo = POPULATION_MATRIX[keyValueB.index];

    }

    public int[] getBestParentOne() {
        return bestParentOne;
    }

    public int[] getBestParentTwo() {
        return bestParentTwo;
    }

    private class KeyValue{
        public Float distance;
        public Integer index;
    }
}

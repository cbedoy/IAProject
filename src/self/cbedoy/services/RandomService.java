package self.cbedoy.services;

import java.util.List;
import java.util.Random;

/**
 * Created by Carlos Bedoy on 08/05/2015.
 */
public abstract class RandomService
{
    protected List<Double> mRandomNumbers;
    protected List<Integer> mRandomNumberInteger;
    protected double mAverange;
    protected int mSize;
    protected Random mRandom;

    public RandomService(Class className){
        mRandom = new Random(Short.MAX_VALUE);
    }

    public void setSize(int size) {
        mSize = size;
    }

    protected void evaluatePseudoNumbers()
    {
        double total = 0;
        for (Double number : mRandomNumbers) {
            total += number;
        }
        System.out.println("Total >>    "+total);
        System.out.println("Size >>     "+mRandomNumbers.size());
        System.out.println("Averange >> "+total / mRandomNumbers.size());
        mAverange = total / mSize;
    }

    protected void hasValidPseudoNumbersDistribution()
    {
        System.out.println("===========<<<EVALUATE>>>================");
        if(mAverange >= 0.49 && mAverange <= 0.51){

            System.out.println("It's a valid distribution");

        }
        else
        {
            System.out.println("It's invalid distribution");
            clearBuild();
        }
    }

    public void clearBuild(){
        if(mRandomNumbers != null)
        {
            mRandomNumbers.clear();
            mAverange = 0;
        }
        //buildRandomNumbers();
    }

    public abstract void buildRandomNumbers();
}

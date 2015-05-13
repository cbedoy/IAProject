package self.cbedoy.services;

import java.util.List;
import java.util.Random;

/**
 * Created by Carlos Bedoy on 08/05/2015.
 */
public abstract class RandomService
{
    protected List<Double> mRandomNumbers;
    protected double mAverange;
    protected int mSize;
    protected Random mRandom;

    public RandomService(Class className){
        System.out.println("Init >> "+className.getCanonicalName());
        mRandom = new Random(Short.MAX_VALUE);
    }

    public void setSize(int size) {
        mSize = size;
    }

    public void evaluatePseudoNumbers()
    {
        double total = 0;
        for (Double number : mRandomNumbers) {
            total += number;
            System.out.println(number);
        }
        System.out.println("Total >>    "+total);
        System.out.println("Size >>     "+mRandomNumbers.size());
        System.out.println("Averange >> "+total / mRandomNumbers.size());
        mAverange = total / mSize;
    }

    public void hasValidPseudoNumbersDistribution()
    {
        System.out.println(mAverange >= 0.4 ? "It's valid distribution " : "It's invalid distribution");
    }

    public void clearBuild(){
        if(mRandomNumbers != null)
        {
            mRandomNumbers.clear();
            mSize = -1;
            mAverange = -1;
        }
    }

    public abstract void buildRandomNumbers();
}

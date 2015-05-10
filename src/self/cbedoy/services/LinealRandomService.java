package self.cbedoy.services;

import java.util.ArrayList;


/**
 * Created by Carlos Bedoy on 07/05/2015.
 *
 */
public class LinealRandomService extends RandomService
{
    public LinealRandomService(Class className) {
        super(className);
    }

    public void buildRandomNumberWithSeedConstIncrementAndModule(int seed, int constant, int increment, int module)
    {
        mRandomNumbers          = new ArrayList<Double>();
        while (mRandomNumbers.size() < mSize)
        {
            int function        = (constant * seed + increment) % module;
            seed                = function;
            double randomValue  = (double)function / module;
            mRandomNumbers.add(randomValue);
        }
    }
}

package self.cbedoy.services;

import java.util.ArrayList;


/**
 * Created by Carlos Bedoy on 07/05/2015.
 *
 */
public class LinealRandomService extends RandomService
{

    private int mModule;
    private int mSeed;
    private int mConstant;
    private int mIncrement;

    public LinealRandomService(Class className) {
        super(className);
    }

    @Override
    public void buildRandomNumbers() {
        generateRandomNumbersByExecuteTheBuild();
        buildRandomNumberWithSeedConstIncrementAndModule(mSeed, mConstant, mIncrement, mModule);
    }

    private void buildRandomNumberWithSeedConstIncrementAndModule(int seed, int constant, int increment, int module)
    {
        mRandomNumbers          = new ArrayList<Double>();
        while (mRandomNumbers.size() < mSize)
        {
            System.out.println(mRandomNumbers.size());

            int function        = (constant * seed + increment) % module;
            seed                = function;
            double randomValue  = (double)function / module;
            mRandomNumbers.add(randomValue);
        }
    }

    private void generateRandomNumbersByExecuteTheBuild(){
        mModule = mRandom.nextInt(Short.MAX_VALUE);
        mIncrement = createRandomNumberMinorToModule();
        mSeed = createRandomNumberMinorToModule();
        mConstant = createRandomNumberMinorToModule();

        System.out.println("Module >> "+mModule);
        System.out.println("Increment >> "+mIncrement);
        System.out.println("Seed >> "+mSeed);
        System.out.println("Constant >> "+mConstant);

    }

    private int createRandomNumberMinorToModule(){
        int value;
        while (true){
            value = mRandom.nextInt(Short.MAX_VALUE);
            if(value <= mModule)
                break;
        }
        return value;
    }

}

package self.cbedoy.services;

import javafx.scene.chart.Chart;

import java.util.*;


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

    private final int SEED = (int) Math.pow(2, 8);

    public LinealRandomService() {
        super(LinealRandomService.class);
        mRandomNumbers          = new ArrayList<Double>();
        mRandomNumberInteger    = new ArrayList<Integer>();
    }

    @Override
    public void buildRandomNumbers() {
        generateRandomNumbersByExecuteTheBuild();
        buildRandomNumberWithSeedConstIncrementAndModule(mSeed, mConstant, mIncrement, mModule);
        evaluatePseudoNumbers();
        hasValidPseudoNumbersDistribution();
    }

    private void buildRandomNumberWithSeedConstIncrementAndModule(int seed, int constant, int increment, int module)
    {
        mRandomNumbers.clear();
        while (mRandomNumbers.size() < mSize)
        {
            int function        = (constant * seed + increment) % module;
            seed                = function;
            double randomValue  = (double)function / module;


            mRandomNumberInteger.add(module / function);

            mRandomNumbers.add(randomValue);

            System.out.println(mRandomNumberInteger.get(mRandomNumbers.size() - 1));
        }
    }

    private void generateRandomNumbersByExecuteTheBuild(){

        mModule = Short.MAX_VALUE;  //Big number accepted
        mSeed = getNoPairNumber();
        mConstant = 7; //getNumberNoDivisibleFromThreeWithFiveAndNoPair();
        mIncrement = 11;//mModule / mConstant;

        System.out.println("Module >> "+mModule);
        System.out.println("Increment >> "+mIncrement);
        System.out.println("Seed >> "+mSeed);
        System.out.println("Constant >> "+mConstant);
    }

    private int getNumberNoDivisibleFromThreeWithFiveAndNoPair()
    {
        int n;
        Random random = new Random();
        while (true)
        {
            n = random.nextInt(SEED);
            if(n > 0 )
            {
                if(n%3 == 0 && n%5 == 0 && n%2 == 0)
                {
                    break;
                }
            }
        }
        return n;
    }

    private int getNoPairNumber()
    {
        int n;
        Random random = new Random();
        while (true){
            n = random.nextInt(SEED);
            if(n % 2 == 0)
                break;
        }
        return n;
    }

    public int getValue(int randomNo_boundary) {
        Double aDouble = mRandomNumbers.get(randomNo_boundary);
        int value = aDouble.intValue();
        return  value;
    }
}

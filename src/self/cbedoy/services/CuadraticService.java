package self.cbedoy.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Bedoy on 08/05/2015.
 */
public class CuadraticService extends RandomService
{
    public CuadraticService(Class className) {
        super(className);
    }

    public void buildRandomNumbersWithSeed(int seed) throws Exception
    {
        if(String.valueOf(seed).length() >= 4)
        {
            mRandomNumbers = new ArrayList<Double>();
            int factor = 4;
            while (mRandomNumbers.size() < mSize)
            {
                long cuadratic      = (long) Math.pow(seed, 2);
                String valueOf      = String.valueOf(cuadratic);
                int stringSize      = valueOf.length();
                int start           = stringSize % 2 == 0 ? (stringSize / 2) - 2 : (stringSize / 2) - 1;
                String substring    = valueOf.substring(start, start + factor);
                substring           = fillWithLessZeros(substring);
                seed                = Integer.parseInt(substring);
                if (seed == 0) break;
                double value = seed / Math.pow(10, substring.length());
                mRandomNumbers.add(value);
            }
        }
        else
        {
            throw new Exception("Unable do Service with invalid seed");
        }

    }

    private String fillWithLessZeros(String number)
    {
        int lessZeros = 4 - number.length();
        for(int index = 0; index<lessZeros; index++){
            number+="0";
        }
        return number;
    }
}

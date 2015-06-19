package self.cbedoy.builders;

import self.cbedoy.models.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Bedoy on 17/06/2015.
 *
 */
public class CityBuilder
{
    private List<City> mCityList;

    public CityBuilder(){
        init();
    }

    private void init(){

        mCityList = new ArrayList<City>();
        mCityList.add(new City(1,5,4));
        mCityList.add(new City(2,7,4));
        mCityList.add(new City(3,5,6));
        mCityList.add(new City(4,4,3));
        mCityList.add(new City(5,3,6));
        mCityList.add(new City(6,4,5));
        mCityList.add(new City(7,2,4));
        mCityList.add(new City(8,1,3));
        mCityList.add(new City(9,1,5));
        mCityList.add(new City(10,3,2));
        mCityList.add(new City(11,6,3));
        mCityList.add(new City(12,7,7));
        mCityList.add(new City(13,6,1));
        mCityList.add(new City(14,4,1));
        mCityList.add(new City(15,1,1));
        mCityList.add(new City(16,1,7));
        mCityList.add(new City(17,4,7));
        mCityList.add(new City(18,7,2));
        mCityList.add(new City(19,9,2));
        mCityList.add(new City(20,8,5));
        mCityList.add(new City(21,10,4));
        mCityList.add(new City(22,11,1));
        mCityList.add(new City(23,10,7));
        mCityList.add(new City(24,13,6));
        mCityList.add(new City(25,12,8));

    }

    public City getCityWithId(int id){
        try {
            City city = mCityList.get(id - 1);

            city.toString();

            return city;
        }catch (Exception e){
            System.err.println(e.getMessage() + " id -->"+id);
            return  null;
        }

    }

    public int getSize(){
        return mCityList.size();
    }

    public List<City> getCityList() {
        return mCityList;
    }
}

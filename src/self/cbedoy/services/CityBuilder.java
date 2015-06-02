package self.cbedoy.services;

import self.cbedoy.models.City;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Bedoy on 27/05/2015.
 */
public class CityBuilder
{
    private List<City> mListCity;

    public CityBuilder(){

        mListCity = new ArrayList<City>();

        try{
            FileInputStream fileInput = new FileInputStream("O:\\IAProject\\src\\self\\cbedoy\\cities");
            DataInputStream dataIn = new DataInputStream(fileInput);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(dataIn));
            String LINE;
            while ((LINE = buffer.readLine()) != null)   {

                String[] matches = LINE.split(" ");

                int id = Integer.parseInt(matches[0]);
                int x = Integer.parseInt(matches[1]);
                int y = Integer.parseInt(matches[2]);

                City city = new City(id, x, y);

                System.out.println(city.toString());

                mListCity.add(city);
            }
            dataIn.close();
        }catch (Exception e){
            System.err.println("Ocurrio un error: " + e.getMessage());
        }

    }

    public int getCitySize(){
        return mListCity.size();
    }

    public City getCityAtIndex(int index){
        return mListCity.get(index);
    }

    public List<City> getCityList(){return mListCity;}

}

package self.cbedoy.models;

import self.cbedoy.Main;

/**
 * Created by Carlos Bedoy on 27/05/2015.
 */
public class City extends Object
{
    private int mId;
    private int mX;
    private int mY;

    public City(int id, int x, int y){
        mId = id;
        mX = x;
        mY = y;
    }

    public City(){

    }


    public void setId(int mId) {
        this.mId = mId;
    }

    public void setX(int mX) {
        this.mX = mX;
    }

    public void setY(int mY) {
        this.mY = mY;
    }

    public int getId() {
        return mId;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    @Override
    public String toString() {
        return  "Identifier: "+mId+" Position X: " + mX + " Position Y: "+mY;
    }

    public float distanceTo(City city){
        int yDistance = Math.abs(mY - city.getY());
        int xDistance = Math.abs(mX - city.getX());

        float distance = (float) Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance,2));

        return  distance;
    }
}

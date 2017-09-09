package com.company.simulated_annealing;

/**
 * Created by Krzysztof Pikóra on 10.09.17.
 */
public class City {
    private int x;
    private int y;

    public City(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }

    public City(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceToCity(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt(( Math.pow(xDistance, 2) + Math.pow(yDistance, 2) ));

        return distance;
    }

    @Override
    public String toString(){
        return getX() + " " + getY();
    }
}

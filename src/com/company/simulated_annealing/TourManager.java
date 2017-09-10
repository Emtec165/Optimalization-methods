package com.company.simulated_annealing;

import java.util.ArrayList;

/**
 * Created by Krzysztof Pikóra on 10.09.17.
 */
public class TourManager {
    private static ArrayList<City> destinationCities = new ArrayList();

    public static void addCity(City city){
        destinationCities.add(city);
    }

    public static City getCity(int index){
        return destinationCities.get(index);
    }

    public static int numberOfCities(){
        return destinationCities.size();
    }
}

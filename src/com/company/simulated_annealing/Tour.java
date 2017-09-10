package com.company.simulated_annealing;

import com.company.particle_swarm_optimization.Particle;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Krzysztof Pikóra on 10.09.17.
 */
public class Tour {
    private ArrayList<City> tour = new ArrayList();
    private int distance = 0;

    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++){
            tour.add(null);
        }
    }

    public Tour(ArrayList tour){
        this.tour = (ArrayList) tour.clone();
    }

    public ArrayList getTour(){
        return this.tour;
    }

    public void generateIndividual(){
        for (int i = 0; i < TourManager.numberOfCities(); i++){
            setCity(i, TourManager.getCity(i));
        }
        Collections.shuffle(tour);
    }

    public void setCity(int tourPosition, City city){
        tour.set(tourPosition, city);
    }

    public City getCity(int tourPosition){
        return tour.get(tourPosition);
    }

    public int getDistance(){
        if (distance == 0){
            int tourDistance = 0;
            for (int i = 0; i < getTourSize(); i++){
                City fromCity = getCity(i);
                City destinationCity;
                if (i+1 < getTourSize()){
                    destinationCity = getCity(i+1);
                }
                else {
                    destinationCity = getCity(0);
                }

                tourDistance += fromCity.distanceToCity(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    public int getTourSize(){
        return tour.size();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("|");
        for (Object city:
             getTour()) {
            stringBuilder.append(city + "|");

        }
        return stringBuilder.toString();
    }
}

package com.company.firefly_algorithm;

import com.company.Point;

/**
 * Created by Krzysztof Pikóra on 05.06.17.
 */
public class Firefly extends Point {
    private double lightIntensity;

    /**
     * Create firefly with position & light intensity value in given position
     * @param x coordinate
     * @param y coordinate
     * @param lightIntensity value
     */
    public Firefly(double x, double y, double lightIntensity){
        super(x, y);
        this.lightIntensity = lightIntensity;
    }

    public double getLightIntensity() {
        return lightIntensity;
    }

}

package com.company.firefly_algorithm;

/**
 * Created by Krzysztof Pikóra on 05.06.17.
 */
public class Firefly {
    private double lightIntensity;
    private double x;
    private double y;

    /**
     * Create firefly with position & light intensity value in given position
     * @param x coordinate
     * @param y coordinate
     * @param lightIntensity value
     */
    public Firefly(double x, double y, double lightIntensity){
        this.x = x;
        this.y = y;
        this.lightIntensity = lightIntensity;
    }

    public double getLightIntensity() {
        return lightIntensity;
    }

    /**
     * @return firefly's x coordinate
     */
    public double getX() {
        return x;
    }



    /**
     * @return firefly's y coordinate
     */
    public double getY() {
        return y;
    }


    public void setLightIntensity(double lightIntensity) {
        this.lightIntensity = lightIntensity;
    }



    /**
     * Set firefly's x coordinate
     * @param x coordinate
     */
    public void setX(double x) {
        this.x = x;
    }



    /**
     * Set firefly's y coordinate
     * @param y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }
}

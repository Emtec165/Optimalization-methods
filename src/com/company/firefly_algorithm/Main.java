package com.company.firefly_algorithm;

import com.company.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Krzysztof Pikóra on 05.06.17.
 *
 * Find function max / min value with firefly algorithm in which function is treated as light intensity function is space
 */
public class Main {

    private static final double alfa = 0.2, beta = 1.0, gamma = 0.5;

    public static void main(String[] args) {

        //YOU CAN CHANGE THIS
        int howManyFireflies = 30, numberOfIteration = 20000;


        List<Firefly> fireflyList = new ArrayList();
        Random randGener = new Random();

        /**
         * Create new fireflies with random position
         */
        for (int i = 0; i < howManyFireflies; i++){
            double x = randGener.nextDouble() * 2;
            double y = randGener.nextDouble() * 2;

            //f(x,y) = -(1 - x)^2 - 100(y - x^2)^2
            double lightIntensity = -Math.pow(1 - x, 2) - 100 * Math.pow((y - Math.pow(x,2)), 2);

            Firefly firefly = new Firefly(x, y, lightIntensity);
            fireflyList.add(firefly);
        }


        for (Firefly e: fireflyList) {
            System.out.printf("x:%.3f   y:%.3f   light intensity:%.3f\n", e.getX(), e.getY(), e.getLightIntensity());
        }
        System.out.println("\nComputing...\n");


        /**
         * Main computation
         */
        long startTime = System.nanoTime();
        for (int y = 0; y < numberOfIteration; y++) {
            /**
             * Compare whole combinations of fireflies.
             * Firefly with weaker light intensity jumps towards firefly with greater light intensity value
             */
            for (int i = 0; i < fireflyList.size(); i++) {
                for (int j = 0; j < fireflyList.size(); j++) {
                    Firefly firefly1 = fireflyList.get(i);
                    Firefly firefly2 = fireflyList.get(j);
                    if (firefly2.getLightIntensity() > firefly1.getLightIntensity()) {
                        firefly1 = updateFormula(firefly1, firefly2);// to avoid dividing by 0

                        fireflyList.remove(i);
                        fireflyList.add(i, firefly1);
                    }
                }
            }
        }
        long stopTime = System.nanoTime();

        for (Firefly e: fireflyList) {
            System.out.printf("x:%.3f   y:%.3f   light intensity:%.3f\n", e.getX(), e.getY(), e.getLightIntensity());
        }

        System.out.println("Execution time " + (stopTime - startTime) + " nanoseconds for " + howManyFireflies + " fireflies"
        + " and " + numberOfIteration + " iterations.");
    }

    private static Firefly updateFormula(Firefly firefly1, Firefly firefly2){
        Random random = new Random();

        double randomValue = -0.5 + (0.5 - -0.5) * random.nextDouble();
        double r = Math.sqrt(Math.pow(firefly1.getX() - firefly2.getX(), 2) + Math.pow(firefly1.getY() - firefly2.getY(), 2));

        double updatedX = firefly1.getX() + beta * Math.exp(-gamma * Math.pow(r, 2)) * (firefly2.getX() - firefly1.getX()) + alfa * randomValue;
        double updatedY = firefly1.getY() + beta * Math.exp(-gamma * Math.pow(r, 2)) * (firefly2.getY() - firefly1.getY()) + alfa * randomValue;

        double lightIntensity = -Math.pow(1 - updatedX, 2) - 100 * Math.pow((updatedY - Math.pow(updatedX,2)), 2);

        Firefly firefly = new Firefly(updatedX, updatedY, lightIntensity);

        return firefly;
    }
}

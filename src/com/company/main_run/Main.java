package com.company.main_run;

import com.company.firefly_algorithm.FireflyRun;
import com.company.particle_swarm_optimization.PSORun;
import com.company.simulated_annealing.SimulatedAnnealingRun;

import java.util.Scanner;

/**
 * Created by Krzysztof Pikóra on 10.09.17.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int algorithmToRun = 999;

        FireflyRun firefly = new FireflyRun();

        while (algorithmToRun != 0) {
            System.out.println("\nChoose an algorithm to run:");
            System.out.println("0) Exit.");
            System.out.println("1) Firefly algorithm.");
            System.out.println("11) Modify firefly algorithm variables.");
            System.out.println("2) Particle swarm optimization.");
            System.out.println("3) Simulated annealing.");

            try {
                algorithmToRun = scanner.nextInt();
            } catch (java.util.InputMismatchException e){
                scanner.nextLine();
            }

            switch (algorithmToRun) {
                case 0:
                    return;
                case 1:
                    firefly.run();
                    break;
                case 11:
                    try {
                        System.out.println("Set new fireflies count (default 30):");
                        firefly.setFIREFLIES_COUNT(scanner.nextInt());
                        System.out.println("Set new number of iterations (default 20000):");
                        firefly.setITERATIONS(scanner.nextInt());
                    } catch (java.util.InputMismatchException e){
                        scanner.nextLine();
                    }
                    break;
                case 2:
                    PSORun.run();
                    break;
                case 3:
                    SimulatedAnnealingRun.run();
                    break;
            }
        }
    }
}

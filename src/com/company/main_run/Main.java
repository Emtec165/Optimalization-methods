package com.company.main_run;

import com.company.firefly_algorithm.FireflyRun;
import com.company.particle_swarm_optimization.PSO;
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
        PSO pso = new PSO();

        while (algorithmToRun != 0) {
            System.out.println("\nChoose an algorithm to run:");
            System.out.println("0) Exit.");
            System.out.println("1) Firefly algorithm.");
            System.out.println("11) Modify firefly algorithm variables.");
            System.out.println("2) Particle swarm optimization.");
            System.out.println("22) Particle swarm optimization.");
            System.out.println("3) Simulated annealing.");
            System.out.println("33) Simulated annealing.");
            System.out.println("9) Reset application.");

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
                    pso.initializeSwarm();
                    pso.execute();
                    break;
                case 22:
                    try {
                        System.out.println("Set new swarm size (default 30):");
                        pso.setSWARM_SIZE(scanner.nextInt());
                        System.out.println("Set new number of iterations (default 600):");
                        pso.setITERATIONS(scanner.nextInt());
                    } catch (java.util.InputMismatchException e){
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    SimulatedAnnealingRun.run();
                    break;
                case 33:
                    break;
                case 9:
                    firefly = new FireflyRun();
                    pso = new PSO();
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }
}

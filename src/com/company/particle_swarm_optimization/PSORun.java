package com.company.particle_swarm_optimization;

/**
 * Created by Krzysztof Pikóra on 06.06.17.
 *
 * PSO - Particle Swarm Optimization
 */
public class PSORun {

    public static void run() {
        PSO pso = new PSO();
        pso.initializeSwarm();
        pso.execute();
    }
}

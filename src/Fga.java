import Model.Factory.IRealFunctionFactory;
import Model.Factory.RealFunctionFactory;
import Model.RealFunction;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Fga {

    public RealFunction execute(IRealFunctionFactory factory, int initialPop, int elitism, int generations) {
        List<RealFunction> initialPopulation = new ArrayList<>(initialPop);

        for (int i = 0; i < initialPop; i++)
            initialPopulation.add(factory.getNewFunction());

        for (int i = 0; i < generations; i++) {
            List<RealFunction> children = generateChildren(initialPopulation);

            List<RealFunction> mutants = new ArrayList<>(initialPop);
            for (RealFunction individual : initialPopulation)
                mutants.add(individual.mutate());

            List<RealFunction> join = new ArrayList<>(initialPop * 3);
            join.addAll(initialPopulation);
            join.addAll(children);
            join.addAll(mutants);

            List<RealFunction> newPop = new ArrayList<>(initialPop);
            newPop.addAll(elitism(join, elitism));
            join.removeAll(newPop);
            newPop.addAll(roulette(join, initialPop - elitism));

            RealFunction bestOfGeneration = newPop.get(0);
            System.out.println("Nº Geração: " + (i + 1) + "    Indivíduo: " + bestOfGeneration.toString() + "    Avaliação: " + bestOfGeneration.getEvaluation());

            initialPopulation = newPop;
        }

        return initialPopulation.get(0);
    }

    private List<RealFunction> elitism(List<RealFunction> individuals, int elitism) {
        Collections.sort(individuals, Comparator.comparingDouble(RealFunction::evaluate));
        return individuals.subList(0, elitism);
    }

    private List<RealFunction> roulette(List<RealFunction> individuals, int nExecutions) {
        List<RealFunction> rouletteIndividuals = new ArrayList<>();
        List<RealFunction> aux = new ArrayList<>();
        aux.addAll(individuals);

        for (int i = 0; i < nExecutions; i++) {
            double r = new Random().nextDouble() * summation(aux);
            double sum = 0.0;
            for (RealFunction individual : aux) {
                sum += (1 / individual.getEvaluation());
                if (sum >= r) {
                    rouletteIndividuals.add(individual);
                    aux.remove(individual);
                    break;
                }
            }
        }

        return rouletteIndividuals;
    }

    private double summation(List<RealFunction> individuals) {
        double summation = 0.0;

        for (RealFunction individual : individuals) {
            summation += (1 / individual.getEvaluation());
        }

        return summation;
    }

    private List<RealFunction> generateChildren(List<RealFunction> individuals) {
        List<RealFunction> children = new ArrayList<>();
        List<RealFunction> aux = new LinkedList<>();

        aux.addAll(individuals);
        int count = individuals.size();

        for (int i = 0; i < individuals.size() / 2; i++) {
            int[] numbers = ThreadLocalRandom.current().ints(0, count).distinct()
                    .limit(2).toArray();

            RealFunction p1 = aux.get(numbers[0] >= aux.size() ? numbers[0] - 1: numbers[0]);
            RealFunction p2 = aux.get(numbers[1] >= aux.size() ? numbers[1] - 1: numbers[1]);

            aux.remove(p1);
            aux.remove(p2);

            children.addAll(p1.recombine(p2));
            count -= 2;
        }

        return children;
    }

}

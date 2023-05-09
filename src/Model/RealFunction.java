package Model;

import Model.Factory.RealFunctionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class RealFunction {
    double[] genes;
    double mutationRate = 0.15;
    int genesAmount;
    double evaluation;
    int recombineAlgorithm;
    boolean evaluated = false;

    public RealFunction(int genesAmount, int recombineAlgorithm) {
        //this.genes = new double[genesAmount];
        this.genesAmount = genesAmount;
        this.recombineAlgorithm = recombineAlgorithm;
    }

    public List<RealFunction> recombine(RealFunction individual) {

        if (this.recombineAlgorithm == 1)
            return recombineCrossOver(individual);

        return recombineBLX(individual);
    }

    public List<RealFunction> recombineCrossOver(RealFunction individual) {
        RealFunction firstSon = new RealFunctionFactory().getNewFunction(this);
        RealFunction secondSon = new RealFunctionFactory().getNewFunction(this);

        for (int i = 0; i < genesAmount; i++) {
            firstSon.genes[i] = (1 - 0.33) * this.genes[i] + (0.33 * individual.genes[i]);
            secondSon.genes[i] = (1 - 0.33) * individual.genes[i] + (0.33 * this.genes[i]);
        }

        return Arrays.asList(firstSon, secondSon);
    }

    public List<RealFunction> recombineBLX(RealFunction individual) {
        RealFunction firstSon = new RealFunctionFactory().getNewFunction(this);
        RealFunction secondSon = new RealFunctionFactory().getNewFunction(this);

        for (int i = 0; i < genesAmount; i++) {
            double alpha = new Random().nextDouble();
            firstSon.genes[i] = this.genes[i] + alpha * Math.abs(this.genes[i] - individual.genes[i]);
            secondSon.genes[i] = individual.genes[i] + alpha * Math.abs(this.genes[i] - individual.genes[i]);
        }

        return Arrays.asList(firstSon, secondSon);
    }
    public RealFunction mutate() {
        RealFunction mutant = new RealFunctionFactory().getNewFunction(this);

        boolean alteredGene = false;
        Random random = new Random();

        for (int i = 0; i < this.genesAmount; i++) {
            double r = random.nextDouble();
            if (r < mutationRate) {
                mutant.genes[i] = this.genes[i] + noise();
                alteredGene = true;
            } else {
                mutant.genes[i] = this.genes[i];
            }
        }

        if (!alteredGene) {
            int i = random.nextInt(this.genesAmount);
            mutant.genes[i] = this.genes[i] + noise();
        }

        return mutant;
    }

    public abstract double evaluate();

    public double getEvaluation() {
        return this.evaluation;
    }

    private double noise() {
        Random random = new Random();
        return (random.nextGaussian());
    }

    public int getGenesAmount() {
        return genesAmount;
    }

    public int getRecombineAlgorithm() {
        return recombineAlgorithm;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        for (int i = 0; i < this.genes.length; i++) {
            if (i == this.genes.length - 1) {
                builder.append(this.genes[i]);
                break;
            }
            builder.append(this.genes[i] + ", ");
        }
        builder.append(" ]");

        return builder.toString();
    }
}
package Model;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class RastriginFunction extends RealFunction {

    public RastriginFunction(int genesAmount, int recombineAlgorithm) {
        super(genesAmount, recombineAlgorithm);
        this.genes = new double[genesAmount];
        for (int i = 0; i < genesAmount; i++) {
            double min = -5.12;
            double max = 5.12;
            this.genes[i] = min + new Random().nextDouble() * (max - min);
        }
    }

    @Override
    public double evaluate() {
        if (!evaluated) {
            int d = this.genes.length;
            double sum = 0.0;
            for (int i = 0; i < d; i++) {
                double xi = this.genes[i];
                sum += Math.pow(xi, 2) - 10 * Math.cos(2 * Math.PI * xi);
            }
            this.evaluation = 10 * d + sum;
            this.evaluated = true;
        }

        return this.evaluation;
    }
}

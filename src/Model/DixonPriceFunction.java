package Model;

import java.util.List;
import java.util.Random;

public class DixonPriceFunction extends RealFunction {

    public DixonPriceFunction(int genesAmount, int recombineAlgorithm) {
        super(genesAmount, recombineAlgorithm);
        this.genes = new double[genesAmount];
        for (int i = 0; i < genesAmount; i++) {
            double min = -10;
            double max = 10;
            this.genes[i] = min + new Random().nextDouble() * (max - min);
        }
    }

    @Override
    public double evaluate() {
        if (!this.evaluated) {
            int n = this.genes.length;
            double sum = Math.pow(this.genes[0] - 1, 2);
            for (int i = 1; i < n; i++) {
                double xi = this.genes[i];
                sum += (i + 1) * Math.pow((2 * xi * xi) - this.genes[i - 1], 2);
            }
            this.evaluation = sum;
            this.evaluated = true;
        }

        return this.evaluation;
    }
}

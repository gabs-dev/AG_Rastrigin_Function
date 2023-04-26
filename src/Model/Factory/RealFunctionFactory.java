package Model.Factory;

import Model.DixonPriceFunction;
import Model.RastriginFunction;
import Model.RealFunction;

public class RealFunctionFactory implements IRealFunctionFactory {

    private int genesAmount;
    private String functionType;
    private int recombineAlgorithm;

    public RealFunctionFactory(int genesAmount, String functionType, int recombineAlgorithm) {
        this.genesAmount = genesAmount;
        this.functionType = functionType;
        this.recombineAlgorithm = recombineAlgorithm;
    }

    public RealFunctionFactory() {

    }

    @Override
    public RealFunction getNewFunction() {
        if (this.functionType.equalsIgnoreCase("R"))
            return new RastriginFunction(this.genesAmount, this.recombineAlgorithm);

        if (this.functionType.equalsIgnoreCase("D"))
            return new DixonPriceFunction(this.genesAmount, this.recombineAlgorithm);

        return null;
    }

    @Override
    public RealFunction getNewFunction(RealFunction individual) {
        if (individual instanceof RastriginFunction)
            return new RastriginFunction(individual.getGenesAmount(), individual.getRecombineAlgorithm());
        else if (individual instanceof DixonPriceFunction)
            return new DixonPriceFunction(individual.getGenesAmount(), individual.getRecombineAlgorithm());

        return null;
    }
}

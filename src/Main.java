import Model.Factory.IRealFunctionFactory;
import Model.Factory.RealFunctionFactory;
import Model.RealFunction;

public class Main {
    public static void main(String[] args) {
        int population = 20;
        int elitism = 4;
        int genesAmount = 16;
        String functionType = "r";
        int recombineAlgorithm = 2;

        IRealFunctionFactory factory = new RealFunctionFactory(genesAmount, functionType, recombineAlgorithm);
        Fga fga = new Fga();

        RealFunction best = fga.execute(factory, population, elitism, 10000);
        System.out.println("-------------------------------------------");
        //System.out.println("Melhor Indivíduo: " + best + "\tAvaliação: " + best.getEvaluation());
        System.out.println("\tAvaliação: " + best.getEvaluation());
    }
}
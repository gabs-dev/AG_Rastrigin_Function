import Model.Factory.IRealFunctionFactory;
import Model.Factory.RealFunctionFactory;
import Model.RealFunction;

public class Main {
    public static void main(String[] args) {
        int population = 50;
        int elitism = 4;
        int genesAmount = 2;
        String functionType = "d";
        int recombineAlgorithm = 1;

        IRealFunctionFactory factory = new RealFunctionFactory(genesAmount, functionType, recombineAlgorithm);
        Fga fga = new Fga();

        RealFunction best = fga.execute(factory, population, elitism, 5000);
        System.out.println("-------------------------------------------");
        System.out.println("Melhor Indivíduo: " + best + "\tAvaliação: " + best.getEvaluation());
    }
}
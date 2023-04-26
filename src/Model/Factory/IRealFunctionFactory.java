package Model.Factory;

import Model.RealFunction;

public interface IRealFunctionFactory {

    RealFunction getNewFunction();
    RealFunction getNewFunction(RealFunction individual);

}

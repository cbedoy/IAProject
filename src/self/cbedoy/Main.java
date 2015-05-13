package self.cbedoy;

import self.cbedoy.services.CuadraticService;
import self.cbedoy.services.LinealRandomService;

public class Main {

    public static void main(String[] cbedoy) throws Exception {

        LinealRandomService linealRandomService = new LinealRandomService(LinealRandomService.class);
        CuadraticService cuadraticService       = new CuadraticService(CuadraticService.class);


        //Linear random service with a valid distribution
        linealRandomService.setSize(Short.MAX_VALUE);
        linealRandomService.buildRandomNumbers();
        linealRandomService.evaluatePseudoNumbers();
        linealRandomService.hasValidPseudoNumbersDistribution();

/*
        //13 random numbers generated with invalid distribution
        cuadraticService.setSize(50);
        cuadraticService.buildRandomNumbers();
        cuadraticService.evaluatePseudoNumbers();
        cuadraticService.hasValidPseudoNumbersDistribution();
        cuadraticService.clearBuild();

        //7 random number generated with invalid distribution
        cuadraticService.setSize(50);
        cuadraticService.buildRandomNumbers();
        cuadraticService.evaluatePseudoNumbers();
        cuadraticService.hasValidPseudoNumbersDistribution();
        cuadraticService.clearBuild();


        //50 random number generated with valid distribution
        cuadraticService.setSize(50);
        cuadraticService.buildRandomNumbers();
        cuadraticService.evaluatePseudoNumbers();
        cuadraticService.hasValidPseudoNumbersDistribution();
        cuadraticService.clearBuild();
*/
    }
}

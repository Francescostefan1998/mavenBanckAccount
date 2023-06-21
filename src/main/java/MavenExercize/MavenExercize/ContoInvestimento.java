package MavenExercize.MavenExercize;

import java.util.Random;

public class ContoInvestimento extends Conto {

	public ContoInvestimento(String titolare) {
        super(titolare);
        Random random = new Random();
        this.tasso = -0.5 + (random.nextDouble() * 1.0); // generates a random number between -0.5 and +0.5
    }

}

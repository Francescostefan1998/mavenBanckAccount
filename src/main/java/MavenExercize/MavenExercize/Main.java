package MavenExercize.MavenExercize;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		Conto cc = new ContoCorrente("Mario Rossi", LocalDate.of(2021, 1, 1), 1000);
		Conto cd = new ContoDeposito("Giovanni Bianchi", LocalDate.of(2021, 1, 1), 1000);
		Conto ci = new ContoInvestimento("Luigi Verdi", LocalDate.of(2021, 1, 1), 1000);

		cc.versa(500);
		cc.preleva(200);
		cd.versa(500);
		cd.preleva(200);
		ci.versa(500);
		ci.preleva(200);

		cc.generaInteressi(LocalDate.of(2021, 12, 31));
		cd.generaInteressi(LocalDate.of(2021, 12, 31));
		ci.generaInteressi(LocalDate.of(2021, 12, 31));

		System.out.println("Saldo CC al 31/12/2021: " + cc.saldo);
		System.out.println("Saldo CD al 31/12/2021: " + cd.saldo);
		System.out.println("Saldo CI al 31/12/2021: " + ci.saldo);

		cc.generaInteressi(LocalDate.of(2022, 12, 31));
		cd.generaInteressi(LocalDate.of(2022, 12, 31));
		ci.generaInteressi(LocalDate.of(2022, 12, 31));

		System.out.println("Saldo CC al 31/12/2022: " + cc.saldo);
		System.out.println("Saldo CD al 31/12/2022: " + cd.saldo);
		System.out.println("Saldo CI al 31/12/2022: " + ci.saldo);

		cc.generaInteressi(LocalDate.now());
		cd.generaInteressi(LocalDate.now());
		ci.generaInteressi(LocalDate.now());

		System.out.println("Saldo CC oggi: " + cc.saldo);
		System.out.println("Saldo CD oggi: " + cd.saldo);
		System.out.println("Saldo CI oggi: " + ci.saldo);
	}
}

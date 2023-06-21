package MavenExercize.MavenExercize;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContoDeposito extends Conto {

	public ContoDeposito(String titolare) {
		super(titolare);
		this.tasso = 0.10;

	}

	@Override
	public void preleva(double amount, LocalDate date) {
		if (amount > 1000.00) {
			throw new IllegalArgumentException("Withdrawal amount cannot be more than 1000.00");
		}
		super.preleva(amount, date);
	}

	@Override
	public void prelevaSecondExercize(double amount, LocalDate date, LocalDate previousDate) {
		if (amount > 1000.00) {
			throw new IllegalArgumentException("Withdrawal amount cannot be more than 1000.00");
		}
		super.prelevaSecondExercize(amount, date, previousDate);
	}
}

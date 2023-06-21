package MavenExercize.MavenExercize;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Conto {
	protected String titolare;
	protected LocalDate dataApertura;
	protected LocalDate dataOperation;
	protected double saldo;
	protected double tasso;
	protected double finalsaldo;
	protected LocalDate dataOfFirstInterest;
	protected LocalDate dataOfSecondInterest;
	protected LocalDate dataOfThirdInterest;
	protected double saldoOfFirstInterest;
	protected double saldoAfterOperation;
	protected double amountOfMoney;
	protected double saldoOfSecondInterest;
	protected double saldoOfThirdInterest;

	public Conto(String titolare) {
		this.titolare = titolare;
		this.dataApertura = LocalDate.of(2021, 1, 1);
		this.saldo = 1000.0;
		this.tasso = 0;
		this.finalsaldo = 1000.00;
		this.dataOfFirstInterest = LocalDate.of(2021, 12, 31);
		this.dataOfSecondInterest = LocalDate.of(2022, 12, 31);
		this.dataOfThirdInterest = LocalDate.now();
		this.saldoOfFirstInterest = 1000.000;
		this.saldoOfSecondInterest = 1000.000;
		this.saldoOfThirdInterest = 1000.0000;

		this.saldoAfterOperation = 1000.0000;
		this.amountOfMoney = 0.000;

	}

	public void setTasso(double tasso) {
		this.tasso = tasso;
	}

	public void prelevaSecondExercize(double amount, LocalDate date, LocalDate previousDate) {

		this.dataOperation = date;
		calculateInterestSecondExercize(date, amount, previousDate, true);

		stampaDatiEx2preleva();
	}

	public void versaSecondExercize(double amount, LocalDate date, LocalDate previousDate) {

		this.dataOperation = date;
		calculateInterestSecondExercize(date, amount, previousDate, false);

		stampaDatiEx2versa();
	}

	public void preleva(double amount, LocalDate date) {

		finalsaldo = 1000.000;
		this.dataOperation = date;
		calculateInterest(date, amount, true);

		// calculateInterest(LocalDate.now());
		stampaDati();
	}

	public void versa(double amount, LocalDate date) {
		finalsaldo = 1000.000;
		this.dataOperation = date;
		calculateInterest(date, amount, false);

		// calculateInterest(LocalDate.now());
		stampaDati();
	}

	protected void calculateInterestSecondExercize(LocalDate currentDate, double amount, LocalDate previousDate,
			Boolean t) {
		long totalDays = ChronoUnit.DAYS.between(previousDate, currentDate);
		long days = totalDays;
		this.finalsaldo = (saldoAfterOperation * (1.000 + this.tasso * (1.0000 - (365.000 - days) / 365.0000)));
		this.amountOfMoney = amount;
		this.saldo = finalsaldo;
		if (t == true) {
			this.saldoAfterOperation = this.finalsaldo - amount;

		} else {
			this.saldoAfterOperation = this.finalsaldo + amount;

		}

	}

	protected void calculateInterest(LocalDate currentDate, double amount, Boolean t) {
		double amount1 = amount;
		if (t == true) {
			amount1 = amount1 - amount - amount;
		} else {
			amount1 = amount1;
		}
		this.saldoOfFirstInterest = 1000.000;
		this.saldoOfSecondInterest = 1000.000;
		this.saldoOfThirdInterest = 1000.000;
		dataOperation = currentDate;
		long totalDays = ChronoUnit.DAYS.between(dataApertura, currentDate);

		if (currentDate.isBefore(dataOfFirstInterest)) {
			long days = totalDays;
			this.saldoOfFirstInterest = ((1000.000 * (1.000 + this.tasso * (1.0000 - (365.000 - days) / 365.0000))) + amount1)
					* (1.000 + this.tasso * (1.000 - days / 365.000));
			this.saldoOfSecondInterest = saldoOfFirstInterest * (1.0000 + this.tasso);
			this.saldoOfThirdInterest = saldoOfSecondInterest * (1.000 + this.tasso);
		} else if (currentDate.isBefore(dataOfSecondInterest) && currentDate.isAfter(dataOfFirstInterest)) {
			long days = totalDays - 365;
			this.saldoOfFirstInterest = 1000.000 * (1.000 + this.tasso);

			this.saldoOfSecondInterest = ((saldoOfFirstInterest * (1.000 + this.tasso * (1.000 - ((365.000 - days)/ 365.000))))
					+ amount1) * (1.000 + this.tasso * (1.000 - days/ 365.000));
			this.saldoOfThirdInterest = saldoOfSecondInterest * (1.000 + this.tasso);
		} else if (currentDate.isBefore(dataOfThirdInterest) && currentDate.isAfter(dataOfSecondInterest)) {
			long days = totalDays - 2 * 365;
			this.saldoOfFirstInterest = 1000.000 * (1.000 + this.tasso);
			this.saldoOfSecondInterest = saldoOfFirstInterest * (1.000 + this.tasso);
			this.saldoOfThirdInterest = ((saldoOfSecondInterest * (1.000 + this.tasso * (1.000 - ((365.000 - days) / 365.000))))
					+ amount1) * (1.000 + this.tasso * (1.000 - days / 365.000));
		}
	}

	public void stampaDati() {
		double number1 = saldoOfFirstInterest;

		DecimalFormat decimalFormat = new DecimalFormat("0.00");

		String formattedNumber1 = decimalFormat.format(number1);
		double number2 = saldoOfSecondInterest;

		DecimalFormat decimalFormat1 = new DecimalFormat("0.00");

		String formattedNumber2 = decimalFormat1.format(number2);

		double number3 = saldoOfThirdInterest;

		DecimalFormat decimalFormat2 = new DecimalFormat("0.00");

		String formattedNumber3 = decimalFormat2.format(number3);

		System.out.println("Titolare: " + titolare);
		System.out.println("Data apertura: " + dataOperation + ", saldo: 1000");
		System.out.println(dataOfFirstInterest + " - saldo : " + formattedNumber1);
		System.out.println(dataOfSecondInterest + " - saldo : " + formattedNumber2);
		System.out.println(dataOfThirdInterest + " - saldo : " + formattedNumber3);

	}

	public void stampaDatiEx2versa() {
		double number1 = saldo;

		DecimalFormat decimalFormat = new DecimalFormat("0.00");

		String formattedNumber1 = decimalFormat.format(number1);
		double number2 = saldoAfterOperation;

		DecimalFormat decimalFormat1 = new DecimalFormat("0.00");

		String formattedNumber2 = decimalFormat1.format(number2);

		System.out.println("--------------------------------------------------------------");
		System.out.println("Data operazione: " + dataOperation + ", saldo: " + formattedNumber1 + " operazione  : "
				+ " versa : " + this.amountOfMoney);
		System.out.println(" saldo dopo operazione : " + formattedNumber2);

	}

	
	public void lastOperation()  {
			long totalDays = ChronoUnit.DAYS.between( dataOperation, LocalDate.now());
			long days = totalDays;
			
			this.finalsaldo = (saldoAfterOperation * (1.000 + this.tasso * (1.0000 - (365.00 - days) / 365.0000)));
			
			this.saldo = finalsaldo;
			double number2 = saldo;

			DecimalFormat decimalFormat1 = new DecimalFormat("0.00");

			String formattedNumber2 = decimalFormat1.format(number2);

			System.out.println(" saldo alla data odierna : " + formattedNumber2 + " $$");
	}
	
	
	public void stampaDatiEx2preleva() {
		double number1 = saldo;

		DecimalFormat decimalFormat = new DecimalFormat("0.00");

		String formattedNumber1 = decimalFormat.format(number1);
		double number2 = saldoAfterOperation;

		DecimalFormat decimalFormat1 = new DecimalFormat("0.00");

		String formattedNumber2 = decimalFormat1.format(number2);

		System.out.println("--------------------------------------------------------------");
		System.out.println("Data operazione: " + dataOperation + ", saldo: " + formattedNumber1 + " operazione  : "
				+ " preleva : " + this.amountOfMoney);
		System.out.println(" saldo dopo operazione : " + formattedNumber2);

	}

}

package MavenExercize.MavenExercize;

import java.time.LocalDate;

public class ContoCorrente extends Conto {
	private double saldo;

	public ContoCorrente(String titolare) {
		super(titolare);
		this.tasso = 0.05; // 5% annual interest rate
	}
//	private static final double TASSO = 0.05;
//
//    public ContoCorrente(String titolare, LocalDate dataApertura, double saldo) {
//        super(titolare, dataApertura, saldo);
//    }
//
//    @Override
//    void generaInteressi(LocalDate date) {
//        long daysPassed = ChronoUnit.DAYS.between(dataApertura, date);
//        double interest = (saldo * TASSO / 365) * daysPassed;
//        saldo += interest;
//    }
}

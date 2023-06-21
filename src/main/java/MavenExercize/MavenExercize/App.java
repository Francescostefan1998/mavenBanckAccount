package MavenExercize.MavenExercize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App

{
	static Scanner scanner = new Scanner(System.in);
	static List<LocalDate> listOfDates = new ArrayList<>();
	static List<Integer> listOfAmountOfMoney = new ArrayList<>();

	public static void generateListOfDate() {
		for (int i = 0; i < 5; i++) {
			Random random = new Random();

			LocalDate startDate = LocalDate.of(2021, 1, 1);
			LocalDate endDate = LocalDate.now();
			long totalDays = ChronoUnit.DAYS.between(startDate, endDate);

			long randomDays = random.nextInt((int) totalDays);

			LocalDate randomDate = startDate.plusDays(randomDays);
			listOfDates.add(randomDate);
		}

	}

	public static void generateListOfRandomAmount() {
		for (int i = 0; i < 5; i++) {
			Random random = new Random();

			Integer randomNumber = random.nextInt((int) 150);

			listOfAmountOfMoney.add(randomNumber);
		}
	}

	public static void allTheOperationFlow() {

		System.out.println("Inserisca il suo nome per aprire un conto con noi");

		String name = scanner.nextLine();
		Conto conto = null;
		Connection con = null;

		try {
			con = DBHconnection.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM correntista WHERE nome = ?");
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				
				ps = con.prepareStatement("INSERT INTO correntista (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.executeUpdate();

				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id_correntista = rs.getInt(1);
					System.out.println("Benvenuto " + name + ", il suo id_correntista è " + id_correntista);
				} else {
					System.out.println("There was a problem with the database operation.");
				}
			} else {
				
				int id_correntista = rs.getInt("id_correntista");
				System.out.println("Benvenuto " + name + ", il suo id_correntista è " + id_correntista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				DBHconnection.getInstance().closeConnection();
			}
		}
		//System.out.println("Benvenuto " + name + " La sua registrazione è avvenuta con successo");
		generateListOfDate();
		System.out.println("In quale conto desidera effettuare le operazioni");
		System.out.println("A --- Conto Corrente");
		System.out.println("B --- Conto Deposito");
		System.out.println("C --- Conto Investimento");

		while (true) {
			String kindOfOperation = scanner.nextLine();

			if (kindOfOperation.equals("A")) {
				conto = new ContoCorrente(name);
				break;
			} else if (kindOfOperation.equals("B")) {
				conto = new ContoDeposito(name); // Assuming you have ContoDeposito class
				break;
			} else if (kindOfOperation.equals("C")) {
				conto = new ContoInvestimento(name); // Assuming you have ContoInvestimento class
				break;
			} else {
				System.out.println("Per favore inserisca A, B o C");
			}
		}
		// Perform operations on the account
		generateListOfRandomAmount();
		for (int i = 0; i < 5; i++) {
			LocalDate date = listOfDates.get(i);
			Integer amount = listOfAmountOfMoney.get(i);
			System.out.println("Versa: " + (amount) + " in data: " + date);
			conto.versa(amount, date); // Deposits random amounts at random dates
			try {
				Thread.sleep(400); // Pause for 200 milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("------------------------------------------------------");
			try {
				Thread.sleep(400); // Pause for 200 milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Preleva: " + (amount / 2) + " in data: " + date); // Display the withdrawal amount
			conto.preleva(amount / 2, date); // Withdraws half the deposited amount at the same dates
			try {
				Thread.sleep(400); // Pause for 200 milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("------------------------------------------------------");
			try {
				Thread.sleep(400); // Pause for 200 milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sortDates();
		System.out.println("-----------------Exercize -- 2  ---------------------------------------");
		System.out.println("  ");
		for (int i = 0; i < 5; i++) {
			LocalDate date = listOfDates.get(i);
			LocalDate previousDate = i != 0 ? listOfDates.get(i - 1) : LocalDate.of(2021, 1, 1);
			Integer amount = listOfAmountOfMoney.get(i);

			if (i % 2 == 0) { // If 'i' is even
				System.out.println("Versa: " + (amount) + " in data: " + date);
				conto.versaSecondExercize(amount, date, previousDate);
			} else { // If 'i' is odd
				System.out.println("Preleva: " + (amount / 2) + " in data: " + date);
				conto.prelevaSecondExercize(amount / 2, date, previousDate);
			}

			try {
				Thread.sleep(400); // Pause for 200 milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("  ");
			System.out.println("------------------------------------------------------");
			try {
				Thread.sleep(400); // Pause for 200 milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		conto.lastOperation();

	}

	public static void sortDates() {
		Collections.sort(listOfDates);
	}

	public static void main(String[] args) {
		allTheOperationFlow();
	}
}

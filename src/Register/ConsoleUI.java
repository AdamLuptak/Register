package Register;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * USer inter-face
 */
public class ConsoleUI implements Interface {
	/** register.Register of persons. */
	private Register1 register;
	private int dlzka = 0;
	private FileRegisterLoader registerLoader = new FileRegisterLoader();
	private RegisterLoader rl;
	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * tu je menu podla vyberu si vieme vybrat zo switch
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
	};

	public ConsoleUI(Register1 register) {
		this.register = register;
		run();
	}

	public ConsoleUI(RegisterLoader rl) {
		this.rl = rl;
		this.register = rl.load();
		// this.register = registerLoader.load();

		if (register == null) {
			System.out.println("Vyberte interfacu\n1 pre POLE \n2 pre LIST");
			String h = readLine();
			switch (h) {
			case "1":
				register = new ArrayRegister(20);
				break;
			case "2":
				register = new ListRegister(20);
				break;
			default:
				return;
			}
			rl.fill(register);
		}

	}

	public void run() {
		while (true) {
			switch (showMenu()) {
			case PRINT:
				clc(50);
				printRegister();
				break;
			case ADD:
				clc(50);
				addToRegister();
				clc(50);
				break;
			case UPDATE:
				clc(50);
				updateRegister();
				clc(50);
				break;
			case REMOVE:
				clc(50);
				removeFromRegister();
				clc(50);
				break;
			case FIND:
				clc(50);
				findInRegister();
				break;
			case EXIT:
				clc(50);
				rl.save(register);
				// registerLoader.save(register);

				System.out.println("CAv koniec");
				return;
			}
		}
	}

	private void uloz() {
		try (FileOutputStream fout = new FileOutputStream("address.bin");
				ObjectOutputStream oos = new ObjectOutputStream(fout)) {
			// FileOutputStream fout = new
			// FileOutputStream("c:\\Users\\adam\\Documents\\address.ser");//
			oos.writeObject(register);
			System.out.println("Ulozil");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * nacitanie z Console
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * zobrazi Menu
	 */
	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());

		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	public int getLongestString() {
		int maxLength = 0;
		for (int index = 0; index < register.getCount(); index++) {
			if (register.getPerson(index).getName().length() > maxLength) {
				maxLength = register.getPerson(index).getName().length();
			}
		}
		return maxLength;
	}

	public int getLongestPhone() {
		int maxLength = 0;
		for (int index = 0; index < register.getCount(); index++) {
			if (register.getPerson(index).getPhoneNumber().length() > maxLength) {
				maxLength = register.getPerson(index).getPhoneNumber().length();
			}
		}
		return maxLength;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Register.Interface#printRegister()
	 */
	@Override
	public void printRegister() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		System.out.println(" ");
		// System.out.println(getLongestPhone());
		for (int index = 0; index < register.getCount(); index++) {
			formatter.format("%s %2$-" + getLongestString() + "s %3$" + getLongestPhone() + "s %n", index + 1,
					register.getPerson(index).getName(), register.getPerson(index).getPhoneNumber());
		}
		if (register.getCount() == 0) {
			System.out.println("prazdny zoznam");
		} else {
			System.out.println(sb.toString());
		}
		System.out.println(" ");
	}

	private void addToRegister() {
		System.out.println("Enter Name: ");
		String name = readLine();
		System.out.println("Enter Phone Number: ");
		String phoneNumber = readLine();
		if (register.ochrana(name, phoneNumber)) {
			try {
				register.addPerson(new Person(name, phoneNumber));
			} catch (RuntimeException e) {
				System.err.println(e);
			}
		} else {
			System.out.println("Takyto pouzivatel uz ecxistuje");
		}
	}

	/**
	 * Update register
	 */
	void updateRegister() {
		System.out.println("Zadajte index pouzivatela ktoreho chcete zmenit: ");
		int index = 0;
		try {
			index = Integer.parseInt(readLine());
		} catch (Exception e) {
			System.out.println("Zadana neplatna volba");
			return;
		}
		System.out.println("Zmena mena zadajte Meno:");
		String meno = readLine();
		Person person = register.getPerson(index - 1);
		person.setName(meno);
		System.out.println("Zmena Telcisla zadajte cislo:");
		String cislo = readLine();
		try {
			person.setPhoneNumber(cislo);
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	/**
	 * Hlada v poli cislo a meno
	 */
	public void findInRegister() {
		System.out.println("Meno hladaneho:");
		String meno = readLine();
		System.out.println("Cislo Hladaneho");
		String cislo = readLine();

		if (register.findPersonByName(meno) != null && register.findPersonByPhoneNumber(cislo) != null) {
			System.out.print("Meno cislo hladaneho :   ");
			System.out.println(register.findPersonByName(meno).toString());
			System.out.println(" ");
		} else {
			System.out.println("nenadchadza sa tune");
			System.out.println(" ");
		}
	}

	private void removeFromRegister() {
		System.out.println("Enter index: ");
		int index = 0;
		try {
			index = Integer.parseInt(readLine());
		} catch (Exception e) {
			System.out.println("Zadana neplatna volba");
			return;
		}

		Person person = register.getPerson(index - 1);
		register.removePerson(person);
	}

	private static void clc(int pocet) {
		for (int i = 0; i < pocet; i++) {
			System.out.println("");
		}

	}
}

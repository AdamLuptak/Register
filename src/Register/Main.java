package Register;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Adam Luptak 2015
 */
public class Main {
	private static Register1 register;
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		/**
		 * mozeme si vybrat riesenie bud cez List akebo cez Pole
		 */
		// FileInputStream fin = new
		// FileInputStream("c:\\Users\\adam\\Documents\\address.ser");
		try(FileInputStream fin = new FileInputStream("address.bin");
				ObjectInputStream ois = new ObjectInputStream(fin)) {
			Register1 register = (Register1) ois.readObject();
			ConsoleUI ui = new ConsoleUI(register);
			clc(50);
			// ui.run();
		} catch (Exception e) {
			System.out.println("neexistuje subor");

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
				System.exit(0);
			}
			register.addPerson(new Person("Aankolist ht", "0900123456"));
			register.addPerson(new Person("Aasht", "0900123456"));
			register.addPerson(new Person("Zht", "0900123456"));
			register.addPerson(new Person("Daddnkolistht", "0900123456"));
			register.addPerson(new Person("B", "0"));
			ConsoleUI ui = new ConsoleUI(register);
			clc(50);
			ui.run();
		}
		System.out.println("sdsdg");
	}

	private static String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	private static void clc(int pocet) {
		for (int i = 0; i < pocet; i++) {
			System.out.println("");
		}

	}

}

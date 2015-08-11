package Register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainRegisterDnu {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	
	public static void main(String[] args){  
		RegisterLoader rl = null;
		System.out.println("Vyberete si typ nacitania zo suboru alebo z databazi");
		String h = readLine();
		switch (h) {
		case "1":
			rl = new FileRegisterLoader();
			break;
		case "2":
			rl = new DatabaseRegisterLoader();
			break;
		default:
			System.exit(0);
		}
		
		ConsoleUI ui = new ConsoleUI(rl);
	    ui.run();
	}
	
	private static String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
}

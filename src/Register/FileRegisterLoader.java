package Register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileRegisterLoader implements RegisterLoader {
	private static final String ADDRESS_BIN = "address.bin";
	private static final String REGISTER_FILE = "addres.bin";

	/* (non-Javadoc)
	 * @see Register.RegisterLoader#save(Register.Register1)
	 */
	@Override
	public void save(Register1 register) {
		try (FileOutputStream fout = new FileOutputStream(ADDRESS_BIN);
				ObjectOutputStream oos = new ObjectOutputStream(fout)) {
			// FileOutputStream fout = new
			// FileOutputStream("c:\\Users\\adam\\Documents\\address.ser");//
			oos.writeObject(register);
			System.out.println("Ulozil");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see Register.RegisterLoader#load()
	 */
	@Override
	public Register1 load() {
		File file = new File(ADDRESS_BIN);

		if (!file.exists()) {
			return null;
		}
		try (FileInputStream fin = new FileInputStream(ADDRESS_BIN);
				ObjectInputStream ois = new ObjectInputStream(fin)) {
			Register1 register = (Register1) ois.readObject();
			return register;
		} catch (Exception e) {
			System.out.println("neexistuje subor");
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see Register.RegisterLoader#fill(Register.Register1)
	 */
	@Override
	public void fill(Register1 register) {
		register.addPerson(new Person("Aankolist ht", "0900123456"));
		register.addPerson(new Person("Aasht", "0900123456"));
		register.addPerson(new Person("Zht", "0900123456"));
		register.addPerson(new Person("Daddnkolistht", "0900123456"));
		register.addPerson(new Person("B", "0"));
	}
}

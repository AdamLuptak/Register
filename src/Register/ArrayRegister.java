package Register;

import java.io.Serializable;

/**
 * robene cez Pole
 */
public class ArrayRegister implements Register1,Serializable {
	/** register.Person array. */
	private Person[] persons;
	private int dlzka;
	public static int countt = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Register.Register1#getPersons()
	 */
	@Override
	public Person[] getPersons() {
		return persons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Register.Register1#setPersons(Register.Person, int)
	 */
	@Override
	public void setPersons(Person persons, int index) {

		this.persons[index] = persons;
	}

	/** Number of persons in this register. */
	private int count;

	/**
	 * vytvori sa register s maximalnou dlzkou ktoru zadame
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Register.Register1#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/**
	 * Vrati dlzku pola persons
	 */
	@Override
	public int getSize() {
		return persons.length;
	}

	@Override
	public Person getPerson(int index) {
		return persons[index];
	}

	@Override
	public void addPerson(Person person) {
		persons[count] = person;
		count++;
	}

	/**
	 * neumozni zadat rovnake meno a cislo ak uz existuje v zaznamoch
	 */
	@Override
	public boolean ochrana(String name, String phoneNumber) {
		for (int i = 0; i < getCount(); i++) {
			if (name.equals(persons[i].getName()) && phoneNumber.equals(persons[i].getPhoneNumber())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Tato funkcia vrati objekt typu person ktory je zhodny z objektom pesron
	 * na vstupe
	 */
	@Override
	public Person findPersonByName(String name) {
		for (int i = 0; i < count; i++) {

			String h = persons[i].getName();
			if (name.equals(h)) {
				return persons[i];
			}
		}
		return null;
	}

	/**
	 * Tato funkcia najde osobu s pozadvanym telefonny cislom na vstupe
	 */
	@Override
	public Person findPersonByPhoneNumber(String number) {
		for (int i = 0; i < count; i++) {
			String h = persons[i].getPhoneNumber();
			if (number.equals(h)) {
				return persons[i];
			}
		}
		return null;
	}

	/**
	 * Tato funkcia vymaze s person zaznam, skutocnosit len presunie prvky
	 */
	@Override
	public void removePerson(Person person) {
		for (int index = 0; index < persons.length; index++) {
			if (this.persons[index].equals(person)) {
				for (int j = index; j < count; j++) {
					persons[j] = persons[j + 1];
				}
				count--;
				break;
			}
		}
	}
}
package Register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListRegister implements Register1, Serializable {

	private List<Person> persons = new ArrayList<Person>();;
	private int count;

	public Iterator<Person> iterator() {
		return persons.iterator();
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public ListRegister(int size) {
		//persons = new ArrayList<Person>();
		count = 0;
	}

	@Override
	public Person[] getPersons() {
		return null;
	}

	@Override
	public void setPersons(Person persons, int index) {
		Person person = this.persons.get(index);
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public int getSize() {
		return persons.size();
	}

	@Override
	public Person getPerson(int index) {
		Collections.sort(persons);
		return this.persons.get(index);

	}

	@Override
	public void addPerson(Person person) {
		persons.add(person);
	//	System.out.println("idem totosdsdsdf list");
		count++;
	}

	@Override
	public boolean ochrana(String name, String phoneNumber) {
		Iterator<Person> it = iterator();// bez toho vracia len objekty
		while (it.hasNext()) {
			Person p = it.next();
			if (name.equals(p.getName()) && phoneNumber.equals(p.getPhoneNumber())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Person findPersonByName(String name) {
		Iterator<Person> it = iterator();// bez toho vracia len objekty
		while (it.hasNext()) {
			Person p = it.next();
			if (name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Person findPersonByPhoneNumber(String number) {
		Iterator<Person> it = iterator();// bez toho vracia len objekty
		while (it.hasNext()) {
			Person p = it.next();
			if (number.equals(p.getPhoneNumber())) {
				return p;
			}
		}
		return null;
	}

	@Override
	public void removePerson(Person person) {
		Iterator<Person> it = iterator();// bez toho vracia len objekty
		int pocitadlo = 0;
		while (it.hasNext()) {
			if (it.next().equals(person)) {
				pocitadlo++;
			}
		}
		count--;
		if (count != 0) {
			persons.remove(pocitadlo);
		}
	}

}

package contacts;

import java.io.Serializable;
import java.time.LocalDate;

class Person extends Contact implements Serializable {

	private static final long serialVersionUID = 10L;

	private String name;

	private String surname;

	private LocalDate birthDate;

	private String gender;

	Person(String phoneNumber, String name, String surname, String birthDate, String gender) {

		super(phoneNumber);
		this.name = name;
		this.surname = surname;

		if (!birthDate.isEmpty()) {
			this.birthDate = LocalDate.parse(birthDate);
		}
		this.gender = gender;
	}

	private String getName() {

		return name;
	}

	private void setName(String name) {

		this.name = name;
	}

	@Override
	String getInfo() {

		return "Name: " +
				getName() +
				"\n" +
				"Surname: " +
				getSurname() +
				"\n" +
				"Birth date: " +
				(getBirthDate() == null ? "[no data]" : getBirthDate().toString()) +
				"\n" +
				"Gender: " +
				((getGender() == null || getGender().equals("")) ? "[no data]" : getGender()) +
				"\n" +
				"Number: " +
				getPhoneNumber() +
				"\n" +
				"Time created: " +
				getTimeCreated().toString() +
				"\n" +
				"Time last edit: " +
				getTimeModified().toString();
	}

	@Override
	String allFieldsToString() {

		return getName() + " " + getSurname() + " " + getPhoneNumber() + " " + getBirthDate() + " " + getGender();
	}

	private String getSurname() {

		return surname;
	}

	private void setSurname(String surname) {

		this.surname = surname;
	}

	private LocalDate getBirthDate() {

		return birthDate;
	}

	private void setBirthDate(String birthDate) {

		try {
			this.birthDate = LocalDate.parse(birthDate);
		} catch (Exception e) {
			System.out.println("Bad birth date!");
			this.birthDate = null;
		}

	}

	private String getGender() {

		return gender;
	}

	private void setGender(String gender) {

		if (gender.equals("M") || gender.equals("F")) {
			this.gender = gender;
		} else {
			System.out.println("Bad gender!");
		}

	}

	@Override
	public String toString() {

		return getName() + " " + getSurname();
	}

	@Override
	public String getAllPossibleFields() {

		return "name, surname, birth, gender, number";
	}

	@Override
	public void changeFieldsValue(String field, String newValue) {

		switch (field) {
			case "name":
				setName(newValue);
				break;
			case "surname":
				setSurname(newValue);
				break;
			case "birth":
				setBirthDate(newValue);
				break;
			case "gender":
				setGender(newValue);
				break;
			case "number":
				setPhoneNumber(newValue);
				break;
			default:
				System.out.println("Wrong field name!");
		}
	}

	@Override
	public String getFieldValue(String field) {

		switch (field) {
			case "name":
				return getName();
			case "surname":
				return getSurname();
			case "birth":
				return getBirthDate().toString();
			case "gender":
				return getGender();
			case "number":
				return getPhoneNumber();
			default:
				return "";
		}
	}
}

package contacts;

import java.io.Serializable;

public class Organization extends Contact implements Serializable {

	private static final long serialVersionUID = 3L;

	private String name;

	private String address;

	Organization(String phoneNumber, String name, String address) {

		super(phoneNumber);
		this.name = name;
		this.address = address;
	}

	@Override
	String getInfo() {

		return "Organization name: " +
				getName() +
				"\n" +
				"Address: " +
				getAddress() +
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

		return getName() + " " + getPhoneNumber() + " " + getAddress();
	}

	private String getName() {

		return name;
	}

	private void setName(String name) {

		this.name = name;
	}

	private String getAddress() {

		return address;
	}

	private void setAddress(String address) {

		this.address = address;
	}

	@Override
	public String toString() {

		return getName();
	}

	@Override
	public String getAllPossibleFields() {

		return "name, address, number";
	}

	@Override
	public void changeFieldsValue(String field, String newValue) {

		switch (field) {
			case "name":
				setName(newValue);
				break;

			case "address":
				setAddress(newValue);
				break;
			case "number":
				setPhoneNumber(newValue);
				break;
		}
	}

	@Override
	public String getFieldValue(String field) {

		switch (field) {
			case "name":
				return getName();
			case "address":
				return getAddress();
			case "number":
				return getPhoneNumber();
			default:
				return "";
		}
	}
}
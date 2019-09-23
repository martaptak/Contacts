package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

abstract class Contact implements Utils, Serializable {

	private static final long serialVersionUID = 7L;

	private String phoneNumber;

	private LocalDateTime timeCreated;

	private LocalDateTime timeModified;

	Contact(String phoneNumber) {

		setPhoneNumber(phoneNumber);
		this.timeCreated = LocalDateTime.now();
		this.timeModified = LocalDateTime.now();
	}

	abstract String getInfo();

	private boolean hasANumber() {

		return !this.phoneNumber.equals("");
	}

	String getPhoneNumber() {

		return phoneNumber;
	}

	void setPhoneNumber(String phoneNumber) {

		if (isNumberValid(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			System.out.println("Wrong number format!  " + phoneNumber);
			this.phoneNumber = "";
		}
	}

	private boolean isNumberValid(String phoneNumber) {

		String pattern = "((^[+]*[(]?\\w+[)]?([-\\s]\\w{2,})?)|(^[+]*\\w+([-\\s][(]?\\w{2,}[)]?)?))([-\\s]\\w{2,})*$";

		return Pattern.compile(pattern).matcher(phoneNumber).matches();
	}

	LocalDateTime getTimeCreated() {

		return timeCreated;
	}

	LocalDateTime getTimeModified() {

		return timeModified;
	}

	void setTimeModified(LocalDateTime timeModified) {

		this.timeModified = timeModified;
	}

	abstract String allFieldsToString();
}





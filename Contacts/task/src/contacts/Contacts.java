package contacts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Contacts {

	private List<Contact> contactList = new ArrayList<>();

	void addRecord(Contact contact) {

		this.contactList.add(contact);
		System.out.println("The record added.");
	}

	void removeRecord(Contact contact) {

		this.contactList.remove(contact);
		System.out.println("This record removed!");
	}

	void getNumberOfContacts() {

		System.out.println("The Phone Book has " + contactList.size() + " records.");
	}

	void editContact(Contact contact) {

		ContactFactory.editContact(contact);

		contact.setTimeModified(LocalDateTime.now());
		}

	void printContacts() {

		for (int i = 0; i < contactList.size(); i++) {
			System.out.println((i + 1) + ". " + contactList.get(i).toString());
		}

	}

	Contact selectRecord(int index) {

		return contactList.get(index);
	}

	boolean isEmpty() {

		return contactList.isEmpty();
	}

	List<Contact> searchContacts(String query) {

		query = ".*" + query + ".*";

		List<Contact> results = new ArrayList<>();
		Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);

		getContactList().forEach(contact -> {
			Matcher matcher = pattern.matcher(contact.allFieldsToString());
			if (matcher.find()) {
				results.add(contact);
			}

		});

		System.out.println(String.format("Found %d results:", results.size()));

		for (int i = 0; i < results.size(); i++) {
			System.out.println((i + 1) + ". " + results.get(i).toString());
		}

		return results;
	}

	List<Contact> getContactList() {

		return contactList;
	}

}

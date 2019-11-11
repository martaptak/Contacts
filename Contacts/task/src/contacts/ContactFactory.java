package contacts;

class ContactFactory {

	private static Contact createContact(String type) {
		switch (type.toLowerCase()) {
			case "person":
				return createPerson();

			case "organization":
				return createOrganization();
			default:
				return null;
		}
	}

	private static Person createPerson() {
		System.out.println("Enter the name: ");
		String name = Main.scanner.nextLine();
		System.out.println("Enter the surname: ");
		String surname = Main.scanner.nextLine();
		System.out.println("Enter the birth date:");
		String birthDay = Main.scanner.nextLine();
		System.out.println("Enter the gender (M, F):");
		String gender = Main.scanner.nextLine();
		System.out.println("Enter the number:");
		String phoneNumber = Main.scanner.nextLine();
		return new Person(phoneNumber, name, surname, birthDay, gender);

	}

	private static Organization createOrganization() {
		System.out.println("Enter the organization name:");
		String name = Main.scanner.nextLine();
		System.out.println("Enter the address:");
		String address = Main.scanner.nextLine();
		System.out.println("Enter the number:");
		String phoneNumber = Main.scanner.nextLine();

		return new Organization(phoneNumber, name, address);
	}

	static Contact addContact(String type) {

		Contact contact = createContact(type);

		if (contact == null) {
			System.out.println("Sorry, we are not able to create this kind of contact\n");
			return null;
		}
		return contact;
	}

	static void editContact(Contact contact) {

		System.out.println(String.format("Select a field (%s): ", contact.getAllPossibleFields()));
		String field = Main.scanner.nextLine();
		System.out.println(String.format("Enter %s: ", field));
		String newValue = Main.scanner.nextLine();
		contact.changeFieldValue(field, newValue);
		System.out.println("Saved");

	}


}



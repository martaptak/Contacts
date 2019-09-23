package contacts;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {

	static Scanner scanner = new Scanner(System.in);

	private static String file;

	private static boolean isExit = false;

	private static Contacts contacts = new Contacts();

	public static void main(String[] args) {

		databaseInit(args);
		while (!isExit) {
			mainMenu();
		}
	}

	private static void mainMenu() {

		System.out.println("[menu] Enter action (add, list, search, count, exit):");
		String answer = scanner.nextLine();

		switch (answer) {
			case "exit":
				try {
					writeToFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				isExit = true;
				return;
			case "add":
				add();
				System.out.println();
				break;
			case "count":
				contacts.getNumberOfContacts();
				System.out.println();
				break;
			case "list":
				info();
				break;
			case "search":
				search();
				break;
		}

	}

	private static void search() {

		System.out.println("Enter search query: ");
		String query = scanner.nextLine();
		List<Contact> results = contacts.searchContacts(query);

		if (!results.isEmpty()) {
			searchMenu(results);
		}

	}

	private static void searchMenu(List<Contact> results) {

		System.out.println();
		System.out.println("[search] Enter action ([number], back, again): ");
		String action = scanner.nextLine();

		if (action.matches("^[0-9]*$")) {
			Contact contact = results.get(Integer.parseInt(action) - 1);
			System.out.println(contact.getInfo());
			recordMenu(contact);
		} else if ("again".equals(action)) {
			search();
		} else if ("back".equals(action)) {
			System.out.println();
			mainMenu();
		}

	}

	private static void recordMenu(Contact contact) {

		System.out.println();
		System.out.println("[record] Enter action (edit, delete, menu): ");
		String action = scanner.nextLine();

		switch (action) {
			case "edit":
				edit(contact);
				break;
			case "delete":
				remove(contact);
				break;
			case "menu":
				mainMenu();
				break;

		}
	}

	private static void databaseInit(String[] args) {

		if (args.length > 0) {
			file = args[0];
			try {
				readFile();
				System.out.println("Open " + file);
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Error file doesn't exists");
				file = "phonebook.db";
			//	System.out.println("Create " + file);
			}
		} else {
			file = "phonebook.db";
		//	System.out.println("Create " + file);

		}

	}

	private static void add() {

		System.out.println("Enter the type (person, organization):");
		String type = scanner.nextLine();
		Contact contact = ContactFactory.addContact(type);
		contacts.addRecord(contact);
	}

	private static void remove(Contact contact) {

		contacts.removeRecord(contact);
		recordMenu(contact);

	}

	private static void edit(Contact contact) {

		contacts.editContact(contact);
		System.out.println(contact.getInfo());

		recordMenu(contact);
	}

	private static void info() {

		contacts.printContacts();
		infoMenu();


	}

	private static void infoMenu() {

		System.out.println();
		System.out.println("[list] Enter action ([number], back): ");
		String action = scanner.nextLine();

		if (action.matches("^[0-9]*$")) {
			Contact contact = contacts.getContactList().get(Integer.parseInt(action) - 1);
			System.out.println(contact.getInfo());
			recordMenu(contact);
		} else if ("back".equals(action)) {
			System.out.println();
			mainMenu();
		}

	}

	@SuppressWarnings("unchecked")
	private static void readFile() throws ClassNotFoundException, IOException {

		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		List<Object> input = (List<Object>) is.readObject();
		for (Object obj : input) {
			contacts.addRecord((Contact) obj);
		}

		is.close();
	}

	private static void writeToFile() throws IOException {

		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(contacts.getContactList());
		os.close();

	}
}


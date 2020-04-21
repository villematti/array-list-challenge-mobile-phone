package com.talviruusu;

import java.util.ArrayList;
import java.util.Scanner;

public class MobilePhone {

    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Contact> contacts = new ArrayList<>();

    public void start() {
        boolean powerOn = true;

        displayOptions();

        while(powerOn) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action) {
                case 1:
                    displayContacts();
                    break;
                case 2:
                    showContact();
                    break;
                case 3:
                    newContactAction();
                    break;
                case 4:
                    modifyContactAction();
                    break;
                case 5:
                    removeContact();
                    break;
                case 6:
                    displayOptions();
                    break;
                case 7:
                    System.out.println("Good bye!");
                    powerOn = false;
                    break;
            }
        }
    }

    private void showContact() {
        System.out.println("Give a name of the contact:");
        String name = scanner.nextLine();
        Contact foundContact = getContactByName(name);
        if (foundContact != null) {
            System.out.println("Name: " + foundContact.getName() + " Number: " + foundContact.getNumber());
        } else {
            System.out.println("Can't find that contact!");
        }
    }

    private void removeContact() {
        System.out.println("Give name of the contact you want to remove:");
        String name = scanner.nextLine();
        if(removeContactByName(name)) {
            System.out.println("Contact was removed!");
        } else {
            System.out.println("Can't find that contact!");
        }
    }

    private void modifyContactAction() {
        System.out.println("Which contact you want to modify?");
        String currentName = scanner.nextLine();
        System.out.println("Give new name: ");
        String newName = scanner.nextLine();
        System.out.println("Give new number: ");
        String newNumber = scanner.nextLine();
        if (updateContact(currentName, newName, newNumber)) {
            System.out.println("Contact updated!");
        } else {
            System.out.println("Can't find contact you are looking for or new contact already exists!");
        }
    }

    private void newContactAction() {
        System.out.println("Give contact name: ");
        String newName = scanner.nextLine();
        System.out.println("Give contact number: ");
        String newNumber = scanner.nextLine();
        if(!addNewContact(newName, newNumber)) {
            System.out.println("Can't add that to contact list!!");
        }
    }

    private void displayOptions() {
        System.out.println("Choose your action:");
        System.out.println("1. List all contacts");
        System.out.println("2. Show contact");
        System.out.println("3. Add new contact");
        System.out.println("4. Modify contact");
        System.out.println("5. Remove contact");
        System.out.println("6. View this list");
        System.out.println("7. Turn off phone");
    }

    private
    void displayContacts() {
        for(Contact contact : contacts) {
            System.out.println("Name: " + contact.getName() + " Number: " + contact.getNumber());
        }
    }

    private Contact getContactByName(String name) {
        return findContact(name);
    }

    private boolean addNewContact(String name, String number) {
        if (!checkIfUnique(name)) return false;

        contacts.add(new Contact(name, number));
        System.out.println("Contact added successfully!");
        return true;
    }

    private boolean updateContact(String name, String newName, String newNumber) {
        Contact currentContact = findContact(name);
        if (currentContact == null) {
            return false;
        } else if (findContact(newName) != null) {
            return false;
        }

        int index = contacts.indexOf(currentContact);

        currentContact.setName(newName);
        currentContact.setNumber(newNumber);

        contacts.set(index, currentContact);

        return true;
    }

    private boolean removeContactByName(String name) {
        Contact currentContact = findContact(name);
        if (currentContact == null) return false;

        contacts.remove(currentContact);

        return true;
    }

    private boolean checkIfUnique(String name) {
        return findContact(name) == null;
    }

    private Contact findContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }
}

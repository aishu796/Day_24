import java.util.*;

class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Contact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName +
                "\nAddress: " + address +
                "\nCity: " + city +
                "\nState: " + state +
                "\nZip: " + zip +
                "\nPhone Number: " + phoneNumber +
                "\nEmail: " + email + "\n";
    }
}

class AddressBook {
    private String name;
    private List<Contact> contacts;

    public AddressBook(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void editContact(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                // Update contact details
                break;
            }
        }
    }

    public void deleteContact(String firstName, String lastName) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                iterator.remove();
                break;
            }
        }
    }

    public List<Contact> searchContactsByName(String name) {
        List<Contact> searchResults = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(name) || contact.getLastName().equals(name)) {
                searchResults.add(contact);
            }
        }
        return searchResults;
    }

    public List<Contact> searchContactsByCity(String city) {
        List<Contact> searchResults = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getCity().equals(city)) {
                searchResults.add(contact);
            }
        }
        return searchResults;
    }

    public List<Contact> searchContactsByState(String state) {
        List<Contact> searchResults = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getState().equals(state)) {
                searchResults.add(contact);
            }
        }
        return searchResults;
    }

    public Map<String, List<Contact>> viewContactsByCity() {
        Map<String, List<Contact>> contactsByCity = new HashMap<>();
        for (Contact contact : contacts) {
            String city = contact.getCity();
            if (contactsByCity.containsKey(city)) {
                contactsByCity.get(city).add(contact);
            } else {
                List<Contact> cityContacts = new ArrayList<>();
                cityContacts.add(contact);
                contactsByCity.put(city, cityContacts);
            }
        }
        return contactsByCity;
    }

    public Map<String, List<Contact>> viewContactsByState() {
        Map<String, List<Contact>> contactsByState = new HashMap<>();
        for (Contact contact : contacts) {
            String state = contact.getState();
            if (contactsByState.containsKey(state)) {
                contactsByState.get(state).add(contact);
            } else {
                List<Contact> stateContacts = new ArrayList<>();
                stateContacts.add(contact);
                contactsByState.put(state, stateContacts);
            }
        }
        return contactsByState;
    }

    public int getContactCountByCity(String city) {
        int count = 0;
        for (Contact contact : contacts) {
            if (contact.getCity().equals(city)) {
                count++;
            }
        }
        return count;
    }

    public int getContactCountByState(String state) {
        int count = 0;
        for (Contact contact : contacts) {
            if (contact.getState().equals(state)) {
                count++;
            }
        }
        return count;
    }

    public void sortContactsByName() {
        Collections.sort(contacts, Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName));
    }

    public void sortContactsByCity() {
        Collections.sort(contacts, Comparator.comparing(Contact::getCity));
    }

    public void sortContactsByState() {
        Collections.sort(contacts, Comparator.comparing(Contact::getState));
    }

    public void sortContactsByZip() {
        Collections.sort(contacts, Comparator.comparing(Contact::getZip));
    }

    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}

public class AddressBookMain {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook("My Address Book");

        // Add contacts
        Contact contact1 = new Contact("John", "Doe", "123 Main St", "City1", "State1", "12345", "1234567890", "john@example.com");
        addressBook.addContact(contact1);

        // Edit contact
        addressBook.editContact("John", "Doe");

        // Delete contact
        addressBook.deleteContact("John", "Doe");

        // Add multiple contacts
        Contact contact2 = new Contact("Alice", "Smith", "456 Elm St", "City2", "State2", "67890", "9876543210", "alice@example.com");
        addressBook.addContact(contact2);

        // Search for duplicate entries

        // Search contacts by name, city, or state
        List<Contact> searchResults = addressBook.searchContactsByName("John");
        System.out.println("Search Results:");
        for (Contact contact : searchResults) {
            System.out.println(contact);
        }

        // View contacts by city or state
        Map<String, List<Contact>> contactsByCity = addressBook.viewContactsByCity();
        System.out.println("Contacts by City:");
        for (Map.Entry<String, List<Contact>> entry : contactsByCity.entrySet()) {
            System.out.println("City: " + entry.getKey());
            for (Contact contact : entry.getValue()) {
                System.out.println(contact);
            }
        }

        Map<String, List<Contact>> contactsByState = addressBook.viewContactsByState();
        System.out.println("Contacts by State:");
        for (Map.Entry<String, List<Contact>> entry : contactsByState.entrySet()) {
            System.out.println("State: " + entry.getKey());
            for (Contact contact : entry.getValue()) {
                System.out.println(contact);
            }
        }

        // Get count of contacts by city or state
        int contactCountByCity = addressBook.getContactCountByCity("City1");
        System.out.println("Contact count in City1: " + contactCountByCity);

        int contactCountByState = addressBook.getContactCountByState("State1");
        System.out.println("Contact count in State1: " + contactCountByState);

        // Sort contacts alphabetically by name
        addressBook.sortContactsByName();

        // Sort contacts by city, state, or zip
        addressBook.sortContactsByCity();
        addressBook.sortContactsByState();
        addressBook.sortContactsByZip();

        // Display contacts
        addressBook.displayContacts();
    }
}

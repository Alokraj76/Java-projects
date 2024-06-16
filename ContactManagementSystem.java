import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// represent a contact with a name and phone number
class Contact 
{
    String name,phoneNumber;
    public void setName(String name)
    {
        this.name = name; // name the name of the contact. "This keyword is refrence to the current instance of a class, its also differentiate between with local and instance variable"
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber; //phoneNumber The phone number of the object
    }
    public String toString() {
        return name + "," + phoneNumber;
    }

    // Creates and returns a Contact object from a string representation.
    //contactString The string representation of the contact in the format "name,phoneNumber".
    // Contact object created from the string representation, or null if format is invalid.
    public static Contact fromString(String contactString) 
    {
        String[] parts = contactString.split(",");
        if (parts.length != 2) 
        {
            return null; // its return null if format is invalid.
        }
        Contact con = new Contact();
        con.setName(parts[0]);
        con.setPhoneNumber(parts[1]);
        return con;
    }
}
public class ContactManagementSystem //main class
{
    private static final String FILE_NAME = "Contacts.txt";
    private static List<Contact> contacts = new ArrayList<>(); // dynamic size, which is shrink and expand when element add or removed.
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        int option;
        System.out.println();
        System.out.println("-------Welcome to the Contact Management System-------");
        while(true)
        {
           menu();
           option = sc.nextInt();
           sc.nextLine(); // clear the new line

           switch(option)
           {
                case 1 -> addContact(sc);
                case 2 -> deleteContact(sc);
                case 3 -> viewContact();
                case 4 -> help();
                case 5 -> {
                    saveContacts();
                    System.out.println("Thankyou for using the contact management system");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("invalid option! please try again");
           }
        }
    }
    public static void menu()
    {
        System.out.println("Contact Mangement System Menu");
        System.out.println("1. Add Contact");
        System.out.println("2. Delete Contact");
        System.out.println("3. View Contact");
        System.out.println("4. Help");
        System.out.println("5. Exit");
        System.out.println("Enter your choice");
    }   
    public static void addContact(Scanner sc)
    {
        Contact con = new Contact();
        System.out.println("Enter the name");
        String name = sc.nextLine(); //Read the user input for name
        con.setName(name); // set the name for the contact
        System.out.println("Enter the phoneNumber");
        String phoneNumber = sc.nextLine(); // Read user input for phone number
        if(!phoneNumber.matches("\\d{10}")) // "\\d" it check its digit "{10}" its means 10 digits total
        {
            System.out.println("invalid phoneNumber! it must be number and 10 digits");
        }
        else
        {
            con.setPhoneNumber(phoneNumber); // set the phone number for the contact
            contacts.add(con); // add the contact to the list
            System.out.println("Contact added successfully-----");
        }
    }
    public static void deleteContact(Scanner sc) //delete a contact from the list by the name
    {
        System.out.println("Enter the name to delete the contact");
        String name = sc.nextLine(); // read user input for the name to delete
        for(int i=0;i<contacts.size();i++)
        {
            if(contacts.get(i).name.equals(name)) // check if the contact name matches to input
            {
                contacts.remove(i); //delete the contact if name matches
                System.out.println("Contact deleted successfully");
                saveContacts(); // save the updated list on file
                return;
            }
        }
        System.out.println("Contact not found");
    }
    public static void viewContact() // display the contact list which are currently loaded in memory
    {
        loadContacts(); //load contact from the file
        if(contacts.isEmpty()) //enhance for loop to check list id empty or not
        {
            System.out.println("No contact available to view");
        }
        else
        {
            System.out.println("-----------------------------");
            for(Contact con : contacts)
            {
                System.out.println("Name:"+ con.name); //display name
                System.out.println("PhoneNumber:"+ con.phoneNumber); // display phone number
            }
            System.out.println("-----------------------------");
        }
    }
    public static void help() //help messege for CMS
    {
        System.out.println("Contact Management System Help");
        System.out.println("1. Add a new contact");
        System.out.println("2. Delete a existing contact");
        System.out.println("3. view the contact list");
        System.out.println("4. Display this help messege");
        System.out.println("5.Exit");
    }
    private static void saveContacts() // save the current list of contact in file
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME)))
        {
            for(Contact con : contacts)
            {
                writer.write(con.toString()); // write details in file
                writer.newLine(); // change line after write
            }
        } 
        catch(IOException e) 
        {
            System.err.println("Error saving contacts: " + e.getMessage()); // print error messege if file operation fails
        }
    }
    private static void loadContacts() //load contact from text file in to the program list
    {
        // contacts.clear(); // Clear current list to avoid duplication
        File file = new File(FILE_NAME);
        if(!file.exists()) 
        {
            return; // return if file does not exist
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) 
        {
            String line;
            while((line = reader.readLine()) != null) 
            {
                Contact con = Contact.fromString(line); // convert each line into a contact object
                if(con != null) 
                {
                    contacts.add(con); // add valid contact to list
                }
            }
        } 
        catch(IOException e) 
        {
            System.err.println("Error loading contacts: " + e.getMessage()); // print error if file operation fails
        }
    }
    
}

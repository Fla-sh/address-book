package cloud.ptl.persistence;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

/**
 * Class for managing JSON -> POJO, and reverse operation. Also used to sustain List of contacts
 * in memory, and make it easy to access them
 */
public class ContactManager {
    private static ArrayList<Contact> contactList;

    /**
     * Used to first time load contacts
     */
    public static void intalize(){
        contactList = new ArrayList<>();
        read();
    }

    /**
     * Add new contacts to Contact book, and save it
     * @param newContact contact which ought be added
     */
    public static void addContact(Contact newContact){
        contactList.add(newContact);
        save();
    }

    /**
     * Removes contact from list
     * @param hash hash of contact to remove
     */
    public static void deleteContact(String hash){
        int position = getContactIDFromHash(hash);
        contactList.remove(position);
        save();
    }

    /**
     * Returns size of Contact Book, as number of field in 0 starting array
     * @return amount of fields
     */
    public static Integer size(){
        return contactList.size();
    }

    /**
     * Returns contact
     * @param position position in which contact is
     * @return Contact Object
     */
    public static Contact getContact(int position){
        return contactList.get(position);
    }

    /**
     * Traverse list to find contact which hash is same as searching one
     * @param hash hash to find
     * @return position in Contact List
     */
    public static Integer getContactIDFromHash(String hash){
        for(int i = 0; i < contactList.size(); i++){
            if(contactList.get(i).getHash().equals(hash)) return i;
        }
        return  -1;
    }

    /**
     * Traverse list to find contact which name is same as searching one
     * @param name name to find
     * @return ArrayList of contacts meeting criterion
     */
    public static ArrayList<Contact> findByName(String name){
        ArrayList<Contact> res = new ArrayList<>();
        for(Contact contact : contactList){
            if(contact.getName().equals(name)) res.add(contact);
        }
        return res;
    }

    /**
     * Traverse list to find contact which surname is same as searching one
     * @param surname surname to find
     * @return ArrayList of contacts meeting criterion
     */
    public static ArrayList<Contact> findBySurname(String surname){
        ArrayList<Contact> res = new ArrayList<>();
        for(Contact contact : contactList){
            if(contact.getSurname().equals(surname)) res.add(contact);
        }
        return res;
    }

    /**
     * Converts Contact List into JSON object and save it. Realize POJO -> JSON
     */
    private static void save(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contact.list", false));
            JSONArray jsonArray = new JSONArray();
            for (Contact contact : contactList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(
                        ContactFields.SURNAME.getFieldName(),
                        contact.getSurname()
                );
                jsonObject.put(
                        ContactFields.PHONE_NUMBER.getFieldName(),
                        contact.getPhoneNumber()
                );
                jsonObject.put(
                        ContactFields.NAME.getFieldName(),
                        contact.getName()
                );
                jsonObject.put(
                        ContactFields.POST_OFFICE.getFieldName(),
                        contact.getAddress().getPostOffice()
                );
                jsonObject.put(
                        ContactFields.POSTAL_CODE.getFieldName(),
                        contact.getAddress().getPostalCode()
                );
                jsonObject.put(
                        ContactFields.FLAT_NUMBER.getFieldName(),
                        contact.getAddress().getRoomNumber()
                );
                jsonObject.put(
                        ContactFields.STREET_NAME.getFieldName(),
                        contact.getAddress().getStreetName()
                );
                jsonObject.put(
                        ContactFields.HOME_NUMBER.getFieldName(),
                        contact.getAddress().getHomeNumber()
                );
                jsonObject.put(
                        ContactFields.HASH.getFieldName(),
                        contact.getHash()
                );
                jsonArray.put(jsonObject);
            }
            bufferedWriter.write(jsonArray.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts JSON file into Contact list. Realize JSON -> POJO. Also check if file with contacts exist, if no
     * create one
     */
    private static void read(){
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("contact.list"));
            String line;
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            bufferedReader.close();
            JSONArray jsonArray = new JSONArray(sb.toString());
            for(int i = 0; i < jsonArray.length(); i++){
                Contact contact = new Contact();
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                contact.setAddress(new Address(
                        jsonObject.getString(ContactFields.STREET_NAME.getFieldName()),
                        jsonObject.getInt(ContactFields.HOME_NUMBER.getFieldName()),
                        jsonObject.getInt(ContactFields.FLAT_NUMBER.getFieldName()),
                        jsonObject.getString(ContactFields.POSTAL_CODE.getFieldName()),
                        jsonObject.getString(ContactFields.POST_OFFICE.getFieldName())
                ));
                contact.setName(
                        jsonObject.getString(ContactFields.NAME.getFieldName())
                );
                contact.setPhoneNumber(
                        jsonObject.getString(ContactFields.PHONE_NUMBER.getFieldName())
                );
                contact.setSurname(
                        jsonObject.getString(ContactFields.SURNAME.getFieldName())
                );
                contact.setHash(
                        jsonObject.getString(ContactFields.HASH.getFieldName())
                );
                contactList.add(contact);
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("contact.list file not found, creating new...");
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contact.list"));
                bufferedWriter.write("[]");
                bufferedWriter.close();
                read();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

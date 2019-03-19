package org.fasttrackit.service;

import org.fasttrackit.domain.PhoneBookContact;
import org.fasttrackit.persistence.PhoneBookContactRepository;
import org.fasttrackit.transfer.SavePhoneBookContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhoneBookContactService {

    private PhoneBookContactRepository phoneBookContactRepository = new PhoneBookContactRepository();

    public void createPhoneBookContact(SavePhoneBookContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("creating contact; " + request);
        phoneBookContactRepository.createPhoneBookContact(request);
    }

    public List<PhoneBookContact> getPhoneBookContacts() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Getting phonebook contacts.");
        return phoneBookContactRepository.getPhoneBookContacts();
    }
}

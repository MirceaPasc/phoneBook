package org.fasttrackit.persistence;

import org.fasttrackit.domain.PhoneBookContact;
import org.fasttrackit.transfer.SavePhoneBookContactRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookContactRepository {

    public void createPhoneBookContact(SavePhoneBookContactRequest request) throws SQLException, IOException, ClassNotFoundException {

        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "INSERT INTO contacts (id, lastName, firstName, phoneNumber) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getLastName());
            preparedStatement.setString(2, request.getFirstName());
            preparedStatement.setString(3, request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }

    }

    public List<PhoneBookContact> getPhoneBookContacts() throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String query = "SELECT id, lastName , firstName , phoneNumber  FROM contacts ORDER BY lastName DESC;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<PhoneBookContact> response = new ArrayList<>();

            while (resultSet.next()) {
                PhoneBookContact phoneBookContact = new PhoneBookContact();
                phoneBookContact.setId(resultSet.getInt("id"));
                phoneBookContact.setLastName(resultSet.getString("lastName"));
                phoneBookContact.setFirstName(resultSet.getString("firstName"));
                phoneBookContact.setPhoneNumber(resultSet.getLong("phoneNumber"));

                response.add(phoneBookContact);
            }

            return response;
        }

    }
}

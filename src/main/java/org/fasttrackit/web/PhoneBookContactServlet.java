package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.domain.PhoneBookContact;
import org.fasttrackit.service.PhoneBookContactService;
import org.fasttrackit.transfer.SavePhoneBookContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/phone-book")
public class PhoneBookContactServlet extends HttpServlet {

    private PhoneBookContactService phoneBookContactService = new PhoneBookContactService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SavePhoneBookContactRequest savePhoneBookContactRequest = objectMapper.readValue(req.getReader(), SavePhoneBookContactRequest.class);

        try {
            phoneBookContactService.createPhoneBookContact(savePhoneBookContactRequest);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<PhoneBookContact> phoneBookContacts = phoneBookContactService.getPhoneBookContacts();

            // serializing or marshalling
            ObjectMapper objectMapper = new ObjectMapper();
            String responseJson = objectMapper.writeValueAsString(phoneBookContacts);

            // content type or mime type
            resp.setContentType("application/Json");
            resp.getWriter().print(responseJson);
            resp.getWriter().flush();

        } catch (Exception e) {
            resp.sendError(500, "There was an error processing your request. " + e.getMessage());
        }
    }

}

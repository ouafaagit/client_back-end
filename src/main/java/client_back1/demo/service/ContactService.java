package client_back1.demo.service;

import client_back1.demo.entity.Complaint;
import client_back1.demo.entity.Contact;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@Service
public interface ContactService {
    Contact findOne(long id);
    Contact save(Contact contact);

    List<Contact> getALLcontact();

    ///delete on db
    void delete(long productId);
}

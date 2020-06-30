package client_back1.demo.repository;
import client_back1.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {

    List<Contact> findAll();
}

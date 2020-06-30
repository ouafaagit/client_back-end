package client_back1.demo.service;

import client_back1.demo.entity.Complaint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@Service
public interface ComplaintService {
    Complaint findOne(long id);


    @Transactional
    Complaint save(long id, Complaint complaint);

    List<Complaint> getALLcomplaintpr(long id);

    List<Complaint> getALLcomplaint();

    ///delete on db
    void delete(long productId);
}

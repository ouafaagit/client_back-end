package client_back1.demo.service;

import client_back1.demo.entity.Doctor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@Service
public interface DoctorService {
    // update Doctor
    Doctor updateProfil(Doctor doctorInfo);

    //add
    Doctor save(Doctor doctorInfo);

    //////////////////////////////profile////////////////////////////////////////////////
    Doctor findOne(String email);

    Doctor getProfil(long id);



    List<Doctor> findAll();


//        this.initDB();

//    private void initDB() {
//        Complaint complaint = new Complaint();
//        complaint.setEmail("Abdou@gmail.com");
//        complaint.setName("CIN1");
//        complaint.setMessage("Laouali");
//        complaint.setObjet("Mahaboubou");
//        complaint.setProduct(productService.getAllProducts().get(0));
//        this.addComplaint(complaint);
//    }
//
//    public Complaint addComplaint(Complaint complaint) {
//        return this.complaintRepository.save(complaint);
//    }

}

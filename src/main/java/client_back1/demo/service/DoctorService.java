package client_back1.demo.service;

import client_back1.demo.entity.Doctor;
import client_back1.demo.entity.ProductInOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
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



    Page<Doctor> findAll(Pageable pageable);

    void deleteitem(String itemId, Doctor user);

    List<ProductInOrder> mergeLocalwish(Collection<ProductInOrder> productInOrders, String name);


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

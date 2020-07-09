package client_back1.demo.repository;

import client_back1.demo.entity.Doctor;
import client_back1.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    ////////////////for admin ////////////////////
    Doctor findByLastname(String name);
    Doctor findById(long productId);
    // Doctors all for admin
   // Page<Doctor> findAllByIdOrderById(Pageable pageable);

    Doctor findByEmail(String mail);
    Page<Doctor> findAll(Pageable pageable);
}

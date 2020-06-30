package client_back1.demo.repository;

import client_back1.demo.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

    List<Complaint> findAll();
    List<Complaint> findAllByStatusIsTrue();
    List<Complaint> findByProductProviderIdOrderByIdDesc(long id);
    List<Complaint> findByProductProviderIdAndStatusTrueOrderByIdDesc(long id);
}

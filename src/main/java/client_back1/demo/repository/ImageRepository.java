package client_back1.demo.repository;

import client_back1.demo.entity.ImageModel;
import client_back1.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
    Optional<ImageModel> findById(long name);
    //find by id product
    //Page<ImageModel> findByProductId(Long id);
    //    Image findFirstByClientId(Long id);
  //void deleteAllByProductId(Long id);
//    Image findFirstBy(Long id);
}

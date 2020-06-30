package client_back1.demo.repository;

import client_back1.demo.entity.Provider;
import client_back1.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	Collection<User> findAllByRole(String role);


	//Optional<User> findByUsername(String username);

	//Boolean existsByUsername(String username);

	//Boolean existsByEmail(String email);
}

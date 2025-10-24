package study.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import study.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}

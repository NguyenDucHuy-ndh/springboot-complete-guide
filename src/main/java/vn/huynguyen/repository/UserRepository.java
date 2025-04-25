package vn.huynguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.huynguyen.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

package vn.hoidanit.laptopshop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.hoidanit.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    User findUserById(long id);

    List<User> findAll();

    @Transactional
    void deleteUserById(long id);
}
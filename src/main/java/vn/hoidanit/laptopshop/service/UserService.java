package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    // DI : dependency injection
    private final UserRepository userRepository;

    public User handleSaveUser(User user) {
        return userRepository.save(user);
    }

    public User handleFindUserById(long id) {
        return userRepository.findUserById(id);
    }

    public List<User> handleShowAllUser() {
        return userRepository.findAll();
    }

    public User handleUpdateUser(User user) {
        return userRepository.save(user);
    }

    public void handleDeleteUserById(long id) {
        userRepository.deleteUserById(id);
    }
}

package service;

import model.Admin;
import model.Role;
import model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    Role login(String username, String password);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long userId);
    Optional<User> findUserById(Long userId);
    Optional<User> findUserByNationalCode(String nationalCode);
    Set<User> getAllUsers();
}

package service.impls;

import exception.NotFoundException;
import model.Admin;
import model.Role;
import model.User;
import repository.impls.UserRepositoryImpl;
import service.UserService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @Override
    public Role login(String username, String password) {
        try {
            Optional<User> optionalUser = userRepository.findByUsernameAndPassword(username, password);
            if (optionalUser.isEmpty()) {
                throw new NotFoundException("username or password incorrect!");
            }
            return optionalUser.get().getRole();
        } catch (SQLException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        try {
            userRepository.add(user);
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            userRepository.update(user);
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            userRepository.delete(userId);
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        try {
            return userRepository.findById(userId);
        } catch (SQLException e) {
            System.out.println("Error finding user: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByNationalCode(String nationalCode) {
        try {
            return userRepository.findByNationalCode(nationalCode);
        } catch (SQLException e) {
            System.out.println("Error finding user: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Set<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
            return Set.of();
        }
    }
}

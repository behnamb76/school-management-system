package service.impls;

import model.Admin;
import model.User;
import repository.AdminRepository;
import repository.UserRepository;
import repository.impls.AdminRepositoryImpl;
import repository.impls.UserRepositoryImpl;
import service.AdminService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository = new AdminRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public void addAdmin(Admin admin) {
        try {
            adminRepository.add(admin);
        } catch (SQLException e) {
            System.out.println("Error adding admin: " + e.getMessage());
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        try {
            adminRepository.update(admin);
        } catch (SQLException e) {
            System.out.println("Error updating admin: " + e.getMessage());
        }
    }

    @Override
    public void deleteAdmin(Long adminId) {
        try {
            adminRepository.delete(adminId);
            Optional<Admin> admin = adminRepository.findById(adminId);
            if (admin.isPresent()) {
                User user = admin.get().getUser();
                userRepository.delete(user.getUserId());
            } else {
                System.out.println("User of Admin not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting admin: " + e.getMessage());
        }
    }

    @Override
    public Optional<Admin> findAdminById(Long adminId) {
        try {
            return adminRepository.findById(adminId);
        } catch (SQLException e) {
            System.out.println("Error finding admin: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Set<Admin> getAllAdmins() {
        try {
            return adminRepository.findAll();
        } catch (SQLException e) {
            System.out.println("Error retrieving admins: " + e.getMessage());
            return Set.of();
        }
    }
}

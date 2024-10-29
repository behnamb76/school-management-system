package service;

import model.Admin;
import model.Course;

import java.util.Optional;
import java.util.Set;

public interface AdminService {
    void addAdmin(Admin admin);
    void updateAdmin(Admin admin);
    void deleteAdmin(Long adminId);
    Optional<Admin> findAdminById(Long adminId);
    Set<Admin> getAllAdmins();
}

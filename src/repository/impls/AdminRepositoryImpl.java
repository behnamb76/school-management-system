package repository.impls;

import exception.NotFoundException;
import model.Admin;
import model.Course;
import model.Role;
import model.User;
import repository.AdminRepository;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AdminRepositoryImpl implements AdminRepository {
    private static final String GET_ALL_ADMINS_QUERY = "SELECT * FROM admins";
    private static final String ADD_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, dob, national_code) VALUES(?,?,?,?)";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ?, last_name = ?, dob = ?, national_code = ? WHERE admin_id = ?";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE admin_id = ?";
    private static final String FIND_ADMIN_BY_ID = "SELECT * FROM admins WHERE admin_id = ?";
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @Override
    public Set<Admin> findAll() throws SQLException {
        ResultSet adminsResult = Database.getSQLStatement().executeQuery(GET_ALL_ADMINS_QUERY);
        Set<Admin> admins = new HashSet<>();
        while (adminsResult.next()) {
            String nc;
            Admin admin = new Admin(
                    adminsResult.getLong("admin_id"),
                    adminsResult.getString("first_name"),
                    adminsResult.getString("last_name"),
                    adminsResult.getDate("dob"),
                    nc = adminsResult.getString("national_code"),
                    userRepository.findByNationalCode(nc).get()
            );
            admins.add(admin);
        }
        return admins;
    }

    @Override
    public void add(Admin admin) throws SQLException {
        PreparedStatement preparedStatement = Database.getPreparedStatement(ADD_ADMIN_QUERY);
        preparedStatement.setString(1, admin.getFirstName());
        preparedStatement.setString(2, admin.getLastName());
        preparedStatement.setDate(3, admin.getDob());
        preparedStatement.setString(4, admin.getNationalCode());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Admin admin) throws SQLException {
        PreparedStatement preparedStatement = Database.getPreparedStatement(UPDATE_ADMIN_QUERY);
        preparedStatement.setString(1, admin.getFirstName());
        preparedStatement.setString(2, admin.getLastName());
        preparedStatement.setDate(3, admin.getDob());
        preparedStatement.setString(4, admin.getNationalCode());
        preparedStatement.setLong(5, admin.getAdminId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Long adminId) throws SQLException, NotFoundException {
        if (this.findById(adminId).isPresent()) {
            PreparedStatement preparedStatement = Database.getPreparedStatement(DELETE_ADMIN_QUERY);
            preparedStatement.setLong(1, adminId);
            preparedStatement.executeUpdate();
        } else {
            throw new NotFoundException("Admin with id of ".concat(String.valueOf(adminId)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Admin> findById(Long adminId) throws SQLException {
        PreparedStatement ps = Database.getPreparedStatement(FIND_ADMIN_BY_ID);
        ps.setLong(1, adminId);
        ResultSet rs = ps.executeQuery();
        Optional<Admin> optionalAdmin = Optional.empty();
        while (rs.next()) {
            Admin admin = new Admin();
            admin.setAdminId(rs.getLong("admin_id"));
            admin.setFirstName(rs.getString("first_name"));
            admin.setLastName(rs.getString("last_name"));
            admin.setDob(rs.getDate("dob"));
            admin.setNationalCode(rs.getString("national_code"));
            optionalAdmin = Optional.of(admin);
        }
        return optionalAdmin;
    }
}

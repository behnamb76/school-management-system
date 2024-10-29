package repository.impls;

import exception.NotFoundException;
import model.Role;
import model.User;
import repository.UserRepository;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository {
    private static final String FIND_USER_BY_USERNAME_PASSWORD_QUERY = "select * from users where username = ? and password = ?";
    private static final String GET_ALL_USERS_QUERY = "select * from users";
    private static final String ADD_USER_QUERY = "INSERT INTO users(username, password, role) VALUES(?,?,?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, password = ?, role = ? WHERE user_id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE user_id = ?";
    private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String FIND_USER_BY_NATIONAL_CODE = "SELECT * FROM users WHERE national_code = ?";

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) throws SQLException{
        PreparedStatement preparedStatement = Database.getPreparedStatement(FIND_USER_BY_USERNAME_PASSWORD_QUERY);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet userResult = preparedStatement.executeQuery();
        Optional<User> foundUser = Optional.empty();
        if (userResult.next()) {
            foundUser = Optional.of(new User(
                    userResult.getLong("user_id"),
                    userResult.getString("username"),
                    userResult.getString("password"),
                    Role.valueOf(userResult.getString("role"))
            ));
        }
        return foundUser;
    }

    @Override
    public Set<User> findAll() throws SQLException {
        ResultSet usersResult = Database.getSQLStatement().executeQuery(GET_ALL_USERS_QUERY);
        Set<User> users = new HashSet<>();
        while (usersResult.next()) {
            User user = new User(
                    usersResult.getLong("user_id"),
                    usersResult.getString("username"),
                    usersResult.getString("password"),
                    Role.valueOf(usersResult.getString("role"))
            );
            users.add(user);
        }
        return users;
    }

    @Override
    public void add(User user) throws SQLException {
        PreparedStatement preparedStatement = Database.getPreparedStatement(ADD_USER_QUERY);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, String.valueOf(user.getRole()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = Database.getPreparedStatement(UPDATE_USER_QUERY);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, String.valueOf(user.getRole()));
        preparedStatement.setLong(4, user.getUserId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Long userId) throws SQLException, NotFoundException {
        if (this.findById(userId).isPresent()) {
            PreparedStatement preparedStatement = Database.getPreparedStatement(DELETE_USER_QUERY);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } else {
            throw new NotFoundException("User with id of ".concat(String.valueOf(userId)).concat(" not found!"));
        }
    }

    @Override
    public Optional<User> findById(Long userId) throws SQLException {
        PreparedStatement ps = Database.getPreparedStatement(FIND_USER_BY_ID);
        ps.setLong(1, userId);
        ResultSet rs = ps.executeQuery();
        Optional<User> optionalUser = Optional.empty();
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            optionalUser = Optional.of(user);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findByNationalCode(String nationalCode) throws SQLException {
        PreparedStatement ps = Database.getPreparedStatement(FIND_USER_BY_NATIONAL_CODE);
        ps.setString(1, nationalCode);
        ResultSet rs = ps.executeQuery();
        Optional<User> optionalUser = Optional.empty();
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            optionalUser = Optional.of(user);
        }
        return optionalUser;
    }
}

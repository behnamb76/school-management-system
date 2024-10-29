package repository;

import model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User>{
    Optional<User> findByUsernameAndPassword(String username, String password) throws SQLException;
    Optional<User> findByNationalCode(String nationalCode) throws SQLException;
}

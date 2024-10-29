package repository;

import model.Teacher;

import java.sql.SQLException;
import java.util.Optional;

public interface TeacherRepository extends BaseRepository<Teacher> {
    Optional<Teacher> findByNationalCode(String nationalCode) throws SQLException;
}

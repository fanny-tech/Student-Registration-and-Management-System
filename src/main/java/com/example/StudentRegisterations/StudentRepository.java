package com.example.StudentRegisterations;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Hindura Save ibe save ntoya (standard) kandi duhuze firstName na lastName bibe full_name
    public int save(Student student){
        String sql = "INSERT INTO students(full_name, age, email) VALUES (?, ?, ?)";
        // Duhuza firstName na lastName bibe full_name nk'uko byasabwe muri Part A
        String fullName = student.getFirst_name() + " " + student.getLast_name();
        return jdbcTemplate.update(sql, fullName, student.getAge(), student.getEmail());
    }

    // Display all students: Garura List<Student> aho kuba List<String> kugira ngo bibe bijyanye na RowMapper
    public List<Student> findAll(){
        String sql = "SELECT * FROM students";

        RowMapper<Student> rowMapper = (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));

            // Dusoma "full_name" yo muri database tuyivanamo firstName na lastName nyayo
            String fullName = rs.getString("full_name");
            if (fullName != null && fullName.contains(" ")) {
                String[] parts = fullName.split(" ", 2);
                student.setFirst_name(parts[0]);
                student.setLast_name(parts[1]);
            } else {
                student.setFirst_name(fullName);
                student.setLast_name("");
            }

            student.setAge(rs.getInt("age"));
            student.setEmail(rs.getString("email"));

            return student;
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}
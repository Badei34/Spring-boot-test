package example.learning.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private static StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public static void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findByEmail(student.getEmail());

        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email already exists.");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentid) {
         if (!studentRepository.existsById(studentid)){
             throw new IllegalStateException("Student does not exist.");
         }
         studentRepository.deleteById(studentid);
    }

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (email != null && name.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email already taken.");
            }
            student.setEmail(email);
        }

    }



}

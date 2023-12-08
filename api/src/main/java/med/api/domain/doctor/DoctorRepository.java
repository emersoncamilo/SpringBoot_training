package med.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    @Query("""
            select d from Doctor d
            where 
            d.active = true
            and 
            d.specialty = :specialty
            and
            m.id not in(
                select c.doctor from Appointment a
                where
                c.date = date 
            
            )
            order by rand()
            limit 1
                   
            """)
    Doctor chooseDoctorFreeOnDate(Specialty specialty, LocalDateTime date);


    @Query("""
            select d.active
            from Doctor d
            where 
            d.id = :idDoctor
            """)
    Boolean findActiveById(Long idDoctor);
}

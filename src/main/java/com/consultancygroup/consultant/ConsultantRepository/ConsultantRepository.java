package com.consultancygroup.consultant.ConsultantRepository;
import com.consultancygroup.consultant.Model.Consultant;
import com.consultancygroup.consultant.Model.ConsultantResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
    Consultant findByConsultantId(Long consultantId);
    List<Consultant> findAllByFullName(String fullName);
    List<Consultant> findAllByConsultantResume(ConsultantResume consultantResume);
    @Override
    List<Consultant> findAll();
    void deleteAllByConsultantId(Long consultantId);
    @Override
    void deleteAll();
    List<Consultant> findAllByAgeAfter(int age);
}
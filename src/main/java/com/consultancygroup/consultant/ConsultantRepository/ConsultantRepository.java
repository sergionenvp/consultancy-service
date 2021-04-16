package com.consultancygroup.consultant.ConsultantRepository;


import com.consultancygroup.consultant.Model.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {



    Consultant findByConsultantId(Long consultantId);

    Consultant deleteConsultantByConsultantId(Long consultantId);

}
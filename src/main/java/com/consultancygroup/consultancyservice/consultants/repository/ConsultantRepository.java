package com.consultancygroup.consultancyservice.consultants.repository;


import com.consultancygroup.consultancyservice.consultants.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {


}

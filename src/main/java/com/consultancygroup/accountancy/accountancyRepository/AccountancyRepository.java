package com.consultancygroup.accountancy.accountancyRepository;


import com.consultancygroup.accountancy.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountancyRepository extends JpaRepository<Payment, Long>{

}

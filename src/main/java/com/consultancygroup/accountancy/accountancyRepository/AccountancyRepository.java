package com.consultancygroup.accountancy.accountancyRepository;


import com.consultancygroup.accountancy.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountancyRepository extends JpaRepository<Payment, Long>{

}

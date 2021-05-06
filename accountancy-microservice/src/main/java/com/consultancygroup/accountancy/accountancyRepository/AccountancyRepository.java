package com.consultancygroup.accountancy.accountancyRepository;


import com.consultancygroup.accountancy.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountancyRepository extends JpaRepository<Payment, Long>{

    @Query("Select p.workerMoney from Payment p where p.id = ?1")
    public double findProfitByPaymentId(Long id);

    @Query("Select p.workerMoney from Payment p where p.workerId = ?1")
    public double findProfitByWorkerId(Long id);

    @Query("Select p from Payment p where p.workerId = ?1")
    public Payment findPaymentWorkerId(Long l);
}

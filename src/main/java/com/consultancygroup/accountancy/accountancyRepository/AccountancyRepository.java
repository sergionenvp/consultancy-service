package com.consultancygroup.accountancy.accountancyRepository;


import com.consultancygroup.accountancy.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountancyRepository extends JpaRepository<Payment, Long>{

    @Query("Select p.workerMoney from Payment p where p.id = ?1")
    public double findProfitByPaymentId(Long id);

    @Query("Select SUM(p.workerMoney) from Payment p where p.workerId = ?1")
    public double findWorkerBalanceByWorkerId(Long id);

    @Query("Select p from Payment p where p.workerId = ?1")
    public List<Payment> findPaymentsByWorkerId(Long id);

    @Query("Select SUM(p.commissionCompany) from Payment p")
    public double findCompanyBalance();

    @Query("Select p.commissionCompany from Payment p where p.id = ?1")
    public double findCompanyMoneyByPaymentId(Long id);
}

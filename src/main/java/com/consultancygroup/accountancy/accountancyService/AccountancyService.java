package com.consultancygroup.accountancy.accountancyService;


import com.consultancygroup.accountancy.model.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountancyService {

    Payment savePayment(Payment payment);
}

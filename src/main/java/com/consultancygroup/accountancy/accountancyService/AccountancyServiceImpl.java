package com.consultancygroup.accountancy.accountancyService;


import com.consultancygroup.accountancy.accountancyRepository.AccountancyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountancyServiceImpl implements AccountancyService{

    @Autowired
    private AccountancyRepository accountancyRepository;

}

package com.consultancygroup;

import com.consultancygroup.consultant.ConsultantController.ConsultantController;
import com.consultancygroup.consultant.ConsultantRepository.ConsultantRepository;
import com.consultancygroup.consultant.ConsultantService.ConsultantService;
import com.consultancygroup.consultant.Model.Consultant;
import com.consultancygroup.consultant.Model.ConsultantResume;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.h2.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


@SpringBootApplication
public class ConsultancyServiceApplication {
	@Autowired
	private ConsultantService consultantService;

	public static void main(String[] args) {
		SpringApplication.run(ConsultancyServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ConsultantRepository consultantRepository) {
		return (args) -> {
			// save a few customers

			List<Consultant> consultants = consultantService.importConsultant();





			consultantRepository.saveAll(consultants);

		};


	}
}

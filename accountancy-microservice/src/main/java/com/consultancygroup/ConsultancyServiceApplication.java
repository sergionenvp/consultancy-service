package com.consultancygroup;
import com.consultancygroup.accountancy.accountancyRepository.AccountancyRepository;
import com.consultancygroup.accountancy.model.Payment;
import com.consultancygroup.accountancy.serialization.Serialization;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
@SpringBootApplication
public class ConsultancyServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsultancyServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(AccountancyRepository accountancyRepository) {
		return (args) -> {
			Serialization serialization = new Serialization();
			List<Payment> payments = serialization.importPayment();
			accountancyRepository.saveAll(payments);
		};
	}
}

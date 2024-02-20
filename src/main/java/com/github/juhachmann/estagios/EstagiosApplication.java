package com.github.juhachmann.estagios;

import com.github.juhachmann.estagios.infrastructure.db.UserDBEntity;
import com.github.juhachmann.estagios.infrastructure.db.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstagiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstagiosApplication.class, args);
	}

}

package com.example.gratidude_journal;

import org.springframework.boot.SpringApplication;

public class TestGratidudeJournalApplication {

	public static void main(String[] args) {
		SpringApplication.from(GratidudeJournalApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

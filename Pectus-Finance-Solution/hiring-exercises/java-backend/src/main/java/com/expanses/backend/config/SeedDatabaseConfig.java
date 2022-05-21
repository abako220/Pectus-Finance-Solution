package com.expanses.backend.config;

import com.expanses.backend.service.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SeedDatabaseConfig implements CommandLineRunner {

    private final CsvService csvService;

    @Override
    public void run(String... args) throws Exception {
        csvService.loadCsvFile();
    }
}

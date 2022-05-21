package com.expanses.backend.service;

import com.expanses.backend.model.Expanse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvService {

    private static final String SAMPLE_CSV_FILE_PATH = "./expanses.csv";

    private final ExpanseService expanseService;

    private  final UtilService utilService;

    public void loadCsvFile() throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                       // .withHeader("departments", "project_name", "amount", "date", "member_name")
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by the names assigned to each column
                String departments = csvRecord.get("departments");
                String projectName = csvRecord.get("project_name");
                String amount = csvRecord.get("amount");
                String date = csvRecord.get("date");
                String memberName = csvRecord.get("member_name");

                List<Expanse> expanses = new ArrayList<>();

                expanses.add(Expanse.builder()
                        .id(utilService.generateId())
                        .amount(utilService.convertStringToDouble(amount))
                        .date(utilService.convertStringToLocalDate(date))
                        .departments(departments)
                        .projectName(projectName)
                        .memberName(memberName).build());

                expanseService.saveExpanses(expanses);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


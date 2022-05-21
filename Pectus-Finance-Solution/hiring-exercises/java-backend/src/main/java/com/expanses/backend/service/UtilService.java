package com.expanses.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Slf4j
@Service
public class UtilService {

    public Double convertStringToDouble(String input) {
        input = input.replace("€", "")
                .replace(",", "");
      return Double.parseDouble(input);
    }

    public LocalDate convertStringToLocalDate(String input) throws Exception {
        LocalDate localDate;
        try{
            localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("M/d/yyyy"));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return localDate;
    }

    public Long generateId() {
       return new Random().nextLong();
    }
}

package com.expanses.backend.service;

import com.expanses.backend.dto.ExpanseDto;
import com.expanses.backend.filter.ExpanseFilter;
import com.expanses.backend.model.Expanse;
import com.expanses.backend.model.IExpanse;
import com.expanses.backend.repository.ExpanseRepository;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpanseServiceTest {
    
    ExpanseRepository repository;

    List<Expanse> expanses;

    ExpanseService service;

    @BeforeEach
    void setUp() {
        repository = mock(ExpanseRepository.class);
        expanses = List.of(Expanse.builder()
                .id(1L)
                .memberName("Sam")
                .projectName("Asterisk")
                .departments("IT")
                .date(LocalDate.now())
                .amount(239.00).build(), Expanse.builder()
                .id(2L)
                .memberName("Briana")
                .projectName("Asterisk")
                .departments("IT")
                .date(LocalDate.now())
                .amount(2000.00).build());
        service = new ExpanseService(repository);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("List should filter by amount")
    void listEmptyExpanseData() {
        when(repository.findAll()).thenReturn(expanses);
        List<ExpanseDto> result = service.listExpanseData(ExpanseFilter.builder().amount(3000.00)
                .build());
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("List should not be empty")
    void listExpanseData() {
        when(repository.findAll()).thenReturn(expanses);
        List<ExpanseDto> result = service.listExpanseData(ExpanseFilter.builder().amount(200.00)
                .build());
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should filter by member name")
    void memberNameFilter() {
        when(repository.findAll()).thenReturn(expanses);
        List<ExpanseDto> result = service.listExpanseData(ExpanseFilter.builder().memberName("Briana")
                .build());
        assertNotNull(result);
        assertEquals(1, result.size());
        
    }

    @Test
    @DisplayName("Should have one non null field after fields filter")
    void fieldsFilter() {
        when(repository.findAll()).thenReturn(expanses);
        List<ExpanseDto> result = service.listExpanseData(ExpanseFilter.builder()
                .fields(Collections.singletonList("departments"))
                .build());

        assertNotNull(result.get(0).getDepartments());
        assertEquals(2, result.size());
        assertNull(result.get(0).getAmount());

    }



    @Test
    void saveExpanses() {
        when(repository.saveAll(expanses)).thenReturn(expanses);
        service.saveExpanses(expanses);
        verify(repository, atMost(1)).saveAll(expanses);
        verify(repository, atLeastOnce()).saveAll(expanses);

    }

    @Test
    void getTotalSumByField() {
        IExpanse expanse = new IExpanse() {
            @Override
            public String getDepartment() {
                return "departments";
            }

            @Override
            public String getProjectName() {
                return null;
            }

            @Override
            public String getMemberName() {
                return null;
            }

            @Override
            public Long getSum() {
                return 500L;
            }
        };
        when(repository.countDistinctByDepartments()).thenReturn(List.of(expanse));
        var result = service.getTotalSumByField("departments");
        assertNotNull(result);
    }


}
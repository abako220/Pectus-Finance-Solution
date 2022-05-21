package com.expanses.backend.service;

import com.expanses.backend.dto.ExpanseDto;
import com.expanses.backend.filter.ExpanseFilter;
import com.expanses.backend.model.Expanse;
import com.expanses.backend.model.IExpanse;
import com.expanses.backend.repository.ExpanseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ExpanseService {

    private final ExpanseRepository repository;

    public List<ExpanseDto> listExpanseData(ExpanseFilter filter) {

        List<String> fields = Objects.requireNonNullElse(filter.getFields(), Collections.emptyList());

        Stream<Expanse> expanses = repository.findAll().stream();

        expanses = filterByAmountGreaterThanOrEquals(filter.getAmount(), expanses);
        expanses = filterByMemberName(filter.getMemberName(), expanses);
        expanses = sortAndOrder(filter.getSort(), filter.getOrder(), expanses);
        return expanses.map(expanse -> ExpanseDto.builder()
                .date(shouldDisplayField("date", fields) ? expanse.getDate(): null)
                .amount(shouldDisplayField("amount", fields) ? expanse.getAmount(): null)
                .memberName(shouldDisplayField("member_name", fields) ? expanse.getMemberName(): null)
                .departments(shouldDisplayField("departments", fields) ? expanse.getDepartments(): null)
                .projectName(shouldDisplayField("project_name", fields) ? expanse.getProjectName(): null)
                .build())
                .collect(Collectors.toList());
    }

    private boolean shouldDisplayField(String display, List<String> fields) {
        return fields.isEmpty() || fields.stream().anyMatch((field -> field.equalsIgnoreCase(display)));
    }

    private Stream<Expanse> filterByAmountGreaterThanOrEquals(Double amount, Stream<Expanse> expanses) {
        if (Objects.isNull(amount))
            return expanses;
        return expanses.filter(expanse -> expanse.getAmount() == amount || expanse.getAmount() >= amount);
    }

    private Stream<Expanse> filterByMemberName(String memberName, Stream<Expanse> expanses) {
        if (!StringUtils.hasText(memberName))
            return expanses;
        return expanses.filter(expanse -> expanse.getMemberName().equalsIgnoreCase(memberName));
    }

    private Stream<Expanse> filterByDepartments(String departments, Stream<Expanse> expanses) {
        if (!StringUtils.hasText(departments))
            return expanses;
        return expanses.filter(expanse -> expanse.getDepartments().equalsIgnoreCase(departments));
    }

    private Stream<Expanse> sortAndOrder(String sort, String order, Stream<Expanse> expanses) {
        Comparator<Expanse> comparator;
        if (StringUtils.hasText(sort)) {
            comparator = evaluateComparator(sort);
            if (order.equalsIgnoreCase("desc"))
                return expanses.sorted(comparator.reversed());
            return expanses.sorted(comparator);
        }
        return expanses;
    }

    private Comparator<Expanse> evaluateComparator(String sort) {
        if (sort.equalsIgnoreCase("departments"))
            return Comparator.comparing(Expanse::getDepartments);
        if (sort.equalsIgnoreCase("amount"))
            return Comparator.comparingDouble(Expanse::getAmount);
        return Comparator.comparing(Expanse::getMemberName);
    }

    public void saveExpanses(List<Expanse> expanses) {
        repository.saveAll(expanses);
    }

    public List<IExpanse> getTotalSumByField(String by) {
        switch (by.toLowerCase()) {
            case "departments":
                 return repository.countDistinctByDepartments();
            case "project_name":
                return repository.countDistinctByProjectName();
            case "member_name":
            default:
                return repository.countDistinctByMemberName();
        }
    }
}

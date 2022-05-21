package com.expanses.backend.web;

import com.expanses.backend.dto.ExpanseDto;
import com.expanses.backend.filter.ExpanseFilter;
import com.expanses.backend.model.Expanse;
import com.expanses.backend.model.IExpanse;
import com.expanses.backend.service.ExpanseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("expanses_data")
public class ExpanseController {

    private final ExpanseService service = null;

    @GetMapping()
    public ResponseEntity<List<ExpanseDto>> listExpanseData(@RequestParam(required = false, name = "amount") Double amount,
                                                            @RequestParam(required = false, name = "member_name") String memberName,
                                                            @RequestParam(required = false) String sort,
                                                            @RequestParam(required = false) String order,
                                                            @RequestParam(required = false) List<String> fields){
        ExpanseFilter filter = ExpanseFilter.builder()
                .amount(amount)
                .memberName(memberName)
                .sort(sort)
                .order(order)
                .fields(fields).build();
        return ResponseEntity.ok().body(service.listExpanseData(filter));
    }

    @GetMapping("aggregates")
    public ResponseEntity<List<IExpanse>> getTotalSumByField(@RequestParam String by) {
        return ResponseEntity.ok().body(service.getTotalSumByField(by));
    }

}

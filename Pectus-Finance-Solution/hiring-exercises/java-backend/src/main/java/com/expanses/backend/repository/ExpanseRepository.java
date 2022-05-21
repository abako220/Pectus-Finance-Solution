package com.expanses.backend.repository;

import com.expanses.backend.model.Expanse;
import com.expanses.backend.model.IExpanse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpanseRepository extends JpaRepository<Expanse, Long> {

    @Query("select e.departments as department, count(e.id) as sum from Expanse e group by e.departments")
    List<IExpanse> countDistinctByDepartments();

    @Query("select e.projectName as projectName,  count(e.id) as sum from Expanse e group by e.projectName")
    List<IExpanse> countDistinctByProjectName();

    @Query("select e.memberName as memberName, count(e.id) as sum from Expanse e group by e.memberName")
    List<IExpanse> countDistinctByMemberName();

}

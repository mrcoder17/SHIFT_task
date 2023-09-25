package ru.nsu.boxberger.shift_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.boxberger.shift_task.entity.IntervalEntity;

@Repository
public interface IntervalRepository extends JpaRepository<IntervalEntity, Long> {

    @Query(
            value = "SELECT * FROM INTERVALS i WHERE i.kind = :kind ORDER BY LENGTH(i.startValue), i.startValue LIMIT 1",
            nativeQuery = true
    )
    IntervalEntity findMinIntervalByKind(@Param("kind") String kind);

}

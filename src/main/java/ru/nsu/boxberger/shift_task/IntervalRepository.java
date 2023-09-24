package ru.nsu.boxberger.shift_task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IntervalRepository extends JpaRepository<IntervalEntity, Long> {
    List<IntervalEntity> findByKind(String kind);

    @Query(value = "SELECT * FROM Interval i WHERE i.kind = :kind ORDER BY LENGTH(i.startValue), i.startValue LIMIT 1", nativeQuery = true)
    IntervalEntity findMinIntervalByKind(@Param("kind") String kind);

}

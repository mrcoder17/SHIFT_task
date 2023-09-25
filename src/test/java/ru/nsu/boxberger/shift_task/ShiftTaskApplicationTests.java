package ru.nsu.boxberger.shift_task;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nsu.boxberger.shift_task.entity.IntervalEntity;
import ru.nsu.boxberger.shift_task.repository.IntervalRepository;
import ru.nsu.boxberger.shift_task.service.IntervalServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShiftTaskApplicationTests {

    @InjectMocks
    private IntervalServiceImpl intervalService;

    @Mock
    private IntervalRepository intervalRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMergeAndSaveIntervals() {
        String kind = "letters";
        List<List<String>> inputIntervals = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "d"),
                Arrays.asList("f", "g")
        );

        List<List<String>> mergedIntervals = Arrays.asList(
                Arrays.asList("a", "d"),
                Arrays.asList("f", "g")
        );

        List<IntervalEntity> expectedEntities = Arrays.asList(
                createIntervalEntity(kind, "a", "d"),
                createIntervalEntity(kind, "f", "g")
        );

        when(intervalRepository.saveAll(any())).thenReturn(expectedEntities);
        intervalService.mergeAndSaveIntervals(kind, inputIntervals);
        verify(intervalRepository, times(1)).saveAll(expectedEntities);
    }

    @Test
    void testFindMinInterval() {
        String kind = "digits";
        IntervalEntity minInterval = createIntervalEntity(kind, "1", "4");
        when(intervalRepository.findMinIntervalByKind(kind)).thenReturn(minInterval);

        String result = intervalService.findMinInterval(kind);
        assertEquals("[1,4]", result);
    }

    private IntervalEntity createIntervalEntity(String kind, String startValue, String endValue) {
        IntervalEntity entity = new IntervalEntity();
        entity.setKind(kind);
        entity.setStartValue(startValue);
        entity.setEndValue(endValue);
        return entity;
    }
}

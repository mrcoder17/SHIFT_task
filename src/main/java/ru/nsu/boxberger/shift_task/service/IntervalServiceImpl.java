package ru.nsu.boxberger.shift_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.boxberger.shift_task.repository.IntervalRepository;
import ru.nsu.boxberger.shift_task.entity.IntervalEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntervalServiceImpl implements IntervalService {

    @Autowired
    private IntervalRepository intervalRepository;

    @Override
    public void mergeAndSaveIntervals(String kind, List<List<String>> intervals) {
        sortIntervals(intervals);
        List<List<String>> mergedIntervals = mergeIntervals(intervals);

        List<IntervalEntity> intervalEntities = new ArrayList<>();
        mergedIntervals.forEach(intervalData -> createAndAddInterval(kind, intervalEntities, intervalData));

        intervalRepository.saveAll(intervalEntities);
    }

    private List<List<String>> mergeIntervals(List<List<String>> intervals) {
        List<List<String>> mergedIntervals = new ArrayList<>();
        List<String> currentInterval = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            List<String> nextInterval = intervals.get(i);
            String currentEnd = currentInterval.get(1);
            String nextStart = nextInterval.get(0);

            if (currentEnd.compareTo(nextStart) >= 0) {
                String currentStart = currentInterval.get(0);
                String nextEnd = nextInterval.get(1);
                currentInterval.set(0, currentStart.compareTo(nextStart) < 0 ? currentStart : nextStart);
                currentInterval.set(1, currentEnd.compareTo(nextEnd) > 0 ? currentEnd : nextEnd);
            } else {
                mergedIntervals.add(currentInterval);
                currentInterval = nextInterval;
            }
        }
        mergedIntervals.add(currentInterval);
        return mergedIntervals;
    }

    private void sortIntervals(List<List<String>> intervals) {
        intervals.sort((a, b) -> {
            String startA = a.get(0);
            String startB = b.get(0);
            if (startA.equals(startB)) {
                String endA = a.get(1);
                String endB = b.get(1);
                return endA.compareTo(endB);
            }
            return startA.compareTo(startB);
        });
    }

    private static void createAndAddInterval(String kind, List<IntervalEntity> intervalEntities, List<String> intervalData) {
        IntervalEntity interval = new IntervalEntity();
        interval.setKind(kind);
        interval.setStartValue(intervalData.get(0));
        interval.setEndValue(intervalData.get(1));
        intervalEntities.add(interval);
    }

    @Override
    public String findMinInterval(String kind) {
        return "[" + intervalRepository.findMinIntervalByKind(kind) +
                "," + intervalRepository.findMinIntervalByKind(kind) + "]";
    }
}
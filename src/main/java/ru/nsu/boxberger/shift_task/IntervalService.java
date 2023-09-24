package ru.nsu.boxberger.shift_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntervalService {

    @Autowired
    private IntervalRepository intervalRepository;

    public void mergeAndSaveIntervals(String kind, List<IntervalEntity> intervals) {

        List<IntervalEntity> existingIntervals = intervalRepository.findByKind(kind);
        List<IntervalEntity> mergedIntervals = new ArrayList<>();

        for (IntervalEntity interval : intervals) {
            boolean merged = false;

            for (IntervalEntity existingInterval : existingIntervals) {
                if (interval.getStartValue().compareTo(existingInterval.getEndValue()) == 0 || interval.getEndValue().compareTo(existingInterval.getStartValue()) == 0) {
                    existingInterval.setStartValue(interval.getStartValue().compareTo(existingInterval.getStartValue()) < 0 ? interval.getStartValue() : existingInterval.getStartValue());
                    existingInterval.setEndValue(interval.getEndValue().compareTo(existingInterval.getEndValue()) > 0 ? interval.getEndValue() : existingInterval.getEndValue());
                    merged = true;
                    break;
                }
            }

            if (!merged) {
                mergedIntervals.add(interval);
            }
        }
        if (!intervalRepository.existsById(1L)) {
            intervalRepository.save(new IntervalEntity());
        }
        intervalRepository.saveAll(mergedIntervals);

    }

    public IntervalEntity findMinInterval(String kind) {
        return intervalRepository.findMinIntervalByKind(kind);
    }
}
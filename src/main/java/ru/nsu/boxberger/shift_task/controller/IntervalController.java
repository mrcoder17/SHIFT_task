package ru.nsu.boxberger.shift_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.boxberger.shift_task.service.IntervalServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/intervals")
public class IntervalController {

    @Autowired
    private IntervalServiceImpl intervalServiceImpl;

    @PostMapping("/merge")
    public ResponseEntity<Void> mergeIntervals(@RequestParam("kind") String kind, @RequestBody List<List<String>> intervals) {
        intervalServiceImpl.mergeAndSaveIntervals(kind, intervals);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/min")
    public ResponseEntity<String> getMinInterval(@RequestParam("kind") String kind) {
        String minInterval = intervalServiceImpl.findMinInterval(kind);
        return ResponseEntity.ok(minInterval);
    }
}
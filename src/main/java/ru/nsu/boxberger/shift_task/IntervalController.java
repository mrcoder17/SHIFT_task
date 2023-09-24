package ru.nsu.boxberger.shift_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/intervals")
public class IntervalController {

    @Autowired
    private IntervalService intervalService;

    @PostMapping("/merge")
    public ResponseEntity<Void> mergeIntervals(@RequestParam("kind") String kind, @RequestBody List<IntervalEntity> intervals) {
        intervalService.mergeAndSaveIntervals(kind, intervals);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/min")
    public IntervalEntity getMinInterval(@RequestParam("kind") String kind) {
        IntervalEntity minInterval = intervalService.findMinInterval(kind);
        return ResponseEntity.ok(minInterval).getBody();
    }
}
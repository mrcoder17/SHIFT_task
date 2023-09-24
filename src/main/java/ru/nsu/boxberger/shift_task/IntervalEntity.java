package ru.nsu.boxberger.shift_task;

import jakarta.persistence.*;

@Entity
@Table(name = "INTERVALS")
public class IntervalEntity {

    public IntervalEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "KIND")
    private String kind;
    @Column(name = "STARTVALUE")
    private String startValue;
    @Column(name = "ENDVALUE")
    private String endValue;

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getEndValue() {
        return endValue;
    }

    public void setEndValue(String endValue) {
        this.endValue = endValue;
    }
}
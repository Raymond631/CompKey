package cn.edu.csu.compkey.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompeteWord {
    private String word;
    private double degree;
    private double averageRate;
    private int rate;

    public CompeteWord(String word, double degree, double averageRate) {
        this.word = word;
        this.degree = degree;
        this.averageRate = averageRate;
        this.rate = 0;
    }
}

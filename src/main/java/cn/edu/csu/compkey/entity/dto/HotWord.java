package cn.edu.csu.compkey.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotWord {
    @JsonIgnore
    private static int idCounter = 1;

    private int id;
    private String name;
    private int value;

    public HotWord(String word, int count) {
        this.id = idCounter++;
        this.name = word;
        this.value = count;
    }
}

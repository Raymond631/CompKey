package cn.edu.csu.compkey.entity.dto;

import cn.edu.csu.compkey.entity.Score;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {
    private String seedKey;
    private List<Score> scores;
}

package cn.edu.csu.compkey.mapper;

import cn.edu.csu.compkey.entity.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompkeyMapper {
    void saveScore(Score score);

    List<Score> getScore(@Param("seed") String seed);

    double selectScoreBySeedAndComp(@Param("seedKey") String seedKey, @Param("compKey") String compKey);
}

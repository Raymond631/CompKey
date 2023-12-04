package cn.edu.csu.compkey.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cache")
public class Cache {
    @TableId
    private String seed;
    private double comp1;
    private double comp2;
    private double comp3;
    private double comp4;
    private double comp5;
    private double comp6;
    private double comp7;
    private double comp8;
    private double comp9;
    private double comp10;
    private String recom1;
    private String recom10;
    private String recom2;
    private String recom3;
    private String recom4;
    private String recom5;
    private String recom6;
    private String recom7;
    private String recom8;
    private String recom9;
    private float score1;
    private float score10;
    private float score2;
    private float score3;
    private float score4;
    private float score5;
    private float score6;
    private float score7;
    private float score8;
    private float score9;

}

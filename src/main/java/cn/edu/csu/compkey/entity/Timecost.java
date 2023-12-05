package cn.edu.csu.compkey.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("timecost")
public class Timecost {
    @TableId
    private int id;
    private String seed;
    private int userid;
    private String algorithm;
    private int process;
    private int algtime;
    private Timestamp timestamp;
}

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
@TableName("history")
public class History {
    @TableId
    private int searchid;
    private int userid;
    private String seed;
    private float score;
    private String compkeyword;
    private Timestamp timestamp;
}

package cn.edu.csu.compkey.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timecost {
    private String seed;
    private int usrid;
    private String algorithm;
    private int process;
    private int algtime;
    private Timestamp timestamp;
}

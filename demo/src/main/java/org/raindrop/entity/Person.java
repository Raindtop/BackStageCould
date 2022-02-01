package org.raindrop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test")
public class Person {
    private String name;
    private Integer age;
}

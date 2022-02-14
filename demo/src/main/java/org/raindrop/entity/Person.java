package org.raindrop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("person")
public class Person extends Model<Person> {
    private String name;
    private Integer age;
}

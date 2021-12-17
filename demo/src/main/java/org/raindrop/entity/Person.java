package org.raindrop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@TableName(value = "person")
public class Person {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String addLoca;

    public static List<Person> list(int i){
        List<Person> list = new ArrayList<>();
        for (int j=0; j<i; j++){
            list.add(new Person(j));
        }

        return list;
    }

    public Person(){

    }

    public Person(int j){
        this.name = "Jack" + j;
        this.age = 18 + j;
        this.addLoca = "HangZhou. WestLake";
    }
}

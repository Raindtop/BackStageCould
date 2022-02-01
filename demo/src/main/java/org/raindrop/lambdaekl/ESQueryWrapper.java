package org.raindrop.lambdaekl;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.raindrop.entity.Person;

/**
 * ES Index Type 搜索类
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class ESQueryWrapper<T> {
    /**
     * 搜索索引
     */
    private String index;
    /**
     * 搜索Type
     */
    private String type;
    /**
     * 实体类型(主要用于确定泛型以及取TableInfo缓存) ? 为什么只有加T才能呢个获取到注解的value
     */
    private Class<T> entityClass;

    /**
     * 列表排序
     */
    private long from = 0;
    private long size = 100;

    public ESQueryWrapper(Class entityClass) {
        this.entityClass = entityClass;
    }


    public String getIndex() {
        return entityClass.getAnnotation(TableName.class).value();
    }

    public static void main(String[] args) {
        ESQueryWrapper esQueryWrapper = new ESQueryWrapper(Person.class);
        System.out.println(esQueryWrapper.getIndex());
        System.out.println(Person.class.getAnnotation(TableName.class).value());
        LambdaQueryWrapper<Person> lambdaQueryWrapper = new LambdaQueryWrapper(Person.class);
        lambdaQueryWrapper.eq(Person::getName, 1);
    }

}

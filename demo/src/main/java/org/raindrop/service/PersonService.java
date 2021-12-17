package org.raindrop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.raindrop.entity.Person;

public interface PersonService extends IService<Person> {
    void insertBatch();
}

package org.raindrop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.raindrop.entity.Person;
import org.raindrop.mapper.PersonMapper;
import org.raindrop.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Override
    public void insertBatch() {
        List<Person> list = Person.list(10);
        baseMapper.insertBatch(list);
    }
}

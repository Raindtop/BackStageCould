package org.raindrop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.entity.Person;
import org.raindrop.service.PersonService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/page")
public class DemoController {
    private final PersonService personService;

    // done
    @PostMapping
    public void insert(@RequestBody Person person){
        personService.save(person);
    }

    // done
    @PutMapping
    public void update(@RequestBody Person person){
        personService.updateById(person);
    }

    // done
    @DeleteMapping("/{id}")
    public void del(@PathVariable Long id){
        personService.removeById(id);
    }

    // done
    @PostMapping("/batch")
    public void insertBatch(){
        personService.insertBatch();
    }

    // done
    @GetMapping
    public Page<Person> page(){
        return personService.page(new Page<>(1, 10));
    }

    // done
    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id){
        return personService.getById(id);
    }
}

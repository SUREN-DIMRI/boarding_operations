package com.boarding.app.Service;

import com.boarding.app.Entity.Module1;
import com.boarding.app.Repository.Module1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Module1Service {

    @Autowired
    private Module1Repository module1Repository;

    public Module1 saveForm(Module1 form) {
        return module1Repository.save(form);
    }

    public Optional<Module1> getFormById(Long id) {
        return module1Repository.findById(id);
    }

    public void deleteForm(Long id) {
        module1Repository.deleteById(id);
    }
}

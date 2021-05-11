package pl.paprota.futurestars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.paprota.futurestars.services.AddService;

@RestController
public class AddController {

    @Autowired
    private AddService addService;

    @RequestMapping(value = {"/add/{numbers}","/add"})
    ResponseEntity<?> add(@PathVariable(value = "numbers",required = false) String numbers){

        int result = addService.add(numbers);

        return ResponseEntity.ok(result);
    }

}

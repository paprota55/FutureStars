package pl.paprota.futurestars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paprota.futurestars.services.AddService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AddController {

    @Autowired
    private AddService addService;

    @PostMapping(value = {"/add"})
    ResponseEntity<?> add(@RequestBody String numbers){

        String[] tableOfNumbers = addService.add(numbers);

        if(tableOfNumbers == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nowa linia w niedozwolonym miejscu.");
        }

        int result = addService.calculate(tableOfNumbers);

        return ResponseEntity.ok(result);
    }

}

package pl.paprota.futurestars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paprota.futurestars.services.AddService;

@RestController
public class AddController {

    private final AddService addService;

    public AddController(AddService addService) {
        this.addService = addService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody String numbers){
        try {

            if (!addService.checkEOFCorrectness(numbers)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("End of line in the wrong place.");
            }

            String[] tableOfNumbers = addService.prepareNumbersAndDelimiter(numbers);

            String negativeNumbers = addService.checkIfNegativeNumbers(tableOfNumbers);
            if (negativeNumbers != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(negativeNumbers);
            }

            int result = addService.calculate(tableOfNumbers);

            return ResponseEntity.ok(addService.updateDataInDataBase(result));
        }
        catch (NumberFormatException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You entered the wrong delimiter in your query");
        }
    }

}

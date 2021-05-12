package pl.paprota.futurestars.services;

import org.springframework.stereotype.Service;

@Service
public class AddService {
    public String[] add(String numbers){

        String delimiter = ",";

            if(numbers.charAt(numbers.length()-1) == '\n'){
                return null;
            }

            numbers = numbers.replace("\n","").replace("\r","");

            String[] tableOfNumbers = numbers.split(delimiter);

        return tableOfNumbers;
    }

    public String[] checkIfNegativeNumbers(){

        return new String[0];
    }

    public int calculate(String[] tableOfNumbers){
        int sum = 0;
        for(String number : tableOfNumbers){
            sum += Integer.parseInt(number);
        }
        return sum;
    }
}

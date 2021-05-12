package pl.paprota.futurestars.services;

import org.springframework.stereotype.Service;

@Service
public class AddService {
    public String[] add(String numbers){

        String delimiter = ",";

            if(numbers.charAt(numbers.length()-1) == '\n'){
                return null;
            }

            if(!Character.isDigit(numbers.charAt(0)) && numbers.charAt(1) == '\r'){
                delimiter = Character.toString(numbers.charAt(0));
                numbers = numbers.substring(1);
            }

            numbers = numbers.replace("\n","").replace("\r","");

        return numbers.split(delimiter);
    }

    public String[] checkIfNegativeNumbers(){

        return new String[0];
    }

    public int calculate(String[] tableOfNumbers){
        int sum = 0;
        int current;
        for(String number : tableOfNumbers){
            current = Integer.parseInt(number);
            if(current <=1000)
            {
                sum += current;
            }
        }
        return sum;
    }
}

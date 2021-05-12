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

    public String checkIfNegativeNumbers(String[] tableOfNumbers){
        int current;
        boolean isNegative = false;
        StringBuilder builder = new StringBuilder("negatives not allowed: ");
        for(String number : tableOfNumbers){
             current = Integer.parseInt(number);
            if(current < 0)
            {
                builder.append(current).append(", ");
                isNegative = true;
            }
        }
        if(isNegative) {
            builder.deleteCharAt(builder.length()-2);
            return builder.toString();
        }
        else{
            return null;
        }
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

package pl.paprota.futurestars.services;

import org.springframework.stereotype.Service;

@Service
public class AddService {
    public int add(String numbers){
        if(numbers == null){
            return 0;
        }

        int sum = 0;
        String delimiter = ",";

        if(numbers.length() != 0)
        {
            String[] tableOfNumbers = numbers.split(delimiter);
            for(String current : tableOfNumbers){
                sum += Integer.parseInt(current);
            }
        }

        return sum;
    }
}

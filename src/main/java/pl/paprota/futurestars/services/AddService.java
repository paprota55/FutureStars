package pl.paprota.futurestars.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.paprota.futurestars.dto.SumEntityDTO;
import pl.paprota.futurestars.models.SumEntity;
import pl.paprota.futurestars.repositories.AddRepository;

@Service
@Transactional
public class AddService {

    private final AddRepository addRepository;

    String delimiter = ",";
    char startDelimiterBracket = '[';
    char endDelimiterBracket = ']';

    public AddService(AddRepository addRepository) {
        this.addRepository = addRepository;
    }

    public void resetDelimiter(){
        delimiter = ",";
    }

    @Transactional
    public SumEntityDTO updateDataInDataBase(int sum)
    {
        SumEntity entity = addRepository.findByNumber(sum);
        if(entity == null){
            entity = new SumEntity();
            entity.setNumber(sum);
            entity.setExistCounter(1L);
            addRepository.save(entity);
        }
        else{
            entity.setExistCounter(entity.getExistCounter()+1L);
            addRepository.save(entity);
        }

        SumEntityDTO result = new SumEntityDTO();
        result.setExistCounter(entity.getExistCounter());
        result.setNumber(entity.getNumber());

        return result;
    }

    public String[] prepareNumbersAndDelimiter(String numbers){
        numbers = this.updateDelimiterAndNumbers(numbers);
        String[] tableOfNumbers = this.splitNumbers(numbers);
        this.resetDelimiter();
        return tableOfNumbers;
    }

    //remove EOF characters and split String to table of numbers
    public String[] splitNumbers(String numbers){
            numbers = numbers.replace("\n","").replace("\r","");
            return numbers.split(delimiter);
    }

    //check if EOF at the end
    public boolean checkEOFCorrectness(String number){

        for(int i = number.length()-1;i>=0;i--){
            if(number.charAt(i) == '\n'){
                return false;
            }
            else if(Character.isDigit(number.charAt(i)))
            {
                return true;
            }
        }
        return true;
    }

    //prepare delimiter and cut delimiters from numbers string
    public String updateDelimiterAndNumbers(String numbers){

        //Only 1 delimiter without brackets
        if(!Character.isDigit(numbers.charAt(0)) && numbers.charAt(1) == '\r'){
            delimiter = Character.toString(numbers.charAt(0));
            numbers = numbers.substring(1);
        }

        //multiple delimiters with brackets
        if(numbers.charAt(0) == startDelimiterBracket){
            char current = numbers.charAt(0);
            StringBuilder newDelimiter = new StringBuilder("\\");
            int currentCharFromNumbersFlag = 0;
            int delimiterCounter = 0;

            while(current != '\r'){

                if(current == startDelimiterBracket){
                    currentCharFromNumbersFlag++;
                    current = numbers.charAt(currentCharFromNumbersFlag);
                    newDelimiter.append(current);

                    while(current!=endDelimiterBracket){
                        currentCharFromNumbersFlag++;
                        current = numbers.charAt(currentCharFromNumbersFlag);
                        delimiterCounter++;
                    }
                    newDelimiter.append("{").append(delimiterCounter).append("}");
                    delimiterCounter = 0;
                    newDelimiter.append("|\\");
                }
                currentCharFromNumbersFlag++;
                current = numbers.charAt(currentCharFromNumbersFlag);
            }
            numbers = numbers.substring(currentCharFromNumbersFlag);
            newDelimiter.setLength(newDelimiter.length()-2);
            delimiter = newDelimiter.toString();
        }
        return numbers;
    }

    //check if negative numbers exist in String and return error message
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

    //sum numbers
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

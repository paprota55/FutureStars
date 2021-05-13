package pl.paprota.futurestars.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SumEntityDTO {

    @JsonProperty("result")
    private int number;

    @JsonProperty("times")
    private Long existCounter;

}

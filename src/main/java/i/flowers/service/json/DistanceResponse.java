package i.flowers.service.json;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DistanceResponse {

    private List<String> destination_addresses;
    private List<String> origin_addresses;
    private List<Row> rows;
    private String status;




}

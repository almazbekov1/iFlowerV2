package i.flowers.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {
    private Date date;
    private String message;

    public ErrorMessage(Date date, String message) {
        this.date = date;
        this.message = message;
    }
}

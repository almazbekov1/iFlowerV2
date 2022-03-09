package i.flowers.exception;

import java.util.function.Supplier;

public class FLowerServiceException extends RuntimeException  {
    public FLowerServiceException(String message){
        super(message);
    }
}

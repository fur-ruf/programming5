package error;

import java.io.IOException;

public class InvalidArgument extends IOException {
    public InvalidArgument(String message) {
        super(message);
    }
}

package error;

import java.io.IOException;

public class RepeatId extends IOException {
    public RepeatId(String message) {
        super(message);
    }
}

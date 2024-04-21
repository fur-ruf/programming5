package error;

import java.io.IOException;

public class CommandNotExist extends IOException {
    public CommandNotExist(String message) {
        super(message);
    }
}
package error;

import java.io.IOException;

public class XMLError extends IOException {
    public XMLError(String message) {
        super(message);
    }
}


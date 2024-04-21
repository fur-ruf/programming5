package error;

import java.io.IOException;

public class IllegalRecursion extends IOException {
    public IllegalRecursion(String message) {
        super(message);
    }
}

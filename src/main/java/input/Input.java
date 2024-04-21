package input;

import error.CantHaveScanner;

public abstract class Input {
    public abstract String nextLine() throws CantHaveScanner;
}

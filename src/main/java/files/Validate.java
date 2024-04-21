package files;

import classes.OrganizationType;

public class Validate {
    public boolean isValid(String[] args) {
        String name = args[0];
        String[] values = args[1].split(" ");
        Double x = Double.parseDouble(values[0]);
        Double y = Double.parseDouble(values[1]);
        Float annualTurnover = Float.parseFloat(args[2]);
        Long employeesCount = Long.parseLong(args[3]);
        OrganizationType type = OrganizationType.valueOf(args[4].toUpperCase());
        String[] words = args[5].split(" ");
        if (words.length < 2) {
        } else {
            if (words[words.length - 1].length() > 18) {
            } else {
                String[] wordStreet = new String[words.length - 1];
                for (int i = 0; i < wordStreet.length; i++) {
                    wordStreet[i] = words[i];
                }
                String street = String.join(" ", wordStreet);
                String zipCode = words[words.length - 1];
            }
        }
        return false;
    }
}

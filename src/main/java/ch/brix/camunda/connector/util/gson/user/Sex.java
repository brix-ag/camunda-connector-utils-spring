package ch.brix.camunda.connector.util.gson.user;

public enum Sex {
    UNSPECIFIED, MALE, FEMALE;

    public static Sex parse(String s) {
        if (s == null || s.isBlank())
            return UNSPECIFIED;
        switch (s.trim().toLowerCase().substring(0, 1)) {
            case "m": return MALE;
            case "f": return FEMALE;
            default: return UNSPECIFIED;
        }
    }
}

package ui.tools.enums;

public enum PaymentType {
    CREDIT_CARDS("Credit Cards"),
    BANCONTACN("Bancontact");

    private final String name;

    PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
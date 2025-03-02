package ui.tools.enums;

public enum DeliveryType {
    THUISLEVERING("Thuislevering"),
    CLICK_AND_COLLECT("Click & Collect");

    private final String name;

    DeliveryType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
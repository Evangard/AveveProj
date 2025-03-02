package ui.tools.enums;

public enum Tabs {
    TUIN("Tuin"),
    DIER("Dier"),
    LEKKERS("Lekkers"),
    BOEKETTEN("Boeketten"),
    INSPIRATIE("Inspiratie"),
    PROMOTIES("Promoties");

    private final String name;

    Tabs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
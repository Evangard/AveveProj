package ui.elements;

import org.openqa.selenium.WebElement;
import ui.AbstractGuiElement;

public class Label extends AbstractGuiElement {

    public Label(WebElement element) {
        super(element);
    }

    public Label(WebElement wrappedElement, String name) {
        super(wrappedElement, name);
    }
}

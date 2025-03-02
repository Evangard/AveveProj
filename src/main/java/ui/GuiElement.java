package ui;

import org.openqa.selenium.WrapsElement;

public interface GuiElement extends WrapsElement {

    boolean isShown();
    boolean isEnabled();

    void click();

}

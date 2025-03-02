package ui.elements;

import org.openqa.selenium.WebElement;
import ui.AbstractGuiElement;
import ui.GuiElement;

public class Button extends AbstractGuiElement {

	public Button(WebElement element) {
		super(element);
	}

	public Button(WebElement element, String name) {
		super(element, name);
	}
}

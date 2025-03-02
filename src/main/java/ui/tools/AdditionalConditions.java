package ui.tools;

import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.function.Supplier;

public class AdditionalConditions {
	public static ExpectedCondition<Boolean> isTrue(Supplier<Boolean> isTrue) {
		return driver -> isTrue.get();
	}
}

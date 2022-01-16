package controller.checkoutproperty;

import java.util.HashMap;

public interface ICheckoutProperties {

	public HashMap<Integer, Integer> filterProperties(ICheckoutPropertiesInput preferences);
	
}

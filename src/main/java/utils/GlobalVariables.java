package utils;

import java.util.HashMap;
import java.util.Map;

public class GlobalVariables {

	private static Map<String, String> map = new HashMap<>();

	public static void addGlobalVariable(String variableName, String variableValue) {
		if(map.containsKey(variableName))
			map.replace(variableName, variableValue);
		else
			map.put(variableName, variableValue);
	}
	
	public static String getGlobalVariable(String variableName) {
		return map.get(variableName);
	}
}

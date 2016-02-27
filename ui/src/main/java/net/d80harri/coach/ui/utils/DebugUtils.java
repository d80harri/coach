package net.d80harri.coach.ui.utils;

import javafx.beans.value.ObservableValue;

public class DebugUtils {
	private final Object bean;
	
	public DebugUtils(Object bean) {
		this.bean = bean;
	}
	
	public <T> ObservableValue<T> logChanges(String name, ObservableValue<T> value) {
		value.addListener((obs, o, n)->System.out.println(bean.getClass().getName() + "#" + name +": " + n));
		return value;
	}
}

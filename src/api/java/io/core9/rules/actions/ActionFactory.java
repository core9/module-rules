package io.core9.rules.actions;

import io.core9.core.plugin.Core9Plugin;

import java.util.List;

public interface ActionFactory extends Core9Plugin {
	
	void registerAction(String name, Class<? extends Action> action);
	
	List<String> getActions();
	
	Action getAction(String name) throws InstantiationException, IllegalAccessException;

}

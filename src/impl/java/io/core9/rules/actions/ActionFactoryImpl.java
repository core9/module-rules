package io.core9.rules.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class ActionFactoryImpl implements ActionFactory {
	
	private Map<String, Class<? extends Action>> actions = new HashMap<String, Class<? extends Action>>();

	@Override
	public void registerAction(String name, Class<? extends Action> action) {
		this.actions.put(name, action);
	}
	
	@Override
	public List<String> getActions() {
		return new ArrayList<String>(this.actions.keySet());
	}

	@Override
	public Action getAction(String name) throws InstantiationException, IllegalAccessException {
		return this.actions.get(name).newInstance();
	}
}

package io.core9.rules.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class ConditionFactoryImpl implements ConditionFactory {
	
	private Map<String,Class<? extends Condition>> conditions = new HashMap<String,Class<? extends Condition>>();

	@Override
	public void registerCondition(String name, Class<? extends Condition> condition) {
		this.conditions.put(name, condition);
	}

	@Override
	public List<String> getConditions() {
		return new ArrayList<String>(this.conditions.keySet());
	}

	@Override
	public Condition getCondition(String name) throws InstantiationException, IllegalAccessException {
		return conditions.get(name).newInstance();
	}

}

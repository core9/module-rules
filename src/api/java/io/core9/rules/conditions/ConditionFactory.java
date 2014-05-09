package io.core9.rules.conditions;

import io.core9.core.plugin.Core9Plugin;

import java.util.List;

public interface ConditionFactory extends Core9Plugin {

	void registerCondition(String name, Class<? extends Condition> condition);
	
	List<String> getConditions();
	
	Condition getCondition(String name) throws InstantiationException, IllegalAccessException;
}

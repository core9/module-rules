package io.core9.rules.handlers;

import java.util.HashMap;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class RuleHandlersRegistryImpl implements RuleHandlersRegistry {
	
	private Map<String, RuleHandler> handlers = new HashMap<String, RuleHandler>();
	
	@Override
	public void addHandler(String ruleType, RuleHandler handler) {
		handlers.put(ruleType, handler);
	}
	
	@Override
	public RuleHandler getHandler(String ruleType) {
		return handlers.get(ruleType);
	}

	@Override
	public void execute() {
		this.handlers.put("ALLOW", new AllowHandler());
		this.handlers.put("DENY", new DenyHandler());
	}
	
}

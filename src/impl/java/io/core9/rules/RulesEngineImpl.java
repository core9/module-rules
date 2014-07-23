package io.core9.rules;

import io.core9.plugin.server.request.Request;
import io.core9.rules.Status.Type;
import io.core9.rules.handlers.RuleHandlersRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

@PluginImplementation
public class RulesEngineImpl implements RulesEngine {
	
	private static final Map<String,RuleSet> RULES = new HashMap<String, RuleSet>();
	
	@InjectPlugin
	private RuleHandlersRegistry registry;

	@Override
	public RulesEngine addRuleSet(RuleSet ruleSet) {
		RULES.put(ruleSet.get_id(), ruleSet);
		return this;
	}

	@Override
	public Status handle(Request request) {
		@SuppressWarnings("unchecked")
		List<String> ruleSets = (List<String>) request.getVirtualHost().getContext(CONTEXT_KEY_RULESETS);
		Status status = new Status(Type.INITIALIZED);
		for(String ruleSet : ruleSets) {
			for(Rule rule : RULES.get(ruleSet).getRules()) {
				status = registry.getHandler(rule.getType()).handle(rule, request, status);
				switch(status.getType()) {
				case ALLOW:
				case DENY:
					return status;
				case PROCESS:
					continue;
				case JUMP:
				default:
					throw new UnsupportedOperationException("Not yet implemented");
				}
			}
		}
		return status.setType(Type.DENY);
	}
}

package io.core9.rules;

import io.core9.plugin.server.VirtualHost;
import io.core9.plugin.server.request.Request;
import io.core9.rules.handlers.RuleHandler;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractRulesEngine<T, K> implements RulesEngine<T, K> {
	
	private final Map<String, RuleHandler<T>> handlers = new TreeMap<String, RuleHandler<T>>();
	private final RulesRegistry registry = new RulesRegistry();
	
	protected RulesRegistry getRulesRegistry() {
		return registry;
	}
	
	@Override
	public K handleRuleset(RuleSet ruleSet, T context, K result) {
		for(Rule rule : ruleSet.getRules()) {
			result = handlers.get(rule.getType()).handle(rule, context, result);
		}
		return result;
	}
	
	@Override
	public RulesEngine<T,K> addRuleHandler(String ruleType, RuleHandler<T> handler) {
		handlers.put(ruleType, handler);
		return this;
	}
	
	@Override
	public RuleHandler<T> getHandler(String ruleType) {
		return handlers.get(ruleType);
	}
	
	@Override
	public RulesEngine<T, K> addRuleSet(VirtualHost vhost, RuleSet ruleSet) {
		registry.addRuleSet(vhost, ruleSet);
		return this;
	}
	
	@Override
	public K handle(VirtualHost vhost, String ruleSet, T context) {
		RuleSet set = registry.getRuleSet(vhost, ruleSet);
		// TODO Instantiate on initiation value
		K result = null;
		return handleRuleset(set, context, result);
	}
	
	@Override
	public Status handle(VirtualHost vhost, List<String> ruleSets, Request context) {
		
		return null;
	}


}

package io.core9.rules;

import io.core9.plugin.server.VirtualHost;
import io.core9.rules.handlers.RuleHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRulesEngine<T, K> implements RulesEngine<T, K> {
	
	private final Map<String, RuleHandler<T, K>> handlers = new HashMap<String, RuleHandler<T, K>>();
	private final RulesRegistry registry = new RulesRegistry();
	
	protected RulesRegistry getRulesRegistry() {
		return registry;
	}
	
	@Override
	public K handleRuleset(RuleSet ruleSet, T context, K result) throws RuleException {
		for(Rule rule : ruleSet.getRules()) {
			result = handlers.get(rule.getType()).handle(rule, context, result);
			switch (handleRuleResult(result)) {
			case STOP:
				return result;
			case EXCEPTION:
				throw new RuleException();
			default:
				continue;
			}
		}
		return result;
	}
	
	public abstract Result handleRuleResult(K result);
	
	@Override
	public RulesEngine<T,K> addRuleHandler(String ruleType, RuleHandler<T, K> handler) {
		handlers.put(ruleType, handler);
		return this;
	}
	
	@Override
	public RuleHandler<T, K> getHandler(String ruleType) {
		return handlers.get(ruleType);
	}
	
	@Override
	public RulesEngine<T, K> addRuleSet(VirtualHost vhost, RuleSet ruleSet) {
		registry.addRuleSet(vhost, ruleSet);
		return this;
	}
	
	@Override
	public K handle(VirtualHost vhost, String ruleSet, T context) throws RuleException {
		RuleSet set = registry.getRuleSet(vhost, ruleSet);
		// TODO Instantiate on initiation value
		K result = null;
		return handleRuleset(set, context, result);
	}
	
	@Override
	public K handle(VirtualHost vhost, List<String> ruleSets, T context) throws RuleException {
		K result = null;
		for(String ruleSet : ruleSets) {
			RuleSet set = registry.getRuleSet(vhost, ruleSet);
			// TODO Instantiate on initiation value
			result = handleRuleset(set, context, result);
		}
		return result;
	}
	
}

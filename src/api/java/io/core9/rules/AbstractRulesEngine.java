package io.core9.rules;

import io.core9.rules.Status.Type;
import io.core9.rules.handlers.RuleHandlersRegistry;

public abstract class AbstractRulesEngine<T, K> implements RulesEngine<T, K> {
	
	private RuleHandlersRegistry<T> handlersRegistry;
	
	protected void setHandlersRegistry(RuleHandlersRegistry<T> registry) {
		handlersRegistry = registry;
	}
	
	public K handleRule(RuleSet ruleSet, T context, K status) {
		for(Rule rule : ruleSet.getRules()) {
			status = handlersRegistry.getHandler(rule.getType()).handle(rule, context, status);
			switch(status.getType()) {
			case ALLOW:
			case DENY:
				return status;
			case PROCESS:
				continue;
			case PROCESSED:
				return status;
			case JUMP:
			default:
				throw new UnsupportedOperationException("Not yet implemented");
			}
		}
		return status.setType(Type.DENY);
	}

}

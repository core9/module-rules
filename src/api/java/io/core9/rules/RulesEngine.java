package io.core9.rules;

import io.core9.plugin.server.VirtualHost;
import io.core9.plugin.server.VirtualHostProcessor;
import io.core9.rules.handlers.RuleHandler;

import java.util.List;

public interface RulesEngine<T, K> extends VirtualHostProcessor {
	
	static final String KEY_RULESETS = "ruleSets";

	RulesEngine<T,K> addRuleSet(VirtualHost vhost, RuleSet ruleSet);
	
	RulesEngine<T,K> addRuleHandler(String ruleType, RuleHandler<T, K> handler);
	
	RuleHandler<T, K> getHandler(String ruleType);
	
	K handleRuleset(RuleSet ruleSet, T context, K result) throws RuleException;

	K handle(VirtualHost vhost, String ruleSet, T context, K init)	throws RuleException;
	
	K handle(VirtualHost vhost, List<String> ruleSets, T context, K init) throws RuleException;

	
	
}

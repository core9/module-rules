package io.core9.rules;

import io.core9.core.plugin.Core9Plugin;
import io.core9.plugin.server.VirtualHost;
import io.core9.plugin.server.VirtualHostProcessor;

import java.util.List;

public interface RulesEngine<T, K> extends VirtualHostProcessor, Core9Plugin {
	
	static final String KEY_RULESETS = "ruleSets";

	RulesEngine<T,K> addRuleSet(VirtualHost vhost, RuleSet ruleSet);

	K handle(VirtualHost vhost, String ruleSet, T context);
	
	K handle(VirtualHost vhost, List<String> ruleSets, T context);
}

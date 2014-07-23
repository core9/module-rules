package io.core9.rules;

import io.core9.core.plugin.Core9Plugin;
import io.core9.plugin.server.request.Request;

public interface RulesEngine extends Core9Plugin {
	
	static final String CONTEXT_KEY_RULESETS = "ruleSets";

	Status handle(Request request);

	RulesEngine addRuleSet(RuleSet ruleSet);
}

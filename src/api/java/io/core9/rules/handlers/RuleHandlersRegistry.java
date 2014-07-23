package io.core9.rules.handlers;

import io.core9.core.executor.Executor;
import io.core9.core.plugin.Core9Plugin;

public interface RuleHandlersRegistry extends Core9Plugin, Executor {

	void addHandler(String ruleType, RuleHandler handler);

	RuleHandler getHandler(String ruleType);

}

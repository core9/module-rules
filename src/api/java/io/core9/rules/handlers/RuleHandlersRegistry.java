package io.core9.rules.handlers;

@Deprecated
public interface RuleHandlersRegistry<T> {

	RuleHandlersRegistry<T> addHandler(String ruleType, RuleHandler<T> handler);

	RuleHandler<T> getHandler(String ruleType);

}

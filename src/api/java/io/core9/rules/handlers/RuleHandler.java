package io.core9.rules.handlers;

import io.core9.rules.Rule;

public interface RuleHandler<T> {
	
	<K> K handle(Rule rule, T context, K status);

}

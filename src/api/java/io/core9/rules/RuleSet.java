package io.core9.rules;

import io.core9.plugin.database.repository.AbstractCrudEntity;
import io.core9.plugin.database.repository.Collection;
import io.core9.plugin.database.repository.CrudEntity;
import io.core9.rules.conditions.Condition;

import java.util.List;

@Collection("core.rules")
public class RuleSet extends AbstractCrudEntity implements CrudEntity {
	
	private List<Condition> conditions;

	public void execute() {
		
	}
}

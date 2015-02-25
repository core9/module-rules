package io.core9.rules;

import io.core9.plugin.database.repository.AbstractCrudEntity;
import io.core9.plugin.database.repository.Collection;
import io.core9.plugin.database.repository.CrudEntity;

import java.util.HashMap;
import java.util.Map;

@Collection("configuration")
public class RuleSet extends AbstractCrudEntity implements CrudEntity {

	private Rule[] rules;
	private String configtype;

	public Rule[] getRules() {
		return rules;
	}

	public void setRules(Rule[] rules) {
		this.rules = rules;
	}
	
	public String getConfigtype() {
		return this.configtype;
	}
	
	public void setConfigtype(String configtype) {
		this.configtype = configtype;
	}

	@Override
	public Map<String, Object> retrieveDefaultQuery() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("configtype", "ruleset");
		return result;
	}

}

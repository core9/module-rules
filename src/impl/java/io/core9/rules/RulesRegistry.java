package io.core9.rules;

import io.core9.plugin.server.VirtualHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RulesRegistry {
	
	private Map<VirtualHost, Map<String, RuleSet>> rules = new HashMap<>();
	
	public RuleSet getRuleSet(VirtualHost vhost, String identifier) {
		if(!rules.containsKey(vhost)) {
			rules.put(vhost, new HashMap<>());
		}
		return rules.get(vhost).get(identifier);
	}
	
	public void addRuleSet(VirtualHost vhost, RuleSet ruleSet) {
		if(!rules.containsKey(vhost)) {
			rules.put(vhost, new HashMap<>());
		}
		rules.get(vhost).put(ruleSet.get_id(), ruleSet);
	}
	
	public List<RuleSet> getRuleSets(VirtualHost vhost, List<String> identifiers) {
		List<RuleSet> result = new ArrayList<RuleSet>();
		identifiers.forEach(identifier -> {
			result.add(rules.get(vhost).get(identifier));
		});
		return result;
	}

	public void remove(VirtualHost vhost) {
		rules.remove(vhost);
	}
}

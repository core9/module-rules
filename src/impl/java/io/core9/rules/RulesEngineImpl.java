package io.core9.rules;

import io.core9.plugin.database.repository.CrudRepository;
import io.core9.plugin.database.repository.NoCollectionNamePresentException;
import io.core9.plugin.database.repository.RepositoryFactory;
import io.core9.plugin.server.VirtualHost;
import io.core9.plugin.server.request.Request;
import io.core9.rules.Status.Type;
import io.core9.rules.handlers.RuleHandlersRegistry;

import java.util.List;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.PluginLoaded;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

@PluginImplementation
public class RulesEngineImpl implements RulesEngine {
	
	private static final RulesRegistry REGISTRY = new RulesRegistry();
	
	@InjectPlugin
	private RuleHandlersRegistry registry;
	
	private CrudRepository<RuleSet> ruleSetRepository;
	
	@PluginLoaded
	public void onRepositoryFactory(RepositoryFactory factory) throws NoCollectionNamePresentException {
		ruleSetRepository = factory.getRepository(RuleSet.class);
	}

	@Override
	public RulesEngine addRuleSet(VirtualHost vhost, RuleSet ruleSet) {
		REGISTRY.addRuleSet(vhost, ruleSet);
		return this;
	}
	
	@Override
	public void addVirtualHost(VirtualHost vhost) {
		ruleSetRepository.getAll(vhost).forEach(ruleSet -> {
			REGISTRY.addRuleSet(vhost, ruleSet);
		});
	}

	@Override
	public void removeVirtualHost(VirtualHost vhost) {
		REGISTRY.remove(vhost);
	}
	
	@Override
	public Status handle(String identifier, Request request) {
		Status status = new Status(Type.INITIALIZED);
		return handleRule(REGISTRY.getRuleSet(request.getVirtualHost(), identifier), request, status);
	}

	@Override
	public Status handle(List<String> identifiers, Request request) {
		Status status = new Status(Type.INITIALIZED);
		return handleRules(REGISTRY.getRuleSets(request.getVirtualHost(), identifiers), request, status);
	}
	
	public Status handleRule(RuleSet ruleSet, Request request, Status status) {
		for(Rule rule : ruleSet.getRules()) {
			status = registry.getHandler(rule.getType()).handle(rule, request, status);
			switch(status.getType()) {
			case ALLOW:
			case DENY:
				return status;
			case PROCESS:
				continue;
			case JUMP:
			default:
				throw new UnsupportedOperationException("Not yet implemented");
			}
		}
		return status.setType(Type.DENY);
	}
	
	public Status handleRules(List<RuleSet> ruleSets, Request request, Status status) {
		for(RuleSet ruleSet : ruleSets) {
			for(Rule rule : ruleSet.getRules()) {
				status = registry.getHandler(rule.getType()).handle(rule, request, status);
				switch(status.getType()) {
				case ALLOW:
				case DENY:
					return status;
				case PROCESS:
					continue;
				case JUMP:
				default:
					throw new UnsupportedOperationException("Not yet implemented");
				}
			}
		}
		return status.setType(Type.DENY);
	}	
}

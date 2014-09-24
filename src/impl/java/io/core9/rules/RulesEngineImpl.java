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
public class RulesEngineImpl<T, K> extends AbstractRulesEngine<T,K> implements RulesEngine<Object, Status> {
	
	private static final RulesRegistry REGISTRY = new RulesRegistry();
	
	@InjectPlugin
	private RuleHandlersRegistry registry;
	
	private CrudRepository<RuleSet> ruleSetRepository;
	
	@PluginLoaded
	public void onRepositoryFactory(RepositoryFactory factory) throws NoCollectionNamePresentException {
		ruleSetRepository = factory.getRepository(RuleSet.class);
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
	public RulesEngine addRuleSet(VirtualHost vhost, RuleSet ruleSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status handle(VirtualHost vhost, String ruleSet, Object context) {
		Status status = new Status(Type.INITIALIZED);
		return handleRule(vhost, context, status);
	}

	@Override
	public Status handle(VirtualHost vhost, List<String> ruleSets, Object context) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

//	@Override
//	public RulesEngine addRuleSet(VirtualHost vhost, RuleSet ruleSet) {
//		REGISTRY.addRuleSet(vhost, ruleSet);
//		return this;
//	}
	

//
//	
	
//	@Override
//	public Status handle(String identifier, Object context) {
//		
//	}
//
//	@Override
//	public Status handle(List<String> identifiers, Object context) {
//		Status status = new Status(Type.INITIALIZED);
//		return handleRules(REGISTRY.getRuleSets(request.getVirtualHost(), identifiers), context, status);
//	}
//	
	public Status handleRule(RuleSet ruleSet, Object context, Status status) {
		for(Rule rule : ruleSet.getRules()) {
			status = registry.getHandler(rule.getType()).handle(rule, context, status);
			switch(status.getType()) {
			case ALLOW:
			case DENY:
				return status;
			case PROCESS:
				continue;
			case PROCESSED:
				return status;
			case JUMP:
			default:
				throw new UnsupportedOperationException("Not yet implemented");
			}
		}
		return status.setType(Type.DENY);
	}
//	
//	public Status handleRules(List<RuleSet> ruleSets, Request request, Status status) {
//		for(RuleSet ruleSet : ruleSets) {
//			for(Rule rule : ruleSet.getRules()) {
//				status = registry.getHandler(rule.getType()).handle(rule, request, status);
//				switch(status.getType()) {
//				case ALLOW:
//				case DENY:
//					return status;
//				case PROCESS:
//					continue;
//				case PROCESSED:
//					return status;
//				case JUMP:
//				default:
//					throw new UnsupportedOperationException("Not yet implemented");
//				}
//			}
//		}
//		return status.setType(Type.DENY);
//	}	
}

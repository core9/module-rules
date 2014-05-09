package io.core9.rules;

import io.core9.plugin.admin.AbstractAdminPlugin;
import io.core9.plugin.database.repository.CrudRepository;
import io.core9.plugin.database.repository.NoCollectionNamePresentException;
import io.core9.plugin.database.repository.RepositoryFactory;
import io.core9.plugin.server.request.Request;
import io.core9.rules.actions.ActionFactory;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.PluginLoaded;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

@PluginImplementation
public class RulesAdminPluginImpl extends AbstractAdminPlugin implements RulesAdminPlugin {
	
	@InjectPlugin
	private ActionFactory actions;
	
	private CrudRepository<RuleSet> ruleRepository;
	
	@PluginLoaded
	public void onDatabaseLoaded(RepositoryFactory factory) throws NoCollectionNamePresentException {
		ruleRepository = factory.getRepository(RuleSet.class);
	}

	@Override
	public String getControllerName() {
		return "rules";
	}

	@Override
	protected void process(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void process(Request request, String type) {
		if(type.equals("actions")) {
			request.getResponse().sendJsonArray(actions.getActions());
		}
		
	}

	@Override
	protected void process(Request request, String type, String id) {
		// TODO Auto-generated method stub
		
	}


}

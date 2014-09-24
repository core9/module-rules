package io.core9.rules;

import io.core9.plugin.database.repository.CrudRepository;
import io.core9.plugin.database.repository.NoCollectionNamePresentException;
import io.core9.plugin.database.repository.RepositoryFactory;
import io.core9.plugin.server.VirtualHost;
import io.core9.plugin.server.request.Request;
import net.xeoh.plugins.base.annotations.Capabilities;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.PluginLoaded;

@PluginImplementation
public class RequestProcessorRulesEngine extends AbstractRulesEngine<Request, Status> {
	
	private CrudRepository<RuleSet> ruleSetRepository;
	
	@PluginLoaded
	public void onRepositoryFactory(RepositoryFactory factory) throws NoCollectionNamePresentException {
		ruleSetRepository = factory.getRepository(RuleSet.class);
	}
	
	@Capabilities
	public String[] getCapabilities() {
		return new String[]{"request", "status"};
	}

	@Override
	public void addVirtualHost(VirtualHost vhost) {
		ruleSetRepository.getAll(vhost).forEach(ruleSet -> {
			this.addRuleSet(vhost, ruleSet);
		});
	}

	@Override
	public void removeVirtualHost(VirtualHost vhost) {
		this.removeVirtualHost(vhost);
	}

	@Override
	public Result handleRuleResult(Status status) {
		switch(status.getType()) {
			case ALLOW:
			case DENY:
				return Result.STOP;
			case PROCESS:
				return Result.CONTINUE;
			case PROCESSED:
				return Result.STOP;
			case JUMP:
			default:
				throw new UnsupportedOperationException("Not yet implemented");
		}
	}
}

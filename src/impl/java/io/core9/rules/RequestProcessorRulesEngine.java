package io.core9.rules;

import java.util.List;

import net.xeoh.plugins.base.annotations.Capabilities;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import io.core9.plugin.server.VirtualHost;
import io.core9.plugin.server.request.Request;

@PluginImplementation
public class RequestProcessorRulesEngine extends AbstractRulesEngine<Request, Status> {
	
	@Capabilities
	public String[] getCapabilities() {
		return new String[]{"request", "status"};
	}

	@Override
	public void addVirtualHost(VirtualHost vhost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVirtualHost(VirtualHost vhost) {
		// TODO Auto-generated method stub
		
	}

}

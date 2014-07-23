package io.core9.rules.handlers;

import io.core9.plugin.server.request.Request;
import io.core9.rules.Client;
import io.core9.rules.Modifier;
import io.core9.rules.Rule;
import io.core9.rules.Status;
import io.core9.rules.Status.Type;

public class ClientHandler implements RuleHandler {

	@Override
	public Status handle(Rule rule, Request request, Status status) {
		Status clientStatus = new Status(Type.PROCESS);
		if(rule.getClients() == null) {
			return clientStatus;
		} else {
			String requester = request.getSourceHost();
			for(Client client : rule.getClients()) {
				if(requester.equals(client.getIp())) {
					if(client.getModifier() == Modifier.NOT) {
						return clientStatus.setType(Type.DENY);
					} else {
						return clientStatus.setType(Type.ALLOW);
					}
				}
			}
			return clientStatus.setType(Type.PROCESS);
		}
	}
}

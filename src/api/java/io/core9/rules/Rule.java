package io.core9.rules;

import java.util.Map;

public class Rule {
	
	private String type;
	private String[] startsWith;
	private String[] endsWith;
	private String[] exact;
	private Client[] clients;
	private Map<String,Object> options;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getStartsWith() {
		return startsWith;
	}

	public void setStartsWith(String[] startsWith) {
		this.startsWith = startsWith;
	}
	
	public String[] getEndsWith() {
		return endsWith;
	}

	public void setEndsWith(String[] endsWith) {
		this.endsWith = endsWith;
	}

	public String[] getExact() {
		return exact;
	}

	public void setExact(String[] exact) {
		this.exact = exact;
	}
	
	public Client[] getClients() {
		return clients;
	}

	public void setClients(Client[] clients) {
		this.clients = clients;
	}

	public Map<String,Object> getOptions() {
		return options;
	}

	public void setOptions(Map<String,Object> options) {
		this.options = options;
	}
}
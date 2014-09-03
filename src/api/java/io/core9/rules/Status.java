package io.core9.rules;

public class Status {

	private Type type;
	private String value;
	
	public Status setType(Type type) {
		this.type = type;
		return this;
	}

	public Type getType() {
		return type;
	}
	
	public Status setValue(String value) {
		this.value = value;
		return this;
	}

	public String getValue() {
		return value;
	}
	
	public Status(Type type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public Status(Type type) {
		this.type = type;
	}

	public enum Type {
		ALLOW, DENY, PROCESS, JUMP, INITIALIZED, PROCESSED;
	}
}

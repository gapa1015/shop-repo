package shop.util.rest;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 835686585155046246L;
	
	private final Object[] args;
	
	public NotFoundException(String msg, Object... args) {
		super(msg);
		this.args = args;
	}
	
	public Object[] getArgs() {
		return args;
	}
}

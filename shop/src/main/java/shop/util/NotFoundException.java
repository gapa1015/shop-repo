package shop.util;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7313807990734296514L;

	public NotFoundException(String msg) {
		super(msg);
	}
	
	public NotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}

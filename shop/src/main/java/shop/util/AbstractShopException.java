package shop.util;

public abstract class AbstractShopException extends RuntimeException {
	private static final long serialVersionUID = 2602319344578808218L;

	public AbstractShopException(String msg) {
		super(msg);
	}

	public AbstractShopException(String msg, Throwable t) {
		super(msg, t);
	}

	public abstract String getMessageKey();
}

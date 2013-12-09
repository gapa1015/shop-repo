package shop.bestellverwaltung.service;

import shop.util.AbstractShopException;

public abstract class AbstractBestellungServiceException extends AbstractShopException {
	private static final long serialVersionUID = -2198221852184634377L;

	public AbstractBestellungServiceException(String msg) {
		super(msg);
	}
	
	public AbstractBestellungServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}

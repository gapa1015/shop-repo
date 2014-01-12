package shop.kundenverwaltung.service;

import shop.util.AbstractShopException;

public abstract class AbstractKundeServiceException extends AbstractShopException {

	private static final long serialVersionUID = 8272081747233984840L;

	public AbstractKundeServiceException(String msg) {
		super(msg);
	
	}

	public AbstractKundeServiceException(String msg, Throwable t) {
		super(msg, t);
		
	}
}

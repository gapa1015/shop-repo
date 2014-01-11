package shop.bestellverwaltung.service;

import shop.util.AbstractShopException;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
public abstract class AbstractBestellungServiceException extends AbstractShopException {
	private static final long serialVersionUID = -626920099480136224L;

	public AbstractBestellungServiceException(String msg) {
		super(msg);
	}
}

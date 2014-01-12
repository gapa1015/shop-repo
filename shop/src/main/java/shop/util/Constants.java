package shop.util;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
public final class Constants {
	public static final String REST_PATH = "/rest";

	// Header-Links
	public static final String SELF_LINK = "self";
	public static final String LIST_LINK = "list";
	public static final String ADD_LINK = "add";
	public static final String UPDATE_LINK = "update";
	public static final String REMOVE_LINK = "remove";
	public static final String FIRST_LINK = "first";
	public static final String LAST_LINK = "last";

	// JPA
	public static final String LOADGRAPH = "javax.persistence.loadgraph";
	public static final Long KEINE_ID = null;
	
	private Constants() {
	}
}

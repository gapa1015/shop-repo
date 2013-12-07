package shop.util;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Producers implements Serializable {
	
	@Resource(name= "absenderMail")
	@Produces
	@AbsenderMail
	private String absenderMail;

	@Resource(name= "absenderName")
	@Produces
	@AbsenderMail
	private String absenderName;
	
}

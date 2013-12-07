package shop.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.inject.Qualifier;

	
@Qualifier
@Target({FIELD,METHOD})
@Retention(RUNTIME)
@Documented
public @interface AbsenderName{}


package touk.recru.app.facade;

import org.springframework.stereotype.Component;

@Component
public @interface Facade {
	String value() default "";
}

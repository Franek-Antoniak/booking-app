package touk.recru.app.usecase;

import org.springframework.stereotype.Component;

@Component
public @interface UseCase {
	String value() default "";
}

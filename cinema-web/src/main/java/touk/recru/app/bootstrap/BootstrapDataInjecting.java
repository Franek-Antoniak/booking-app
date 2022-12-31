package touk.recru.app.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class BootstrapDataInjecting implements ApplicationListener<ContextRefreshedEvent> {
	private final MovieBootstrap movieBootstrap;
	private final ScreeningBootstrap screeningBootstrap;
	private final RoomBootstrap roomBootstrap;
	private final SeatBootstrap seatBootstrap;


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("START BootStrapDataInjecting");
		movieBootstrap.init();
		roomBootstrap.init();
		seatBootstrap.init();
		screeningBootstrap.init();
		log.info("END BootStrapDataInjecting");
	}
}

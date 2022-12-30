package touk.recru.app.factory;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ScreeningPageableFactory {
	private static final int DEFAULT_SIZE = 20;
	private static final Sort TITLE_SORTING = Sort.by(Sort.Direction.ASC, "title");
	private static final Sort SCREENING_TIME_SORTING = Sort.by(Sort.Direction.DESC, "screeningTime");

	public Pageable create(int page) {
		return create(page, DEFAULT_SIZE);
	}

	public Pageable create(int page, int size) {
		return PageRequest.ofSize(size)
				.withPage(page)
				.withSort(TITLE_SORTING)
				.withSort(SCREENING_TIME_SORTING);
	}

	public Pageable defaultPageable() {
		return PageRequest.ofSize(20)
				.withPage(0)
				.withSort(TITLE_SORTING)
				.withSort(SCREENING_TIME_SORTING);
	}
}

package org.cargain.ukc.content.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CourseResponse(Long id, String title, String description, Long authorId, BigDecimal price,
		LocalDateTime startDate, LocalDateTime endDate, LocalDateTime creationDate, List<ModulesResponse> modules) {
}

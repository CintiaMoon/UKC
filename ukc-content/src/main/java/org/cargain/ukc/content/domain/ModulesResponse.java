package org.cargain.ukc.content.domain;

import java.util.List;

public record ModulesResponse(Long id, String title, String description, Integer position, List<LessonResponse> lessons) {
	
}

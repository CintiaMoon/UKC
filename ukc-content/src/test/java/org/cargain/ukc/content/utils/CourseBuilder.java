package org.cargain.ukc.content.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.cargain.ukc.content.domain.CourseResponse;
import org.cargain.ukc.content.model.Course;

public class CourseBuilder {

	private static final Long ID = 1l;
	private static final String TITLE = "Introduction to AI";
	private static final String DESCRIPTION = "AI Basics";
	private static final BigDecimal PRICE = new BigDecimal(99.99);

	public static Course getCourse() {
		Course course = new Course();
		course.setId(1L);
		course.setTitle(TITLE);
		course.setDescription(DESCRIPTION);
		course.setPrice(PRICE);
		return course;
	}

	public static List<Course> getCoursesList() {
		List<Course> courses = new ArrayList<>();
		courses.add(getCourse());

		return courses;
	}

	public static CourseResponse getCourseResponse() {
		CourseResponse courseResponse = new CourseResponse(ID, TITLE, DESCRIPTION, 1l, PRICE,
				LocalDateTime.of(2023, Month.SEPTEMBER, 28, 00, 00, 01),
				LocalDateTime.of(2023, Month.SEPTEMBER, 28, 23, 59, 59),
				LocalDateTime.of(2023, Month.SEPTEMBER, 28, 00, 00, 01), null);
		
		return courseResponse;
	}

	public static List<CourseResponse> getCourseResponseList() {
		List<CourseResponse> courses = new ArrayList<>();
		courses.add(getCourseResponse());

		return courses;
	}
}

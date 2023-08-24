package org.cargain.ukc.content.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.cargain.ukc.commons.events.content.CourseCreatedEvent;
import org.cargain.ukc.commons.utils.kafka.producer.KafkaProducer;
import org.cargain.ukc.commons.utils.mappers.ModelMapperService;
import org.cargain.ukc.content.domain.CourseResponse;
import org.cargain.ukc.content.domain.CreateCourseRequest;
import org.cargain.ukc.content.domain.UpdateCourseRequest;
import org.cargain.ukc.content.mapper.CourseMapper;
import org.cargain.ukc.content.model.Course;
import org.cargain.ukc.content.repository.CourseRepository;
import org.cargain.ukc.content.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

	@Mock
	private CourseRepository courseRepository;

	@Mock
	private CourseMapper mapper;

	@Mock
	private KafkaProducer producer;

	@Mock
	private ModelMapperService mapperService;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private CourseService courseService;

	private Course course;
	private CreateCourseRequest createRequest;

	CourseResponse courseResp = new CourseResponse(1l, "Introduction to AI", "AI Basics", 2l, new BigDecimal(49.99),
			LocalDateTime.of(2023, Month.SEPTEMBER, 28, 00, 00, 01),
			LocalDateTime.of(2023, Month.SEPTEMBER, 28, 23, 59, 59),
			LocalDateTime.of(2023, Month.SEPTEMBER, 28, 00, 00, 01), null);

	@BeforeEach
	public void setUp() {
		course = new Course();
		course.setId(1L);
		course.setTitle("Sample Course");
		course.setDescription("Sample Description");
		course.setPrice(new BigDecimal(99.99));
		
		createRequest = new CreateCourseRequest("Introduction to AI", "AI Basics", 2l, 
				new BigDecimal(49.99),
		LocalDateTime.of(2023, Month.SEPTEMBER, 28, 00, 00, 01),
		LocalDateTime.of(2023, Month.SEPTEMBER, 28, 23, 59, 59));
	}

	@Test
	public void testGetAllCourses() {

		List<Course> courses = new ArrayList<>();
		courses.add(course);

		when(courseRepository.findAll()).thenReturn(courses);
		when(mapper.toCourseResponse(course)).thenReturn(courseResp);

		List<CourseResponse> courseResponses = courseService.getAll();

		verify(courseRepository, times(1)).findAll();
		// Perform assertions on the result
        assertNotNull(courseResponses);
        assertEquals(1, courseResponses.size());

        // Verify appropriate interactions with mocked objects
        verify(courseRepository, times(1)).findAll();
        verify(mapper, times(1)).toCourseResponse(any());
	}

	@Test
	public void testGetCourseById() {
		Long courseId = 1L;

		when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
		when(mapper.toCourseResponse(course)).thenReturn(courseResp);

		CourseResponse courseResponse = courseService.getById(courseId);

		// Add assertions here to check the results
		verify(courseRepository, times(1)).findById(courseId);
		
		// Perform assertions on the result
        assertNotNull(courseResponse);

        // Verify appropriate interactions with mocked objects
        verify(courseRepository, times(1)).findById(1L);
        verify(mapper, times(1)).toCourseResponse(course);
	}

	@Test
	public void testSaveCourse() {
		
		when(mapper.toCourse(createRequest)).thenReturn(course);
		when(courseRepository.save(course)).thenReturn(course);
		when(mapper.toCourseResponse(course)).thenReturn(courseResp);

		CourseCreatedEvent cce = new CourseCreatedEvent();
		CourseResponse courseResponse = courseService.save(createRequest);

		// Add assertions here to check the results
		verify(courseRepository, times(1)).save(any(Course.class));
		
		assertNotNull(courseResponse);
        assertEquals(1L, courseResponse.id());
        assertEquals("Introduction to AI", courseResponse.title());

        verify(courseRepository, times(1)).save(course);
        verify(mapper, times(1)).toCourseResponse(course);
        verify(producer, times(1)).sendMessage(any(), eq(Constants.COURSE_CREATED));
    
	}

	@Test
	public void testUpdateCourse() {
		long courseId = 1L;
		UpdateCourseRequest updateRequest = new UpdateCourseRequest("Introduction to AI", "AI Basics",
				new BigDecimal(49.99));

		when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
		when(courseRepository.save(course)).thenReturn(course);
		when(mapper.toCourseResponse(course)).thenReturn(courseResp);

		CourseResponse courseResponse = courseService.update(courseId, updateRequest);

		// Add assertions here to check the results
		verify(courseRepository, times(1)).findById(courseId);
		verify(courseRepository, times(1)).save(any(Course.class));
		
		assertNotNull(courseResponse);
        assertEquals(1L, courseResponse.id());
        assertEquals("Introduction to AI", courseResponse.title());
	}

	@Test
	public void testDeleteCourse() {
		Long courseId = 1L;

		when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

		CourseResponse courseResponse = courseService.delete(courseId);

		// Add assertions here to check the results
		verify(courseRepository, times(1)).findById(courseId);
		verify(courseRepository, times(1)).delete(course);
	}
}

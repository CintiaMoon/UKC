package org.cargain.ukc.content.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.cargain.ukc.content.domain.CourseResponse;
import org.cargain.ukc.content.domain.CreateCourseRequest;
import org.cargain.ukc.content.domain.UpdateCourseRequest;
import org.cargain.ukc.content.service.CourseService;
import org.cargain.ukc.content.utils.CourseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc

//@EmbeddedKafka
//@ContextConfiguration(classes = {KafkaTestContainerConfiguration.class})
public class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private CourseService courseService;

	@InjectMocks
	private CourseController courseController;

//    @Autowired
//    private EmbeddedKafkaBroker embeddedKafkaBroker;

	private List<CourseResponse> sampleCourseList;

	private final CourseResponse courseResp = new CourseResponse(1l, "Introduction to AI", "AI Basics", 2l,
			new BigDecimal(49.99), LocalDateTime.of(2023, Month.SEPTEMBER, 28, 00, 00, 01),
			LocalDateTime.of(2023, Month.SEPTEMBER, 28, 23, 59, 59),
			LocalDateTime.of(2023, Month.SEPTEMBER, 28, 00, 00, 01), null);

	@BeforeEach
	public void setUp() {
		sampleCourseList = new ArrayList<>();
		// Initialize sampleCourseList with some sample data
		// ...

		sampleCourseList.add(CourseBuilder.getCourseResponse());

		when(courseService.getAll()).thenReturn(sampleCourseList);
		when(courseService.getById(anyLong())).thenReturn(courseResp);
		when(courseService.save(any())).thenReturn(courseResp);
		when(courseService.update(anyLong(), any())).thenReturn(courseResp);
		when(courseService.delete(anyLong())).thenReturn(courseResp);
	}

	@Test
	public void testGetAllCourses() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/courses"))
		.andExpect(MockMvcResultMatchers.status().isOk());
		// .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(sampleCourseList.size()));
	}

	@Test
	public void testGetCourseById() throws Exception {
		long courseId = 1L;
		mockMvc.perform(MockMvcRequestBuilders.get("/courses/" + courseId))
				.andExpect(MockMvcResultMatchers.status().isOk());
		// Add more assertions for the response as needed
	}

	// @Test
	public void testSaveCourse() throws Exception {

		CreateCourseRequest request = new CreateCourseRequest("Introduction to AI", "AI Basics", 2l,
				new BigDecimal(49.99), LocalDateTime.of(2023, Month.SEPTEMBER, 28, 00, 00, 01),
				LocalDateTime.of(2023, Month.SEPTEMBER, 28, 23, 59, 59));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String requestBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/courses").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isCreated());
		// Add more assertions for the response as needed
	}

	// @Test
	public void testUpdateCourse() throws Exception {
		long courseId = 1L;
		// Sample UpdateCourseRequest
		UpdateCourseRequest request = new UpdateCourseRequest("Introduction to AI", "AI Basics", new BigDecimal(49.99));
		String requestBody = "";

		mockMvc.perform(MockMvcRequestBuilders.put("/courses/" + courseId).contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(MockMvcResultMatchers.status().isOk());
		// Add more assertions for the response as needed
	}

	// @Test
	public void testDeleteCourse() throws Exception {
		long courseId = 1L;
		mockMvc.perform(MockMvcRequestBuilders.delete("/courses/" + courseId))
				.andExpect(MockMvcResultMatchers.status().isOk());
		// Add more assertions for the response as needed
	}
}

package org.cargain.ukc.profile.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.cargain.ukc.profile.domain.UserProfileDTO;
import org.cargain.ukc.profile.service.UserProfileService;
import org.cargain.ukc.profile.utils.TestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = UserProfileController.class)
public class UserProfileControllerTest {

	@Autowired
	private MockMvc mockMvc;

    @MockBean
    private UserProfileService userProfileService;
    
    private TestDataBuilder tdb = new TestDataBuilder();

    @Test
    public void testCreateUserProfile() throws Exception {
    	UserProfileDTO userProfileDTO = tdb.createSampleUserProfileDTO();        
        
        when(userProfileService.createUserProfile(any(UserProfileDTO.class))).thenReturn(userProfileDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/profiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(userProfileDTO)))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.bio").value("Experienced software developer"));
    }

    @Test
    public void testGetUserProfile() throws Exception {
    	UserProfileDTO userProfileDTO = tdb.createSampleUserProfileDTO();
        when(userProfileService.getUserProfileById("1")).thenReturn(userProfileDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles/1"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.bio").value("Experienced software developer"));
    }

    @Test
    public void testGetAllUserProfiles() throws Exception {
    	List<UserProfileDTO> userProfiles = Arrays.asList(tdb.createSampleUserProfileDTO(), tdb.createSampleUserProfileDTO());

        when(userProfileService.getAllUserProfiles()).thenReturn(userProfiles);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].bio").value("Experienced software developer"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].bio").value("Experienced software developer"));
    }

    @Test
    public void testUpdateUserProfile() throws Exception {
    	 UserProfileDTO userProfileDTO = tdb.createSampleUserProfileDTO();

        when(userProfileService.updateUserProfile("1", userProfileDTO)).thenReturn(userProfileDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/profiles/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(userProfileDTO)))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.bio").value("Experienced software developer"));
    }

    @Test
    public void testDeleteUserProfile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/profiles/1"))
            .andExpect(status().isNoContent());
    }

	// Helper method to convert object to JSON
	private String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper =

					JsonMapper.builder().addModule(new JavaTimeModule()).build();

			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

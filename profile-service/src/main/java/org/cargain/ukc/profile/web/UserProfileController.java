package org.cargain.ukc.profile.web;

import java.util.List;

import org.cargain.ukc.profile.domain.UserProfileDTO;
import org.cargain.ukc.profile.exception.UserProfileNotFoundException;
import org.cargain.ukc.profile.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profiles")
@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "It's okay, the request was successful."),
		@ApiResponse(responseCode = "400", description = "There is an error in the request parameters."),
		@ApiResponse(responseCode = "404", description = "The URL is invalid or there is no such action in the web application."),
		@ApiResponse(responseCode = "500", description = "An error occurred on the server while executing the request.") })
@RequiredArgsConstructor
public class UserProfileController {

	private final UserProfileService userProfileService;

	@Operation(summary = "Creates a user profile")
	@PostMapping
	public ResponseEntity<UserProfileDTO> createUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
		UserProfileDTO createdProfile = userProfileService.createUserProfile(userProfileDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
	}

	@Operation(summary = "Get a user profile by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the user profile", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UserProfileDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "User profile not found", content = @Content) })
	@GetMapping("/{profileId}")
	public ResponseEntity<UserProfileDTO> getUserProfile(
			@Parameter(description = "id of user profile to be searched") @PathVariable String profileId) {
		UserProfileDTO userProfile = userProfileService.getUserProfileById(profileId);
		return ResponseEntity.ok(userProfile);
	}

	@Operation(summary = "Gets all profiles")
	@GetMapping
	public ResponseEntity<List<UserProfileDTO>> getAllUserProfiles() {
		List<UserProfileDTO> userProfiles = userProfileService.getAllUserProfiles();
		return ResponseEntity.ok(userProfiles);
	}

	@Operation(summary = "Updates a user profile")
	@PutMapping("/{profileId}")
	public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable String profileId,
			@RequestBody UserProfileDTO userProfileDTO) {
		UserProfileDTO updatedProfile = userProfileService.updateUserProfile(profileId, userProfileDTO);
		return ResponseEntity.ok(updatedProfile);
	}

	@Operation(summary = "Deletes a user profile")
	@DeleteMapping("/{profileId}")
	public ResponseEntity<Void> deleteUserProfile(@PathVariable String profileId) {
		userProfileService.deleteUserProfile(profileId);
		return ResponseEntity.noContent().build();
	}

	// Exception handling using ControllerAdvice
	@ExceptionHandler(UserProfileNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundException(UserProfileNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}

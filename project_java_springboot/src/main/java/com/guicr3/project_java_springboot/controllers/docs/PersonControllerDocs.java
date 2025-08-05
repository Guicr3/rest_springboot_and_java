package com.guicr3.project_java_springboot.controllers.docs;

import com.guicr3.project_java_springboot.data.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PersonControllerDocs {
    @Operation(summary = "Find a Person", description = "Finds a specific person by ID", tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "No content", responseCode = "201", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    PersonDTO findById(@PathVariable("id") Long id);

    @Operation(summary = "Find All People", description = "Finds All People", tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))}),
            @ApiResponse(description = "No content", responseCode = "201", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<List<PersonDTO>> findAll();

    @Operation(summary = "Create a Person", description = "Add a person by passing a representation via JSON, XML or YML", tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person);

    @Operation(summary = "Update a Person", description = "Updates a person by passing a representation via JSON, XML or YML by ID", tags = {"People"}, responses = {
            @ApiResponse(description = "No content", responseCode = "201", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<PersonDTO> update(@RequestBody PersonDTO updatedPerson, @PathVariable Long id);

    @Operation(summary = "Disable a Person", description = "Disable a specific person by ID", tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "No content", responseCode = "201", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    PersonDTO disablePerson(@PathVariable("id") Long id);

    @Operation(summary = "Delete a Person", description = "Deletes a person by ID", tags = {"People"}, responses = {
            @ApiResponse(description = "No content", responseCode = "201", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<Void> delete(@PathVariable("id") Long id);
}

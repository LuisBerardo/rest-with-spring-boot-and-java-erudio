package com.luisberardo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisberardo.data.vo.v1.PersonVO;
import com.luisberardo.services.PersonServices;
import com.luisberardo.util.MediaType;

import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	//private PersonServices service = new PersonServices();
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all People", description = "Finds all People",
		tags = {"People"},
		responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = {
						@Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
						)
					}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
	public List<PersonVO> findAll() throws Exception{			
		return service.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a Person", 
		description = "Finds a Person",
		tags = {"People"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = @Content(schema = @Schema(implementation = PersonVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
	public PersonVO findById(@PathVariable(value = "id") Long id){			
		return service.findById(id);
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://luisberardo.com"})
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a new Person",
		description = "Adds a new Person by passing in a JSON, XML or YML representation of the person!",
		tags = {"People"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = PersonVO.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
	public PersonVO create(@RequestBody PersonVO person){			
		return service.create(person);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a Person", 
		description = "Updates a Person by passing in a JSON, XML or YML representation of the person!",
		tags = {"People"},
		responses = {
			@ApiResponse(description = "Updated", responseCode = "200",
				content = @Content(schema = @Schema(implementation = PersonVO.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
	public PersonVO update(@RequestBody PersonVO person){			
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Person", 
		description = "Deletes a Person",
		tags = {"People"},
		responses = {
			@ApiResponse(description = "No Content", responseCode = "204",content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){			
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	/*
	@PostMapping(value = "/v2",
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person){			
		return service.createV2(person);
	}*/	
	
	/*
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() throws Exception{			
		return service.findAll();
	}
	
	@RequestMapping(value = "/{id}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") Long id){			
		return service.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person create(@RequestBody Person person){			
		return service.create(person);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person update(@RequestBody Person person){			
		return service.update(person);
	}
	
	@RequestMapping(value = "/{id}",
			method=RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Long id){			
		service.delete(id);
	}
	*/	
}

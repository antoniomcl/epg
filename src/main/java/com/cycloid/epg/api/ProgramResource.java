package com.cycloid.epg.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cycloid.epg.domain.entity.Program;
import com.cycloid.epg.domain.exception.NotFoundException;
import com.cycloid.epg.domain.repository.ProgramRepository;
import com.cycloid.epg.domain.service.ProgramService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/programs")
public class ProgramResource {

	@Autowired
	private ProgramRepository programRepository;
	
	@Autowired
	private ProgramService programService;

	// RQ.2.0 - Provides all programs ...
	@GetMapping
	public List<Program> allPrograms() {
		return programRepository.findAll();
	}

	// RQ.2.1 - Create a program ...
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Program newProgram(@RequestBody Program newProgram) {
		return programService.save(newProgram);
	}

	// RQ.2.2 - Provides all programs by channel id ...
	@GetMapping("/channel/{channelId}")
	public List<Program> allProgramsByChannel(@PathVariable Long channelId) {
		return programService.programsByChannelID(channelId);
	}

	// RQ.2.3 - Provides the program detail by id ..
	@GetMapping("/{programId}")
	public Program oneProgram(@PathVariable Long programId) {
		return programRepository.findById(programId).orElseThrow(() -> new NotFoundException(programId));
	}

	// RQ.2.4 - Delete the program by id ...
	@DeleteMapping("/{programId}")
	public ResponseEntity<Void> offProgram(@PathVariable Long programId) {
		if (!programRepository.existsById(programId)) {
			return ResponseEntity.notFound().build();
		}

		programService.removeProgram(programId);

		return ResponseEntity.noContent().build();
	}

	// RQ.2.5 - Update the program by id ...
	@PutMapping("/{programId}")
	public ResponseEntity<Program> updateProgram(@Valid @PathVariable Long programId, @RequestBody Program program) {

		if (!programRepository.existsById(programId)) {
			return ResponseEntity.notFound().build();
		}
		
		program.setId(programId);
		program = programService.save(program);
		
		return ResponseEntity.ok(program);
	}
}

// Antonio M C Lopes
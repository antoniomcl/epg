package com.cycloid.epg.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cycloid.epg.domain.entity.Channel;
import com.cycloid.epg.domain.entity.Program;
import com.cycloid.epg.domain.exception.ExistsException;
import com.cycloid.epg.domain.exception.NotFoundException;
import com.cycloid.epg.domain.repository.ChannelRepository;
import com.cycloid.epg.domain.repository.ProgramRepository;

@Service
public class ProgramService {

	@Autowired
	private ProgramRepository programRepository;

	@Autowired
	private ChannelRepository channelRepository;

	// Save program ...
	public Program save(Program program) {

 		Program programExists = programRepository.findByTitle(program.getTitle());

		if (programExists != null && !programExists.equals(program)) {
			throw new ExistsException();
		}

		return programRepository.save(program);
	}

	// List all program by channel ...
	public List<Program> programsByChannelID(Long channelId) {
		
		Optional<Channel> channelExists = channelRepository.findById(channelId);
		
		if (!channelExists.isPresent()) {
			throw new NotFoundException(channelId);
		} else {
			return programRepository.findByChannel(channelExists.get());			
		}
	}

	// Delete Program ...
	public void removeProgram(Long programId) {
		programRepository.deleteById(programId);
	}
}

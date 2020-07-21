package com.cycloid.epg.api;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cycloid.epg.domain.entity.Channel;
import com.cycloid.epg.domain.exception.NotFoundException;
import com.cycloid.epg.domain.repository.ChannelRepository;
import com.cycloid.epg.domain.service.ChannelService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/channels")
public class ChannelResource {

	@Autowired
	private ChannelRepository channelRepository;
	
	@Autowired
	private ChannelService channelService;
	
	// RQ.1.1 - Provides all channels ...
	@GetMapping
	public List<Channel> allChannels() {
		return channelRepository.findAll();
	}
	
	// RQ.1.2 - Create a channel ...
	@PostMapping("create")
	@ResponseStatus(HttpStatus.CREATED)
	public Channel newChannel(@RequestBody Channel channel) {
		return channelService.save(channel);
	}
	
	// Provides the channel detail by id ..
	@GetMapping("/{channelId}")
	public Channel oneChannel(@PathVariable Long channelId) {
		return channelRepository.findById(channelId).orElseThrow(() -> new NotFoundException(channelId));
	}
}

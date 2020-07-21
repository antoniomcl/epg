package com.cycloid.epg.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cycloid.epg.domain.entity.Channel;
import com.cycloid.epg.domain.exception.ExistsException;
import com.cycloid.epg.domain.repository.ChannelRepository;

@Service
public class ChannelService {

	@Autowired
	private ChannelRepository channelRepository;
	
	// Save channel ...
	public Channel save(Channel channel) {
		
		Channel channelExists = channelRepository.findByName(channel.getName());
		
		if (channelExists != null && !channelExists.equals(channel)) {
			throw new ExistsException();
		}
		
		return channelRepository.save(channel);
	}
}

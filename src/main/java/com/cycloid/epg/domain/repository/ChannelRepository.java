package com.cycloid.epg.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cycloid.epg.domain.entity.Channel;

@Repository
public interface ChannelRepository extends JpaRepository <Channel, Long> {

	Channel findByName(String name);
	
}

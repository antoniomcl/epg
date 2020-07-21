package com.cycloid.epg.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cycloid.epg.domain.entity.Channel;
import com.cycloid.epg.domain.entity.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long>{

	Program findByTitle(String title);
	List<Program> findByChannel(Channel channel);
}

package com.github.juhachmann.estagios.data.mock; 

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.github.juhachmann.estagios.data.dto.PerfilDTO;


public class MockDatabasePerfil {
	
	public static List<PerfilDTO> perfilCollection = new ArrayList<PerfilDTO>();
	
	private static AtomicLong currentId = new AtomicLong();
	
	public static Long incrementAndGet() {
		return currentId.incrementAndGet();
	}
	
	public static Long getCurrentId() {
		return currentId.longValue();
	}
	
}

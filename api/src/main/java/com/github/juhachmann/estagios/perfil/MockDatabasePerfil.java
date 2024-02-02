package com.github.juhachmann.estagios.perfil; 

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


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

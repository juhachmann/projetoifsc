package com.github.juhachmann.estagios.trash; 

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.github.juhachmann.estagios.apirest.resources.authUserPerfil.AuthUserPerfilDTO;


public class MockDatabasePerfil {
	
	public static List<AuthUserPerfilDTO> perfilCollection = new ArrayList<AuthUserPerfilDTO>();
	
	private static AtomicLong currentId = new AtomicLong();
	
	public static Long incrementAndGet() {
		return currentId.incrementAndGet();
	}
	
	public static Long getCurrentId() {
		return currentId.longValue();
	}
	
}

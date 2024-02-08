package com.github.juhachmann.estagios.trash;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.github.juhachmann.estagios.api.resources.vagas.VagaDTO;

public class MockVagasDatabase {
	
	public static List<VagaDTO> vagasCollection = new ArrayList<>();
	
	private static AtomicLong currentId = new AtomicLong();
	
	public static Long incrementAndGet() {
		return currentId.incrementAndGet();
	}
	
	public static Long getCurrentId() {
		return currentId.longValue();
	}

}

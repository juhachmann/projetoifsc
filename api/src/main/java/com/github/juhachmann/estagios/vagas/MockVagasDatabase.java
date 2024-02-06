package com.github.juhachmann.estagios.vagas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

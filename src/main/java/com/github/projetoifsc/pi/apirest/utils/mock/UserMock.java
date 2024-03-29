package com.github.projetoifsc.pi.apirest.utils.mock;

import com.github.projetoifsc.pi.apirest.dto.UserPrivateProfileDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserMock {

	public static UserPrivateProfileDTO getOne() {
		return getList().get(0);
	}

	public static List<UserPrivateProfileDTO> getList() {
		var list = new ArrayList<>(List.of(
			new UserPrivateProfileDTO("1", "NoBanks", false, "empresa nobanks", ContatoMock.getOne(), "nobanks", "94169927000101", LocalizacaoMock.getOne(), ContatoMock.getOne(), LocalDateTime.now(), LocalDateTime.now()),
			new UserPrivateProfileDTO("2", "Giggle", false, "empresa giggle", ContatoMock.getOne(), "giggle", "57576707000168", LocalizacaoMock.getOne(), null, LocalDateTime.now(), LocalDateTime.now())
		));
		list.addAll(getSchools());
		return list;
	}

	public static UserPrivateProfileDTO getInvalid() {
		return new UserPrivateProfileDTO("", "", false, "", null, "", "", null, null, null, null);
	}

	public static List<UserPrivateProfileDTO> getSchools() {
		return List.of(
			new UserPrivateProfileDTO("3", "IFXC", true, "instituto federal", ContatoMock.getOne(),"ifxc", "16417198000143", LocalizacaoMock.getOne(), null, LocalDateTime.now(), LocalDateTime.now()),
			new UserPrivateProfileDTO("4", "UFXC", true, "universidade federal", ContatoMock.getOne(),"ufxc", "71962402000115", LocalizacaoMock.getOne(), ContatoMock.getOne(), LocalDateTime.now(), LocalDateTime.now())
		);
	}


}

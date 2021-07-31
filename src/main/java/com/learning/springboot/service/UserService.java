package com.learning.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.springboot.beans.ae.UserAE;
import com.learning.springboot.beans.de.UserDE;
import com.learning.springboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void submitUser(UserAE userAE) {

		UserDE userDE = convert(userAE);
		userRepository.save(userDE);

	}

	public List<UserAE> getAllUsers() {
		List<UserDE> deList = (List<UserDE>) userRepository.findAllByDeletedFalse();
		List<UserAE> aeList = deList.stream().map(this::convert).collect(Collectors.toList());
		return aeList;
	}

	public UserAE getUser(int id) {
		return convert(userRepository.findById(id).orElseGet(UserDE::new));
	}

	public void updateUser(UserAE userAE) {
		UserDE userDE = userRepository.findById(userAE.getId()).orElseGet(UserDE::new);
		userDE.setUserId(userAE.getUserId());
		userRepository.save(userDE);
	}

	public void delete(int id) {
		UserDE userDE = userRepository.findById(id).orElseGet(UserDE::new);
		userDE.setDeleted(true);
		userRepository.save(userDE);
	}

	private UserDE convert(UserAE userAE) {
		UserDE userDE = new UserDE();
		userDE.setId(userAE.getId());
		userDE.setUserId(userAE.getUserId());
		userDE.setPassword(passwordEncoder.encode(userAE.getPassword()));
		return userDE;
	}

	private UserAE convert(UserDE userDE) {
		UserAE userAE = new UserAE();
		userAE.setId(userDE.getId());
		userAE.setUserId(userDE.getUserId());
		return userAE;
	}

}

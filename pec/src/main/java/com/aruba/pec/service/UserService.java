package com.aruba.pec.service;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruba.pec.dao.entities.Message;
import com.aruba.pec.dao.entities.Pec;
import com.aruba.pec.dao.entities.User;
import com.aruba.pec.repositories.UsersRepository;
import com.aruba.pec.service.exceptions.ResourceNotFoundException;

@Service
public class UserService implements IUserService {

	@Autowired
	private UsersRepository repository;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User findUserById(String id) throws ResourceNotFoundException {
		User user = repository.findUserByIdAruba(id);
		if (user == null) {
			throw new ResourceNotFoundException("User with id " + id + " not found");
		}
		return user;

	}

	@Override
	public List<Message> searchMessagesBySender(String idAruba, String pecName, String sender, String text,
			String subject, String priority, boolean hasAttach) throws ResourceNotFoundException {
		return getPecs(idAruba).stream().filter(pec -> pec.getName().equalsIgnoreCase(pecName))
				.flatMap(pec -> pec.getMessages().stream())
				.filter(filterSender(sender)
						.and(filterText(text).and(filterSubject(subject).and(filterPriority(priority))))
						.and(filterAttachment(hasAttach)))
				.collect(Collectors.toList());
	}

	private Predicate<Message> filterSender(String sender) {
		return message -> message.getSender().contains(sender);
	}

	private Predicate<Message> filterText(String text) {
		return message -> message.getText().contains(text);
	}

	private Predicate<Message> filterSubject(String subject) {
		return message -> message.getSubject().contains(subject);
	}

	private Predicate<Message> filterPriority(String priority) {
		return message -> message.getPriority().contains(priority);
	}

	private Predicate<Message> filterAttachment(boolean hasAttach) {
			if (hasAttach) {				
				return message -> message.getAttachment() != null;
			}
			return message -> message.getAttachment() == null;
	}

	public Set<Pec> getPecs(String id) throws ResourceNotFoundException {
		return findUserById(id).getPecs();
	}

	@Override
	public String getPrivateKey(String id) throws ResourceNotFoundException {
		logger.debug("UserService:get private key for user");
		return this.findUserById(id).getPrivateKey();
	}

	@Override
	public User findUserByEmail(String email) throws ResourceNotFoundException {
		User user = this.repository.findUserByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("User with email " + email + " not found");
		}
		return this.repository.findUserByEmail(email);

	}
}

package com.own.space.service;

import com.own.space.domain.User;
import com.own.space.util.events.user.UserCreatedEvent;
import com.own.space.util.events.EventPublisher;
import com.own.space.util.mail.Email;
import com.own.space.util.mail.MailSender;
import com.own.space.repository.UserRepository;
import com.own.space.util.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.own.space.util.Validation.*;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{


    private UserRepository repository;

    private EventPublisher publisher;

    private MailSender sender;

    public UserServiceImpl(UserRepository repository, EventPublisher publisher, MailSender sender) {
        this.repository = repository;
        this.publisher = publisher;
        this.sender = sender;
    }

    @Override
    @Transactional
    public User create(User user) {
        checkNew(user);
        User newUser = repository.save(user);
        sendRegistrationMessage(newUser);
        publisher.publish(new UserCreatedEvent(newUser));
        return newUser;
    }

    private void sendRegistrationMessage(User user) {
        sender.send(new Email(user.getEmail(),"templete","welcome"));
    }

    @Override
    public User get(int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.getById(userId),userId);
    }

    @Override
    public List<User> getAll() {
        return  repository.getAll();
    }
}

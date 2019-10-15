package com.own.space.service;

import com.own.space.domain.AuthUser;
import com.own.space.domain.User;
import com.own.space.util.events.user.UserCreatedEvent;
import com.own.space.util.events.EventPublisher;
import com.own.space.util.mail.EmailMessage;
import com.own.space.util.mail.MailSender;
import com.own.space.repository.UserRepository;
import com.own.space.util.exceptions.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        sender.send(new EmailMessage(user.getEmail(),"template","welcome"));
    }

    @Override
    public User get(int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.getById(userId),userId);
    }

    @Override
    public List<User> getAll() {
        return  repository.getAll();
    }

    @Override
    public User getByName(String name) throws NotFoundException {
        return checkNotFoundWithCredentials(repository.getByUsername(name),name);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return checkNotFoundWithCredentials(repository.getByEmail(email),email);
    }

    @Override
    public UserDetails loadUserByUsername(String mailOrName) throws UsernameNotFoundException {
        if(mailOrName == null || mailOrName.isBlank()){
            throw new UsernameNotFoundException("mailOrName must be not empty");
        }
        User user;
        if(mailOrName.contains("@")){
            user = repository.getByEmail(mailOrName);
        }else{
            user = repository.getByUsername(mailOrName);
        }
        if(user==null){
            throw new UsernameNotFoundException(String.format("user with username %s not found",mailOrName));
        }
        return AuthUser.getPrincipal(user);
    }
}

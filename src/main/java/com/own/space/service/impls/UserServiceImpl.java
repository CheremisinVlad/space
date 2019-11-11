package com.own.space.service.impls;

import com.own.space.domain.AuthUser;
import com.own.space.domain.User;
import com.own.space.service.UserService;
import com.own.space.util.events.user.UserCreatedEvent;
import com.own.space.util.events.EventPublisher;
import com.own.space.util.mail.EmailMessage;
import com.own.space.util.mail.MailSender;
import com.own.space.repository.UserRepository;
import com.own.space.util.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.own.space.service.validation.Validation.*;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


    private UserRepository repository;

    private EventPublisher publisher;

    private MailSender sender;

    @Autowired
    public UserServiceImpl(UserRepository repository, EventPublisher publisher, MailSender sender) {
        this.repository = repository;
        this.publisher = publisher;
        this.sender = sender;
    }
    public UserServiceImpl() {
    }

    @Override
    @Transactional
    public User create(User user) {
        checkNew(user);
        LOG.debug("create user: {}",user);
        User newUser = repository.save(user);
      //  sendRegistrationMessage(newUser);
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

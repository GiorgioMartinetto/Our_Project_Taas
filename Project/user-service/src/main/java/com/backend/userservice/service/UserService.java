package com.backend.userservice.service;

import com.backend.userservice.dto.EmailUpdateRequest;
import com.backend.userservice.dto.PasswordUpdateRequest;
import com.backend.userservice.dto.UserRequest;
import com.backend.userservice.model.User;
import com.backend.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public void createUser(UserRequest userRequest){
        User user = User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
        userRepository.save(user);
        log.info("User {} is create", user.getId());
    }

    public void updateEmail(EmailUpdateRequest emailUpdateRequest){
        Optional<User> user = userRepository.findById(emailUpdateRequest.getId());

        if(user.isPresent()) {
            User _user = user.get();
            _user.setEmail(emailUpdateRequest.getEmail()); //TODO:Inseire il controllo sulla mail
            userRepository.save(_user);
        }else{
            throw new IllegalArgumentException("User id invalid");
        }

    }

    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest){
        Optional<User> user = userRepository.findById(passwordUpdateRequest.getId());

        if(user.isPresent()) {
            User _user = user.get();
            if(_user.getPassword().equals(passwordUpdateRequest.getOldPassword()) &&
                !_user.getPassword().equals(passwordUpdateRequest.getNewPassword())){
                _user.setPassword(passwordUpdateRequest.getNewPassword());
                userRepository.save(_user);
            } else {
                throw new IllegalArgumentException("Invalid password");
            }

        }else{
            throw new IllegalArgumentException("User id invalid");
        }

    }


}

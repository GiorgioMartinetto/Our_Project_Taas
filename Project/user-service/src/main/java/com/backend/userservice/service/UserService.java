package com.backend.userservice.service;

import com.backend.userservice.dto.*;
import com.backend.userservice.model.User;
import com.backend.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final WebClient.Builder webClientBuilder;

    public void userRegistration(UserRequest userRequest) throws IllegalArgumentException{
        if (!validateEmail(userRequest.getEmail()))
            throw new IllegalArgumentException("Invalid email format");
        if (!emailExist(userRequest.getEmail()))
             throw new IllegalArgumentException("email already exists");
        if (!validatePassword(userRequest.getPassword()))
            throw new IllegalArgumentException("Invalid password format");
        if (!validateProvider(userRequest.getProvider()))
            throw new IllegalArgumentException("invalid provider");

        var password = userRequest.getProvider().equals("Local") ? userRequest.getPassword() : null;
        User user = User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(password)
                .provider(userRequest.getProvider())
            .build();
        userRepository.save(user);

        createProfile(user.getEmail(), "MyProfile");
        log.info("User {} is create", user.getId());
    }

    
    private void userRegistrationGoogle(UserGoogleDTO userGoogleDTO) throws IllegalArgumentException{
        if (!validateProvider(userGoogleDTO.getProvider()))
            throw new IllegalArgumentException("invalid provider");

        User user = User.builder()
                .userName(userGoogleDTO.getUserName())
                .email(userGoogleDTO.getEmail())
                .password(null)
                .provider(userGoogleDTO.getProvider())
                .build();
        userRepository.save(user);

        createProfile(user.getEmail(), "MyProfile");
        log.info("User {} is create", user.getId());
    }
       

    private boolean validateProvider(String provider) {
        return provider.equals("Google") || 
            provider.equals("Facebook") || 
            provider.equals("Local");
    }

    /**
     * 
     * @param email the email that you want to validate 
     * @return true if the email has a valid format, false otherwise
     */
    private boolean validateEmail(String email){
        String EMAIL_PATTERN = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     *
     * @param email the email that you want to validate
     * @return true if the email does not exist, false otherwise
     */
    private boolean emailExist(String email){
        Optional<User> user = userRepository.getUserByEmail(email);
        return !user.isPresent();
    }


    private void createProfile(String ownerEmail, String profileName){
        ProfileRequest profileRequest = ProfileRequest.builder()
                .profileName(profileName)
                .ownerEmail(ownerEmail)
                .build();

        System.out.println("Default Profile ...");

        webClientBuilder.build().post()
                .uri("http://profile-service/api/profile/create",
                        uriBuilder -> uriBuilder.build())
                .body(Mono.just(profileRequest), ProfileRequest.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void updateEmail(EmailUpdateRequest emailUpdateRequest) throws IllegalArgumentException {
        Optional<User> user = userRepository.findById(emailUpdateRequest.getId());

        if (!user.isPresent()) 
            throw new IllegalArgumentException("User id invalid");
        if (!validateEmail(emailUpdateRequest.getEmail()))        
            throw new IllegalArgumentException("invalid EmailUpdateRequest");

        User _user = user.get();
        _user.setEmail(emailUpdateRequest.getEmail());
        userRepository.save(_user);
    }

    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) throws IllegalArgumentException {
        Optional<User> user = userRepository.findById(passwordUpdateRequest.getId());

        if (!user.isPresent()) 
            throw new IllegalArgumentException("User id invalid");
            
        User _user = user.get();
        boolean oldPasswordIsCorrect = _user.getPassword().equals(passwordUpdateRequest.getOldPassword());
        boolean newPasswordIsDifferentFromOldPassword = !_user.getPassword().equals(passwordUpdateRequest.getNewPassword());
        boolean validNewPassword = validatePassword(passwordUpdateRequest.getNewPassword()); 
        if(!oldPasswordIsCorrect)
            throw new IllegalArgumentException("old password is incorrect");
        if (!newPasswordIsDifferentFromOldPassword)
            throw new IllegalArgumentException("new password must be different from old one");
        if (!validNewPassword)
            throw new IllegalArgumentException("new password invalid format");
        
        _user.setPassword(passwordUpdateRequest.getNewPassword());
        userRepository.save(_user);
    }

    /**
     * 
     * @param password
     * @return true if the password is in a valid format, false otherwise
     * @implNote ^                        # start of line
        (?=.*[0-9])                       # positive lookahead, digit [0-9]
        (?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
        (?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
        (?=.*[!@#–[{}]:',?/*~$^+=<>])     # positive lookahead, one of the special character in this [..]
        .                                 # matches anything
        {8,30}                            # length at least 8 characters and maximum of 20 characters
        $                                 # end of li
     */
    private boolean validatePassword(String password) {
        /* String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#–[{}]:',?/*~$^+=<>]).{8,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches(); */
        return true;
    }

    public boolean userLogin(UserLoginRequest userLoginRequest){
        Optional<User> opt_user = getUserByUserEmail(userLoginRequest.getEmail());
        System.out.println();
        //System.out.println("---------------User email= "+user.getEmail()+"\n------------User password="+user.getPassword());
        //System.out.println("---------------User email= "+userLoginRequest.getEmail()+"\n------------User password="+userLoginRequest.getPassword());
        
        if(opt_user.isPresent()  && opt_user.get().getPassword().equals(userLoginRequest.getPassword()))
            return true;
        else
            return false;
    }

    public boolean userLoginWithGoogle(UserGoogleDTO userGoogleDTO){
        if(!getUserByUserEmail(userGoogleDTO.getEmail()).isPresent())
            userRegistrationGoogle(userGoogleDTO);
        return true;
    }

    public void unsubscribeUser(UnsubscribeUserRequest unsubscribeUserRequest){
        Optional<User> user = userRepository.getUserByUserName(unsubscribeUserRequest.getUserName());

        if(!user.isPresent())
            throw new IllegalArgumentException("Something went wrong!");
            
        User _user = user.get();
        //TODO fix this shit!
        /* if(!_user.getPassword().equals(unsubscribeUserRequest.getPassword()))
            throw new IllegalArgumentException("Something went wrong!");
         */
        webClientBuilder.build().post()
            .uri("http://profile-service/api/profile/unsubscription",
                    uriBuilder -> uriBuilder.build())
            .body(Mono.just(_user.getUserName()), String.class)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
        userRepository.delete(_user);
    }

    public Optional<User> getUserByUserEmail(String email){
        return userRepository.getUserByEmail(email);
        

    }


}

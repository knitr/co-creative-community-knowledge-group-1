package com.cardiff.service;

import com.cardiff.domain.UserDto;
import com.cardiff.entity.User;
import com.cardiff.exception.UserAlreadyExistException;
import com.cardiff.repository.UserRepository;
import com.cardiff.service.iface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * This method save user details in to the database
     *
     * @param userDto
     * @return
     * @throws UserAlreadyExistException
     */
    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        //password encryption
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        userDto.setPassword(encodedPassword);
        createUserFromDto(userDto);
        return userRepository.save(createUserFromDto(userDto));
    }

    /**
     * This method will convert dto to entity user object
     *
     * @param userDto
     * @return
     */
    private User createUserFromDto(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        return user;

    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
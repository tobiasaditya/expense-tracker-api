package com.obider.expensetrackerapi.user.service;

import com.obider.expensetrackerapi.user.entity.User;
import com.obider.expensetrackerapi.user.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->new IllegalStateException("username/password invalid"));

        //Check password
        if (!BCrypt.checkpw(password,user.getPassword())){
            throw new IllegalStateException("username/password invalid");
        }


        return user;
    }

    @Override
    public User registerUser(User user){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        String email = user.getEmail();
        if (email!= null){
            email = email.toLowerCase();
        }

        if (!pattern.matcher(email).matches()){
            throw new IllegalStateException("Invalid email format");
        }
        Integer count = userRepository.getCountByEmail(email);
        if (count > 0){
            throw new IllegalStateException("Email already taken");
        }

        //hashed password
        String hashedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return user;
    }

    @Override
    public User findUserById(Integer userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()){
            return null;
        }
        return foundUser.get();
    }
}













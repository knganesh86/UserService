package com.example.userserviceapi.services;

import com.example.userserviceapi.models.Token;
import com.example.userserviceapi.models.User;
import com.example.userserviceapi.repos.TokenRepo;
import com.example.userserviceapi.repos.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class  UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepo userRepo;
    private final TokenRepo tokenRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepo userRepo,
                       TokenRepo tokenRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepo = userRepo;
        this.tokenRepository = tokenRepository;
    }
    public User signUp(String email, String name, String password){
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        user.setCreatedAt(LocalDateTime.now());
        return userRepo.save(user);
    }

    public User validateToken(String tokenvalue) {
        Optional<Token> token =
                tokenRepository.findByValueAndExpiryAtGreaterThan(tokenvalue, new Date());
        if(token.isEmpty()) {
            throw new RuntimeException("InvalidToken");
        }

        return token.get().getUser();
    }
    public Token login(String email, String password) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if(!bCryptPasswordEncoder.matches(password,user.get().getHashedPassword())){
            throw new RuntimeException("Incorrect password");
        }
        Token token = generateToken(user.get());
        tokenRepository.save(token);

        return token;
    }

    private Token generateToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(10));
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysLater = currentDate.plusDays(30);
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        token.setExpiryAt(expiryDate);
        return token;
    }
}

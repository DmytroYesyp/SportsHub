package com.sportshub.service.user;

import com.sportshub.entity.role.Roles;
import com.sportshub.entity.user.User;
import com.sportshub.repository.role.RoleRepository;
import com.sportshub.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class UsersService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<String> addNewUser(User user) {
        boolean userOptional = userRepository.findUserByEmail(user.getEmail()).isPresent();
        if (userOptional) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users/registerUser").toUriString());
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<String> setRole(Long userId,Long roleId){

        User user = userRepository.getById(userId);
        Roles role = roleRepository.getById(roleId);


        role.getUser().add(user);

        role.setUser(role.getUser());

        user.getRoles().add(role);

        user.setRoles(user.getRoles());


        roleRepository.save(role);
        userRepository.save(user);

        return ResponseEntity.ok("User id "+userId.toString() +" Role id "+ roleId.toString());
    }

    public List<User> GetUsers() {
        return userRepository.findAll();
    }

    public User getByEmail(String email){
        return userRepository.findUserByEmail(email).orElse(null);
    }



    public ResponseEntity<Set<Roles>> getUserRole (long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userRepository.findById(userId).get().getRoles());
    }


    public ResponseEntity<User> getUserById(long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userRepository.findById(userId).get());
    }

    public ResponseEntity<Long> deleteUser(long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok(userId);
    }

    public ResponseEntity updateUser(long userId, User upd_user) {

        boolean trigger;


        if (userRepository.findById(userId).isEmpty())
            return ResponseEntity.notFound().build();

        User user = userRepository.getById(userId);

        if (upd_user.getEmail() != null && upd_user.getEmail().length() > 0 && !Objects.equals(user.getEmail(), upd_user.getEmail())) {
            user.setEmail(upd_user.getEmail());
        }


        if (upd_user.getFirstName() != null && upd_user.getFirstName().length() > 0 && !Objects.equals(user.getFirstName(), upd_user.getFirstName())) {
            user.setFirstName(upd_user.getFirstName());
        }

        if (upd_user.getLastName() != null && upd_user.getLastName().length() > 0 && !Objects.equals(user.getLastName(), upd_user.getLastName())) {
            user.setLastName(upd_user.getLastName());
        }


        if (upd_user.getPassword() != null && upd_user.getPassword().length() > 8 && !Objects.equals(user.getPassword(), upd_user.getPassword())) {
            user.setPassword(upd_user.getPassword());
        }


        userRepository.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/registerUser").toUriString());
        return ResponseEntity.created(uri).body(null);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Default_Role"));
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);
    }
}

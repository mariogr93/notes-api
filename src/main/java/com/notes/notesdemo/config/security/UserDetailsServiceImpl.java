package com.notes.notesdemo.config.security;


import com.notes.notesdemo.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthenticationRepository authService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.authService.findByEmail(username).map(user -> new UserDetailsImpl(user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole()))
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found Exception"));
    }
}

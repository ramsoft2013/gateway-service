package com.authservice.security;

import com.authservice.feignInterface.AdminInterface;
import com.authservice.responseDTO.AdminResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private AdminInterface adminInterface;

    public CustomUserDetailsService(AdminInterface adminInterface) {
        this.adminInterface = adminInterface;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("loadUserByUsername  username::"+username);
        AdminResponseDTO adminResponseDTO = adminInterface.fetchAdminByUsername(username);
        System.out.println("loadUserByUsername  adminResponseDTO::"+adminResponseDTO.getUsername());
        List<GrantedAuthority> grantedAuthorities = adminResponseDTO.getRoles()
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        System.out.println("loadUserByUsername  adminResponseDTO grantedAuthorities");
        grantedAuthorities.forEach(e->System.out.println(e));
        System.out.println("loadUserByUsername  adminResponseDTO grantedAuthorities end");
        return new User(String.join("-", username, adminResponseDTO.getEmailAddress()),
                adminResponseDTO.getPassword(), true, true, true,
                true, grantedAuthorities);
    }
}

package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.UsuarioRepository;
import com.upc.babyhealth.models.entity.RolEnum;
import com.upc.babyhealth.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username);

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (RolEnum r: RolEnum.values()) {
            if(usuario.getRol().getNombreRol() == r)
                authorities.add(new SimpleGrantedAuthority("ROLE_" + r.toString()));
        }

        User user = new User( usuario.getNombreUsuario(),usuario.getContrasenia(), authorities);
        return user;
    }
}

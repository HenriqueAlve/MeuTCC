package com.example.padaria.security;

import com.example.padaria.usuario.model.AuthenticationDto;
import com.example.padaria.usuario.model.LoginResponseDto;
import com.example.padaria.usuario.model.UserDTO;
import com.example.padaria.usuario.model.Usuario;
import com.example.padaria.usuario.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    UserRepository repository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;

    public List<Usuario> getAll(){
        return repository.findAll();
    }

    public Optional<Usuario> getById(Long id){
        return repository.findById(id);
    }

    public LoginResponseDto login(AuthenticationDto authenticationDto) {
        System.out.println(authenticationDto);
        var UsuarionamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password());
        var auth = this.authenticationManager.authenticate(UsuarionamePassword);
        Usuario authenticatedUsuario = (Usuario) auth.getPrincipal();
        var token = tokenService.generateToken(authenticatedUsuario);
        String role = authenticatedUsuario.getRole().name();

        return new LoginResponseDto(token, role);
    }

    public String register(UserDTO registerDto){
        if (this.repository.findByUsername(registerDto.username()) != null) throw new EntityExistsException("Usuário já existente.");
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        Usuario newCLiente = new Usuario(registerDto, encryptedPassword);
        this.repository.save(newCLiente);
        return newCLiente.toString();
    }

    public String registerUser(UserDTO registerDto) {
        if (this.repository.findByUsername(registerDto.username()) != null) {
            throw new EntityExistsException("Usuário já existente.");
        }


        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());


        Usuario newClient = new Usuario(registerDto, encryptedPassword);
        newClient.setRole("USER");


        this.repository.save(newClient);

        return newClient.toString();
    }




}

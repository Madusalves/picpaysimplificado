package com.picpaysimplificado.picpaysimplificado.services;

import com.picpaysimplificado.picpaysimplificado.domain.user.usuario.User;
import com.picpaysimplificado.picpaysimplificado.domain.user.usuario.UserType;
import com.picpaysimplificado.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário nao autorizado");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("saldo insuficiente");
        }
    }
    public User findUserById(){
        return this.repository.findById(id).orElseThrow(() -> new Exception("Usuario nao  encontrado"))
    }

    public void saveUser (User user){
        this.repository.save(user);
    }
}

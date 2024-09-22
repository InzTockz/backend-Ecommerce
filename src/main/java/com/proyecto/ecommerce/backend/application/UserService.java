package com.proyecto.ecommerce.backend.application;

import com.proyecto.ecommerce.backend.domain.model.User;
import com.proyecto.ecommerce.backend.domain.port.IUserRepository;

public class UserService {

    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User save (User user){
        return iUserRepository.save(user);
    }

    public User findByEmail(String email){
        return iUserRepository.findByEmail(email);
    }

    public User findById(Integer id){
        return this.iUserRepository.findById(id);
    }
}

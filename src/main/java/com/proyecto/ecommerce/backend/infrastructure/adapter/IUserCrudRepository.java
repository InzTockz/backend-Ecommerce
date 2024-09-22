package com.proyecto.ecommerce.backend.infrastructure.adapter;

import com.proyecto.ecommerce.backend.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Integer> {
}

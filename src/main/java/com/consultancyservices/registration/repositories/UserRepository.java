package com.consultancyservices.registration.repositories;

import com.consultancyservices.registration.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

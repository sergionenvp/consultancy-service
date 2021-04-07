package com.consultancygroup.registration.repositories;

import com.consultancygroup.registration.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

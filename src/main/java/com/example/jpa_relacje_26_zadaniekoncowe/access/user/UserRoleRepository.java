package com.example.jpa_relacje_26_zadaniekoncowe.access.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    Optional<UserRole> findByName(String name);
}

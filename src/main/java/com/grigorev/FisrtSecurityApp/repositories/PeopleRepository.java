package com.grigorev.FisrtSecurityApp.repositories;

import com.grigorev.FisrtSecurityApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);
}
// Напрямую вызывать методы из репозитория некорректно, поэтому создадим Service

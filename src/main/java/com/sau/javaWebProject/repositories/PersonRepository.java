package com.sau.javaWebProject.repositories;

import com.sau.javaWebProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}

package com.example.backend.repositories;

import com.example.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.models.Country;

import java.util.Optional;

@Repository
public interface CountryRepository  extends JpaRepository<Country, Long>
{
    Optional<Country> findByName(String name);
}
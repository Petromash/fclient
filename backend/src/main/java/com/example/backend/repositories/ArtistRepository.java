package com.example.backend.repositories;

import com.example.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.models.Artist;

import java.util.Optional;

@Repository
public interface ArtistRepository  extends JpaRepository<Artist, Long>
{
}
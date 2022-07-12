package com.mateusz.home.repository;

import com.mateusz.home.model.Inmate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmateRepository extends JpaRepository<Inmate, Long> {
}

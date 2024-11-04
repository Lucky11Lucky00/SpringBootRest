package com.sathya.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sathya.rest.entity.Employeeentity;

@Repository

public interface Employeerepository extends JpaRepository<Employeeentity, Long> {
	
	Optional<Employeeentity> findByEmail(String email);

	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employeeentity e WHERE e.email = :email")
	boolean existsByEmail(String email);

	@Transactional
	void deleteByEmail(String email);

}

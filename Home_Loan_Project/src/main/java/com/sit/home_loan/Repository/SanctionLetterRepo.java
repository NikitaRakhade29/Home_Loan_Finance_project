package com.sit.home_loan.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit.home_loan.Model.SanctionLetter;
@Repository
public interface SanctionLetterRepo extends JpaRepository<SanctionLetter, Long>{

}

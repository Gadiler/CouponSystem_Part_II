/*
 * Copyright (c) / / Author Gadiler 5/4/2021.
 *  All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */

package com.example.demo.accessingdatajpa;

import com.example.demo.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
//    List<Company> findByNameAndEmail(String name, String email);
//
//    List<Company> findAllByOrderById(); //Not sure if needed
//
//    List<Company> findAllByOrderByIdDesc();
//
//    List<Company> findAllByOrderByIdAsc();
//
//    List<Company> findByNameOrId(String name, int id);

}

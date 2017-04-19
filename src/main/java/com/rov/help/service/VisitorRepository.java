package com.rov.help.service;

import java.util.List;

import com.rov.help.domain.Visitor;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by rov on 3/22/2017.
 */
public interface VisitorRepository extends CrudRepository<Visitor, Long>{

    List<Visitor> findByFirstName(String firstName);
}

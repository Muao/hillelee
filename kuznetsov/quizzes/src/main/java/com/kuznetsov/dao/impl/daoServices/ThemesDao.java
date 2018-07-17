package com.kuznetsov.dao.impl.daoServices;

import com.kuznetsov.entities.Themes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ThemesDao extends CrudRepository<Themes, Integer> {


    public Themes getThemesById(Integer id);


}

package com.ditraacademy.travelagency.core;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResposotory extends JpaRepository<User,Integer> { //entity + primary key int
}

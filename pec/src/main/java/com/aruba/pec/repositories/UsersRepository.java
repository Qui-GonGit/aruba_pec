package com.aruba.pec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aruba.pec.dao.entities.User;
@Repository
public interface  UsersRepository extends JpaRepository<User, Integer> {

	User findUserByEmail(String email);

	User findUserByIdAruba(String idAruba);
}

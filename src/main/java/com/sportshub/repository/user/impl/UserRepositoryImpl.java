package com.sportshub.repository.user.impl;

import com.sportshub.exception.NotFoundException;
import com.sportshub.patch.UserPatch;
import com.sportshub.repository.user.CustomUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements CustomUserRepository {
    private static final String PATCH_UPDATE_USER_BY_ID_QUERY_TEMPLATE = """
            UPDATE users SET
                %s
            WHERE id = :id
            """;


    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void patch(Long id, UserPatch userPatch) {
        List<String> assigments = new ArrayList<>(7);
        MapSqlParameterSource parameters = new MapSqlParameterSource("id", id);

        if (userPatch.isEmailUpdated()) {
            String email = userPatch.getEmail();

            assigments.add("email = :email");
            parameters.addValue("email", email);
        }

        if (userPatch.isPasswordUpdated()) {
            String passwordHash = bCryptPasswordEncoder.encode(userPatch.getPassword());

            assigments.add("password_hash = :passwordHash");
            parameters.addValue("passwordHash", passwordHash);
        }

        if (userPatch.isFirstNameUpdated()) {
            String firstName = userPatch.getFirstName();

            assigments.add("first_name = :firstName");
            parameters.addValue("firstName", firstName);
        }

        if (userPatch.isMiddleNameUpdated()) {
            String middleName = userPatch.getMiddleName();

            assigments.add("middle_name = :middleName");
            parameters.addValue("middleName", middleName);
        }

        if (userPatch.isLastNameUpdated()) {
            String lastName = userPatch.getLastName();

            assigments.add("last_name = :lastName");
            parameters.addValue("lastName", lastName);
        }

        if (userPatch.isPhoneUpdated()) {
            String phone = userPatch.getPhone();

            assigments.add("phone = :phone");
            parameters.addValue("phone", phone);
        }

        if (userPatch.isInfoUpdated()) {
            String info = userPatch.getInfo();

            assigments.add("info = :info");
            parameters.addValue("info", info);
        }

        String assigmentStr = String.join(", ", assigments);
        String query = String.format(PATCH_UPDATE_USER_BY_ID_QUERY_TEMPLATE, assigmentStr);

        int affectedRows = jdbcTemplate.update(query, parameters);

        if (affectedRows == 0) {
            throw new NotFoundException("User with id " + id + " not found!");
        }
    }
}


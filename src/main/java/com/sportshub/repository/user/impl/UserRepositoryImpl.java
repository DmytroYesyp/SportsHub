package com.sportshub.repository.user.impl;

import com.sportshub.entity.user.UserEntity;
import com.sportshub.exception.ConflictException;
import com.sportshub.exception.NotFoundException;
import com.sportshub.patch.UserPatch;
import com.sportshub.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private static final String INSERT_USER_QUERY = """
            INSERT INTO users (
                email,
                password_hash,
                first_name,
                middle_name,
                last_name,
                phone,
                state,
                info
            ) VALUES (
                :email,
                :passwordHash,
                :firstName,
                :middleName,
                :lastName,
                :phone,
                :state,
                :info
            )
            """;

    private static final String SELECT_USER_QUERY = """
            SELECT
                id,
                email,
                password_hash,
                first_name,
                middle_name,
                last_name,
                phone,
                state,
                info,
                created_at
            FROM users
            """;

    private static final String SELECT_USER_BY_ID_QUERY = """
            SELECT
                id,
                email,
                password_hash,
                first_name,
                middle_name,
                last_name,
                phone,
                state,
                info,
                created_at
            FROM users
            WHERE id = :id
            """;

    private static final String SELECT_USER_BY_EMAIL_QUERY = """
            SELECT
                id,
                email,
                password_hash
            FROM users
            WHERE email = :email
            """;

    private static final String UPDATE_USER_BY_ID_QUERY = """
            UPDATE users SET
                email = :email,
                first_name = :firstName,
                middle_name = :middleName,
                last_name = :lastName,
                phone = :phone,
                info = :info
            WHERE id = :id
            """;

    private static final String PATCH_UPDATE_USER_BY_ID_QUERY_TEMPLATE = """
            UPDATE users SET
                %s
            WHERE id = :id
            """;

    private static final String DELETE_USER_BY_ID_QUERY = """
            DELETE FROM users WHERE id = :id
            """;

    private static final RowMapper<UserEntity> USER_ROW_MAPPER = (rs, rowNum) -> {
        UserEntity entity = new UserEntity();

        entity.setId(rs.getObject("id", Long.class));
        entity.setEmail(rs.getString("email"));
        entity.setFirstName(rs.getString("first_name"));
        entity.setMiddleName(rs.getString("middle_name"));
        entity.setLastName(rs.getString("last_name"));
        entity.setPhone(rs.getString("phone"));
        entity.setState(rs.getString("state"));
        entity.setInfo(rs.getString("info"));

        return entity;
    };

    private static final RowMapper<UserEntity> USER_AUTH_MAPPER = (rs, rowNum) -> {
        UserEntity entity = new UserEntity();

        entity.setId(rs.getObject("id", Long.class));
        entity.setEmail(rs.getString("email"));
        entity.setPasswordHash(rs.getString("password_hash"));

        return entity;
    };

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity create(UserEntity userEntity) {
        SqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("email", userEntity.getEmail())
                .addValue("passwordHash", userEntity.getPasswordHash())
                .addValue("firstName", userEntity.getFirstName())
                .addValue("middleName", userEntity.getMiddleName())
                .addValue("lastName", userEntity.getLastName())
                .addValue("phone", userEntity.getPhone())
                .addValue("state", userEntity.getPhone())
                .addValue("info", userEntity.getInfo());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(INSERT_USER_QUERY, sqlParameters, keyHolder);
        } catch (DuplicateKeyException e) {
            Throwable cause = e.getCause();
            if (cause instanceof SQLException) {
                if (cause.getMessage().contains("duplicate key value violates unique constraint \"users_email_key\"")) {
                    String errorMessage = String.format("User with email '%s' already exists!", userEntity.getEmail());
                    throw new ConflictException(errorMessage);
                }
            }

            throw e;
        }

        Long customerId = (Long) keyHolder.getKeys().get("id");
        userEntity.setId(customerId);

        return userEntity;
    }

    @Override
    public List<UserEntity> findAll() {
        return jdbcTemplate.query(SELECT_USER_QUERY, USER_ROW_MAPPER);
    }

    @Override
    public UserEntity find(Long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_USER_BY_ID_QUERY,
                    new MapSqlParameterSource("id", id), USER_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User with id " + id + " not found!");
        }
    }

    @Override
    public UserEntity findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL_QUERY,
                    new MapSqlParameterSource("email", email), USER_AUTH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User with email " + email + " not found!");
        }
    }

    @Override
    public void update(Long id, UserEntity entity) {
        int affectedRows = jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY, new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("email", entity.getEmail())
                .addValue("passwordHash", entity.getPasswordHash())
                .addValue("firstName", entity.getFirstName())
                .addValue("middleName", entity.getMiddleName())
                .addValue("lastName", entity.getLastName())
                .addValue("phone", entity.getPhone())
                .addValue("state", entity.getPhone())
                .addValue("info", entity.getInfo()));

        if (affectedRows == 0) {
            throw new NotFoundException("User with id " + entity.getId() + " not found!");
        }
    }

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

    @Override
    public void delete(Long id) {
        int affectedRows = jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, new MapSqlParameterSource("id", id));

        if (affectedRows == 0) {
            throw new NotFoundException("User with id " + id + " not found!");
        }
    }
}


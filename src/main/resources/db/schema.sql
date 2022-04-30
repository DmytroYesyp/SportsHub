DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE kinds_of_sport
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE NOT NULL
);

CREATE TABLE league
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128) NOT NULL,
    league_date INTEGER NOT NULL,
    UNIQUE(name, league_date)
);

CREATE TABLE news
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(255) UNIQUE NOT NULL,
    description      TEXT         NOT NULL,
    news_date        TIMESTAMP,
    publication_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    image            VARCHAR(128),
    league_id        BIGINT       NOT NULL REFERENCES league (id) ON DELETE CASCADE
);

CREATE TABLE news_kind_of_sport
(
    news_id           BIGINT NOT NULL REFERENCES news (id) ON DELETE CASCADE,
    kinds_of_sport_id BIGINT NOT NULL REFERENCES kinds_of_sport (id) ON DELETE CASCADE,
    PRIMARY KEY (news_id, kinds_of_sport_id)
);

CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    email         VARCHAR(30) UNIQUE NOT NULL,
    password_hash VARCHAR(128)       NOT NULL,
    first_name    VARCHAR(128)       NOT NULL,
    middle_name   VARCHAR(128)       ,
    last_name     VARCHAR(128)       NOT NULL,
    phone         VARCHAR(16)        ,
    state         VARCHAR(64),
    info          TEXT,
    created_at    TIMESTAMP(3)       NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
);

CREATE TABLE users_kind_of_sport
(
    users_id          BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    kinds_of_sport_id BIGINT NOT NULL REFERENCES kinds_of_sport (id) ON DELETE CASCADE,
    PRIMARY KEY (users_id, kinds_of_sport_id)
);

CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE NOT NULL
);

CREATE TABLE permissions
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE NOT NULL
);

CREATE TABLE permission_roles
(
    role_id BIGINT NOT NULL REFERENCES roles (id) ON DELETE CASCADE,
    permission_id BIGINT NOT NULL REFERENCES  permissions (id) ON DELETE CASCADE,
    PRIMARY KEY (role_id,  permission_id)
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    role_id BIGINT NOT NULL REFERENCES roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);



CREATE TABLE team
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(128) UNIQUE NOT NULL,
    coach VARCHAR(128) NOT NULL,
    state VARCHAR(64)
);

CREATE TABLE comments
(
    id   BIGSERIAL PRIMARY KEY,
    text VARCHAR(128) NOT NULL
);

CREATE TABLE user_comments
(
    user_id    BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    comment_id BIGINT NOT NULL REFERENCES comments (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, comment_id)
);

CREATE TABLE team_news
(
    news_id BIGINT NOT NULL REFERENCES news (id) ON DELETE CASCADE,
    team_id BIGINT NOT NULL REFERENCES team (id) ON DELETE CASCADE,
    PRIMARY KEY (news_id, team_id)
);

CREATE TABLE user_team
(
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    team_id BIGINT NOT NULL REFERENCES team (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, team_id)
);

CREATE TABLE user_league
(
    user_id   BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    league_id BIGINT NOT NULL REFERENCES league (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, league_id)
);

CREATE TABLE survey
(
    id    BIGSERIAL PRIMARY KEY,
    topic VARCHAR(128) NOT NULL,
    text  VARCHAR(128) NOT NULL
);

CREATE TABLE user_survey
(
    user_id   BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    survey_id BIGINT NOT NULL REFERENCES survey (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, survey_id)
);

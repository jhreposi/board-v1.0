create DATABASE if not exists ebrainsoft_study;

use ebrainsoft_study;

create table if not exists category (
     id bigint auto_increment primary key,
     name varchar(255)
    )

create table if not exists article (
    id bigint auto_increment primary key,
    title varchar(100) not null,
    author varchar(100) not null,
    password varchar(255) not null,
    content text not null,
    view_count int default 0,
    post_date datetime default now(),
    edit_date datetime,
    FOREIGN KEY (category_id) REFERENCES category (id)
    );
create table if not exists comment (
    id bigint auto_increment primary key,
    comment text not null,
    post_date datetime default now(),
    FOREIGN KEY (article_id) REFERENCES article (id)
);
create table if not exists file (
    id bigint auto_increment primary key,
    filename varchar(255),
    original_name varchar(255),
    dir varchar(255),
    FOREIGN KEY (article_id) REFERENCES article (id)
);

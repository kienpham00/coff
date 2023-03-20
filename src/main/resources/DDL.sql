create table sys_user (
                          id int not null AUTO_INCREMENT,
                          full_name varchar(255),
                          pass_word varchar(255),
                          user_sdt varchar(255),
                          role varchar(255),
                          user_email varchar(255),
                          user_name varchar(255),
                          primary key (id))
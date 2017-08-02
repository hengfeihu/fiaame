create table s_permission (
  id                            bigint auto_increment not null,
  roleid                        integer,
  name                          varchar(255),
  permission_url                varchar(255),
  method                        varchar(255),
  description                   varchar(255),
  create_time                   datetime(6) not null,
  update_time                   datetime(6) not null,
  constraint pk_s_permission primary key (id)
);

create table s_role (
  id                            bigint auto_increment not null,
  uid                           integer,
  name                          varchar(100),
  create_time                   datetime(6) not null,
  update_time                   datetime(6) not null,
  constraint pk_s_role primary key (id)
);

create table s_user (
  id                            bigint auto_increment not null,
  name                          varchar(120),
  email                         varchar(50),
  password                      varchar(120),
  create_time                   datetime(6) not null,
  update_time                   datetime(6) not null,
  constraint pk_s_user primary key (id)
);


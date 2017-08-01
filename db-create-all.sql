create table s_resource (
  id                            bigint auto_increment not null,
  resourcestring                varchar(1000),
  resourceid                    varchar(50),
  remark                        varchar(200),
  resourcename                  varchar(400),
  methodname                    varchar(400),
  methodpath                    varchar(1000),
  create_time                   datetime(6) not null,
  update_time                   datetime(6) not null,
  constraint pk_s_resource primary key (id)
);

create table s_resource_role (
  id                            bigint auto_increment not null,
  roleid                        varchar(50),
  resourceid                    varchar(50),
  create_time                   datetime(6) not null,
  update_time                   datetime(6) not null,
  constraint pk_s_resource_role primary key (id)
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


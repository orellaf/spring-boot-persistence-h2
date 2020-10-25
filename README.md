# Specification with join

```sql
    select
        letter0_.id as id1_2_,
        letter0_.aid_id as aid_id4_2_,
        letter0_.certificate_id as certific5_2_,
        letter0_.code as code2_2_,
        letter0_.created_at as created_3_2_ 
    from
        letters letter0_ 
    left outer join
        certificates certificat1_ 
            on letter0_.certificate_id=certificat1_.id 
    left outer join
        postmen postman2_ 
            on certificat1_.postman_id=postman2_.id_rh 
    left outer join
        aids aid3_ 
            on letter0_.aid_id=aid3_.id 
    left outer join
        postmen postman4_ 
            on aid3_.postman_id=postman4_.id_rh 
    where
        postman2_.id_rh=? 
        or postman4_.id_rh=? 
    order by
        letter0_.created_at desc limit ?
```

# Tables

```sql
   create table aids (
       id integer not null,
        request_number varchar(255),
        postman_id varchar(255),
        primary key (id)
    )
```

```sql
   create table certificates (
       id integer not null,
        number varchar(255),
        postman_id varchar(255),
        primary key (id)
    )
```

```sql
    create table letters (
       id integer not null,
        code varchar(255),
        created_at timestamp,
        aid_id integer,
        certificate_id integer,
        primary key (id)
    )
```

```sql
   create table postmen (
       id_rh varchar(255) not null,
        firstname varchar(255),
        lastname varchar(255),
        primary key (id_rh)
    )
```

```sql

```

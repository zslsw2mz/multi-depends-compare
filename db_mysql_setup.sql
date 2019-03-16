use depends;
CREATE table IF NOT EXISTS file_id_mapping (
	id int unsigned not null,
    filename varchar(64) not null,
    project_name varchar(32) not null,
    primary key(id, project_name)
);
CREATE table IF NOT EXISTS dependency_record (
	src int unsigned not null,
    dest int unsigned not null,
    version int unsigned not null,
    project_name varchar(32) not null,
    import_amount int default 0,
    contain_amount int default 0,
    implement_amount int default 0,
    extend_amount int default 0,
    call_amount int default 0,
    parameter_amount int default 0,
	return_amount int default 0,
    set_amount int default 0,
    use_amount int default 0,
    create_amount int default 0,
    cast_amount int default 0,
    throw_amount int default 0,
    annotation_amount int default 0,
    total_amount int default 0,
    primary key (src, dest, version, project_name)
);

create table IF NOT EXISTS compare (
	src int unsigned not null,
    dest int unsigned not null,
    version int unsigned not null,
    project_name varchar(32) not null,
    import_change int default 0,
    contain_change int default 0,
    implement_change int default 0,
    extend_change int default 0,
    call_change int default 0,
    parameter_change int default 0,
    return_change int default 0,
    set_change int default 0,
    use_change int default 0,
    create_change int default 0,
    cast_change int default 0,
    throw_change int default 0,
    annotation_change int default 0,
    total_change int default 0,
    primary key (src, dest, version, project_name)
);


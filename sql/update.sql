update masterdb1 set f17=trim(f17);
update masterdb1 set f17=right(f17,length(f17)-1) where f17 like ':%';
update masterdb1 set f17=right(f17,length(f17)-2) where f17 like 'e:%';
alter table masterdb1 add column status varchar(10) default '';
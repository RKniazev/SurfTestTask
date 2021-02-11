insert into user(login,password,role) values ('surf','good','ADMIN');
insert into user(login,password,role) values ('other','bad','ADMIN');


insert into history_calc(date,time,request,response,user_id) values ('2021-02-06','12:00:00','2+2','4',1);
insert into history_calc(date,time,request,response,user_id) values ('2021-02-07','13:00:00','2+3','5',1);
insert into history_calc(date,time,request,response,user_id) values ('2021-02-08','16:00:00','3+2','5',2);
insert into history_calc(date,time,request,response,user_id) values ('2021-02-09','17:00:0','3+3','6',2);
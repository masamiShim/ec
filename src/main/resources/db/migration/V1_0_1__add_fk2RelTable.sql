alter table ec_db.Rel_User_Customer
  add constraint FK_Rue_customerId_Customer_id
    FOREIGN KEY (customer_id)
      references customer(id);
alter table ec_db.Rel_User_Exhibitor
  add constraint FK_Rue_exhibitorId_Exhibitor_id
    FOREIGN KEY (exhibitor_id)
      references Exhibitor(id);


alter table ec_db.Rel_User_Customer
  add constraint FK_Ruc_userId_User_id
    FOREIGN KEY (user_id)
      references User(id);

alter table ec_db.Rel_User_Exhibitor
  add constraint FK_Rue_userId_User_id
    FOREIGN KEY (user_id)
      references User(id);


/*
alter table ec_db.User
  add constraint FK_User_user_id_Ruc_user_id
    FOREIGN KEY (id)
      references rel_user_customer(user_id);

alter table ec_db.User
  add constraint FK_User_user_id_Rue_user_id
    FOREIGN KEY (id)
      references rel_user_exhibitor(user_id);

*/

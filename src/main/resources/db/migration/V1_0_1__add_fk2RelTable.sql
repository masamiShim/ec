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

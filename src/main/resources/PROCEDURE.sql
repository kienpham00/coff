create procedure sp_Login(IN UserName varchar(100), IN PassWord varchar(500),OUT IsValid int)
begin
    DECLARE a INTEGER default 0;
select count(*) into a from sys_user
where user_name = UserName
  and pass_word = Password;

SET IsValid = a;
end;

# Call procedure
call sp_Login('abc', '5', @x);
SELECT @x;
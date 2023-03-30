package com.kienpham.coffee.repository;

import com.kienpham.coffee.entity.User;
import dto.request.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Component
public class UserSql {
    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    EntityManager em;
    @Autowired
    PasswordEncoder pe;



    public boolean getUser(AuthenticationRequest objLogin){

        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sp_Login");
        // set parameters
        storedProcedure.registerStoredProcedureParameter("UserName", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("PassWord", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("IsValid", Integer.class, ParameterMode.OUT);
        storedProcedure.setParameter("UserName", objLogin.getUsername());
        storedProcedure.setParameter("PassWord", encodePass(objLogin));
        // execute SP
        storedProcedure.execute();
        // get result
        Integer isValid = (Integer)storedProcedure.getOutputParameterValue("IsValid");
//        1 = success else fail
        return isValid == 1;
    }

    private String encodePass(AuthenticationRequest obj) {
        try {
            User user = userRepository.findUserByUserName(obj.getUsername()).get();
            if (pe.matches(obj.getPassword(), user.getPassWord())) {
                return user.getPassWord();
            };
            return "-1";
        } catch (Exception ex) {
            return "-1";
        }
    }
}

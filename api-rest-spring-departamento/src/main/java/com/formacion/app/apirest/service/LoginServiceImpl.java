package com.formacion.app.apirest.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacion.app.apirest.dao.LoginDao;
import com.formacion.app.apirest.entity.Login;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDao service;

    @Override
    public Login findDni(String dni, String contraseña) {

        return service.findDni(dni, contraseña);
    }

}

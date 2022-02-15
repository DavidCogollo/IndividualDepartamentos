package com.formacion.app.apirest.service;

import com.formacion.app.apirest.entity.Login;

public interface LoginService {

    public Login findDni(String dni, String contraseña);


}
package com.classcraft.demo.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    String correo;
    String nombre;
    String rol;
    String contrasena;
    Long id;
}

package com.project.faro.util;

import java.io.Serializable;

public interface RegistroSerializable extends Serializable {
	// Método para obtener el ID del usuario que creó el registro
	Integer getUserIdCreacion();

    // Método para obtener el ID del usuario que cerró el registro
	Integer getUserIdCierre();

    // Método para establecer el ID del usuario que creó el registro
    void setUserIdCreacion(Integer userIdCreacion);

    // Método para establecer el ID del usuario que cerró el registro
    void setUserIdCierre(Integer userIdCierre);
}

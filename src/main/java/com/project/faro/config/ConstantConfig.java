package com.project.faro.config;

public interface ConstantConfig {
	//HABITACIONES
	public static final String HABITACION_STATUS_ACT = "ACT";	//Lista para usar
    public static final String HABITACION_STATUS_BAJ = "BAJ";	//Ocupada
    public static final String HABITACION_STATUS_DES = "DES";	//Fuera de estado

    //TURNOS
    public static final String TURNO_STATUS_ACT = "ACT";	//Turno activo
    public static final String TURNO_STATUS_BAJ = "BAJ";	//Turno finalizado
	public static final int CANT_HORA_X_TURNO = 2;
}

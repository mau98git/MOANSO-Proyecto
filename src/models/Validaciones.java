package models;

public class Validaciones {
    public boolean validacionNomApe(String datos){
        return datos.matches("([a-zA-Z]*|[a-zA-Z]*[\\s][a-zA-Z]*|[a-zA-Z]*[\\s][a-zA-Z]*[\\s][a-zA-Z]*)");
    }
    public boolean validacionEmail(String datos){
        return datos.matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
    public boolean validacionNumeros(String datos){
        return datos.matches("[0-9]*");
    }
    public boolean validacionLetras(String datos){
        return datos.matches("[a-zA-Z]*");
    }
    public boolean validacionEstado(String datos){
        return datos.matches("([1|0])");
    }
    public boolean validacionAcceso(String datos){
        return datos.matches("(Administrador|Recepcion)");
    }
    public boolean validacionTipoDocumento(String datos){
        return datos.matches("(DNI|Pasaporte)");
    }
    public boolean validacionHabitaciones(String datos){
        return datos.matches("[0-9]{3}");
    }
    public boolean validacionPiso(String datos){
        return datos.matches("[0-9]{1}");
    }
    public boolean validacionNumerosDecimales(String datos){
        return datos.matches("\\d+(\\.\\d{1,2})?");
    }
}

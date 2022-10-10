package cajeroautomatico_evaluacion;

import java.util.Date;

public class Historial {
    Date fecha;
    double retiro;
    double saldoNuevo;
    double saldoAnterior;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getRetiro() {
        return retiro;
    }

    public void setRetiro(double retiro) {
        this.retiro = retiro;
    }

    public double getSaldoNuevo() {
        return saldoNuevo;
    }

    public void setSaldoNuevo(double saldoNuevo) {
        this.saldoNuevo = saldoNuevo;
    }

    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }    
}

package cajeroautomatico_evaluacion;

import cajeroautomatico_evaluacion.Historial;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//cambio en Rama Develop
public class CajeroAutomatico_Evaluacion {
    static Scanner sc = new Scanner(System.in);
    static List<Historial> historialMov = new ArrayList<>();
    static double saldo=1000;
    public static void main(String[] args) {
        boolean acceso=false;
        int intentos=3;
        
        while(intentos>0){
        System.out.print("Ingresa el pin: ");
        String entrada = sc.nextLine();
        
        if(entrada.equals("1235")){
           acceso=true;
           System.out.println("Correcto");
           break;
        }else{
           intentos--;
           System.out.println("PIN Incorrecto. Intentos restantes: "+intentos);
        }
        }
        
        if(acceso){
        char answer ='S';
        while(answer == 'S' || answer=='s'){
            System.out.println("** MENU DE OPCIONES **");
            System.out.println("(1) CONSULTAR SALDO");
            System.out.println("(2) RETIRAR SALDO");
            System.out.println("(3) HISTORIAL DE MOVIMIENTOS");
            System.out.println("(4) SALIR");
            System.out.print("Seleccione la Opción: ");
            String opcion = sc.next();

            switch(opcion){
                case "1":
                    if(saldo<=0){
                        sinSaldo();
                    }else{
                        System.out.println("** CONSULTA DE SALDO **");
                        System.out.println("Su saldo actual es de $"+saldo); 
                    }
                    break;
                case "2": 
                    System.out.println("** RETIRO DE SALDO **");
                    Retiro();
                    break;
                case "3": 
                    System.out.println("** HISTORAL DE MOVIMIENTOS **");
                    Historial();
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    System.out.println("¡Opcion Incorrecta!");
                    break;
            }
            System.out.print("¿Desea realizar otra operación? S/N ");
            answer = sc.next().charAt(0);
        }
        }else{
           System.exit(0); //Programa termina
        }
    }
    
    public static void Retiro(){
        boolean valido=false;
        while(!valido){
            if(saldo<=0){
                sinSaldo();
                break;
            }else{
            System.out.print("Ingresa el Retiro: ");
            String cadena = sc.next();
            if(validaNumero(cadena)){
                double retiro = Double.parseDouble(cadena);
                if((saldo-retiro)<0){
                    System.out.println("El retiro sobrepasa tu saldo");
                }else{
                    Date fecha = new Date();
                    double saldoAnterior=saldo;
                    saldo=saldo - retiro;
                    System.out.println("Retiro correcto por $"+retiro);
                    
                    Historial historial = new Historial();
                    historial.setFecha(fecha);
                    historial.setRetiro(retiro);
                    historial.setSaldoAnterior(saldoAnterior);
                    historial.setSaldoNuevo(saldo);
                    historialMov.add(historial);
                    valido=true;
                }
            }else{
                System.out.println("Monto Incorrecto. Intente Denuevo");
            }
        }
        }
    }
    
    public static void Historial(){
        if(!historialMov.isEmpty())
        {
            for(Historial historial:historialMov){
                System.out.println(historial.getFecha()+" - R:$"+historial.getRetiro()+" - SA:$"+historial.getSaldoAnterior() +" - SN:$"+historial.getSaldoNuevo());
            }
        }else{
            System.out.println("No se tienen movimientos Registrados!!");
        }
    }
    
    public static void sinSaldo(){
        System.out.println("No se tienen fondos suficientes!!");
        System.out.println("(1) Continuar al Menú");
        System.out.println("(2) Salir");
        System.out.print("Seleccione la Opción: ");
        String opcion = sc.next();
        switch(opcion){
            case "2":
                System.exit(0);
                break;
        }
    }
    public static boolean validaNumero(String cad){

        boolean hayPunto=false;
        int posicionDelPunto=0;
        StringBuffer parteEntera = new StringBuffer();
        StringBuffer parteDecimal = new StringBuffer();
        int i=0;

        for( i=0;i<cad.length(); i++ )              	     
            if ( cad.charAt(i) == '.'){
                hayPunto=true;
            }
            if(hayPunto){
                posicionDelPunto=cad.indexOf('.');
            }
            else{
                try {
                    Integer.parseInt(cad);
                    return true;
                } catch (NumberFormatException nfe){
                    return false;
                }
            }
            if( posicionDelPunto == cad.length()-1 || posicionDelPunto== 0){
                return false;
            }

        for(i = 0; i<parteEntera.length(); i++)
            if( ! Character.isDigit(parteEntera.charAt(i)) ){
                return false;
            }

        for(i = 0; i<parteDecimal.length(); i++)
            if( ! Character.isDigit(parteDecimal.charAt(i))){
                return false;
            }
        return true;
 
    }    
}

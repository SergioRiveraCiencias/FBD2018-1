public class Consulta{

    public static void main(String [] args){
        //Inicializamos la instancia de Cliente
        System.out.println("Inicio de la Consulta");
        Cliente c= new Cliente();
        c.inicializa();
        //Propiedades
        System.out.println("Propiedades en cierto rango y metros: ");
        String [] tmp= c.rangoMetros(50000,500000,200);
        for(int i =0; i< tmp.length; i++){
            if(tmp[i]!=null){        
                System.out.println(tmp[i]);
            }
        }
        System.out.println("");
        //Multiples inmobiliarios
        System.out.println("Clientes con Multiples Inmobiliarios");
        tmp= c.multipleInmobiliario();
        for(int i =0; i< tmp.length; i++){
            if(tmp[i]!=null){        
                System.out.println(tmp[i]);
            }
        }
        System.out.println("");
        //Propiedades mas baratas
        System.out.println("Propiedades mas baratas: ");
        tmp= c.topMinimo();
        for(int i =0; i< tmp.length; i++){
            if(tmp[i]!=null){        
                System.out.println(tmp[i]);
            }
        }
        System.out.println("");
        //Propiedades mas caras
        System.out.println("Propiedades mas caras: ");
        tmp= c.topMaximo();
        for(int i =0; i< tmp.length; i++){
            if(tmp[i]!=null){        
                System.out.println(tmp[i]);
            }
        }
        System.out.println("");
        //Propiedades con mayor margen de ganancia
        System.out.println("Propiedades con mayor margen de ganancias: ");
        tmp= c.topGanancias();
        for(int i =0; i< tmp.length; i++){
            if(tmp[i]!=null){        
                System.out.println(tmp[i]);
            }
        }
        System.out.println("");
        //Propiedades mas valiosas
        System.out.println("Propiedades mas valiosas: ");
        tmp= c.topValiosas();
        for(int i =0; i< tmp.length; i++){
            if(tmp[i]!=null){        
                System.out.println(tmp[i]);
            }
        }
        System.out.println("");
        //Propiedades con mas metros
        System.out.println("Propiedades con mas metros: ");
        tmp= c.topMetros();
        for(int i =0; i< tmp.length; i++){
            if(tmp[i]!=null){        
                System.out.println(tmp[i]);
            }
        }
        System.out.println("");
    }

}

import java.io.FileNotFoundException;
import java.io.IOException;
import com.csvreader.CsvReader;

public class Cliente{
    //Variables globales
    String [] nombres = new String[100];
    String [] telefonos = new String[100] ;
    String [] direcciones = new String[100];
    String [] correos = new String[100];
    String [] m2 = new String[100];
    String [] valor_prop = new String[100];
    String [] valor_venta = new String[100];

    /*
        Metodo que inicializa las variables de consulta por medio del archivo csv
    */

    public void inicializa(){       
        try{
            CsvReader mi_csv = new CsvReader("/home/vox/Documents/FBD/FBD2018-1/Practica01/P1.csv");
            int i=0;
            mi_csv.readRecord();
            while(mi_csv.readRecord()){
               nombres[i]= mi_csv.get(0);
               telefonos[i]= mi_csv.get(1);
               direcciones[i] = mi_csv.get(2);
               m2[i] = mi_csv.get(4);
               valor_prop[i]= mi_csv.get(5);
               valor_venta[i]= mi_csv.get(6);
               i++;
            }
            mi_csv.close();
        }catch(Exception e){
               System.err.println("Error al leer el csv");
               System.exit(-1);
        }
        System.out.println("El archivo csv ha sido cargado");
    }
    
    /*
      Metodo que obtiene propiedades segun su rango de ganancia y maximo de metros cuadrados
      @return String [] un arreglo de las propiedades entre las caracteristicas.
    */
    public String[] rangoMetros(double piso, double techo, int metros){
        String result []= new String[100];
        int j=0;
        System.out.println("Generando Resultados con rango: "+piso+", "+techo+" y metros: "+metros);
        for (int i=0; i<valor_prop.length; i++){
            if(Double.parseDouble(valor_prop[i])>=piso && Double.parseDouble(valor_prop[i])<=techo && Integer.parseInt(m2[i])<=metros){        
                result [j]= j+". Nombre: "+nombres[i]+" | Direccion: "+ direcciones[i]+"| Metros: "+m2[i]+"| Valor Propiedad: "+valor_prop[i];
                j++;
            }
        }
        return result;
    }

   /*
      Metodo que obtiene los clientes con mas de 1 propiedad.
      @return String [] un arreglo con los clientes que cumplen las caracteristicas
    */   

    public String[] multipleInmobiliario(){
        String result []= new String[nombres.length];
        int contador;
        int aux=0;
        System.out.println("Buscando clientes con multiple Inmobiliario");
        for (int i=0; i<nombres.length; i++){
            contador=0;
            for(int j=0; j<nombres.length;j++){
                if(nombres[i].equals(nombres[j])){        
                    contador++;
                    if(contador == 2){
                        result[aux]=aux+". "+nombres[j];
                        aux++;
                    }
                }
            }
        }
        return result;
    
    }

   /*
      Metodo que obtiene las 10 propiedades menos costosas
      @return String [] un arreglo con las propiedades que cumplen la caracteristica
    */ 
    public String[] topMinimo(){
        String tmp []= valor_venta;
        double base=Double.parseDouble(valor_venta[0]);
        String min="";
        int index=0;
        String result []= new String[10];
        int contador=0;
        System.out.println("Generando Resultados mas baratos: ");
        while(contador<10){
          for (int i=0; i<tmp.length; i++){
              if(Double.parseDouble(tmp[i])<base && Double.parseDouble(tmp[i])!=0){
                  base=Double.parseDouble(tmp[i]);
                  min="Nombre: "+nombres[i]+" | Valor Venta: "+valor_venta[i];        
                  index=i;
              }
          }
        result [contador]=min;
        contador++;
        tmp[index]="0";
        index=0;
        min="";
            if(Double.parseDouble(tmp[0])!=0){
                base=Double.parseDouble(tmp[0]);
            }else{
                int inc=1;
                while(Double.parseDouble(tmp[inc])==0){
                     inc++;
                }    
            }
        }
        return result;
    }

    /*
      Metodo que obtiene las 5 propiedades mas caras
      @return String [] un arreglo con las propiedades que cumplen la caracteristica
    */
    public String[] topMaximo(){
        String tmp []= valor_venta;
        double base=Double.parseDouble(valor_venta[0]);
        String max="";
        int index=0;
        String result []= new String[5];
        int contador=0;
        System.out.println("Generando Resultados mas caros: ");
        while(contador<5){
          for (int i=0; i<tmp.length; i++){
              if(Double.parseDouble(tmp[i])>base){
                  base=Double.parseDouble(tmp[i]);
                  max="Nombre: "+nombres[i]+" | Valor Venta: "+valor_venta[i];        
                  index=i;
              }
          }
        result [contador]=max;
        contador++;
        tmp[index]="0";
        index=0;
        max="";
            if(Double.parseDouble(tmp[0])!=0){
                base=Double.parseDouble(tmp[0]);
            }else{
                int inc=1;
                while(Double.parseDouble(tmp[inc])==0){
                     inc++;
                }    
            }
        }
        return result;
    }

    /*
      Metodo que obtiene las 5 propiedades con mayor margen de ganancia
      @return String [] un arreglo con las propiedades que cumplen la caracteristica
    */
    public String[] topGanancias(){
        String tmp []= margenGanancia();
        double base=Double.parseDouble(tmp[0]);
        String max="";
        int index=0;
        String result []= new String[5];
        int contador=0;
        System.out.println("Generando Resultados con mayor margen de ganancia: ");
        while(contador<5){
          for (int i=0; i<tmp.length; i++){
              if(Double.parseDouble(tmp[i])>base){
                  base=Double.parseDouble(tmp[i]);
                  max="Nombre: "+nombres[i]+" | Margen Ganancia: "+tmp[i];        
                  index=i;
              }
          }
        result [contador]=max;
        contador++;
        tmp[index]="0";
        index=0;
        max="";
            if(Double.parseDouble(tmp[0])!=0){
                base=Double.parseDouble(tmp[0]);
            }else{
                int inc=1;
                while(Double.parseDouble(tmp[inc])==0){
                     inc++;
                }    
            }
        }
        return result;
    }

    /*
      Metodo auxiliar que calcula los margenes de ganancia de las propiedades
      @return String [] un arreglo con los margenes de ganancia de las propiedades.
    */
    public String[] margenGanancia(){
       String [] result= new String [100];
       double aux;
       for (int i =0; i<valor_prop.length; i++){
            aux = (Double.parseDouble(valor_venta[i]) - Double.parseDouble(valor_prop[i]));
            result [i]=aux+"";
       }
       return result;     
    }

    /*
      Metodo que busca las propiedades mas valiosas
      @return String [] un arreglo con las propiedades que cumplen la condicion.     
    */

    public String[] topValiosas(){
        String tmp []= valor_prop;
        double base=Double.parseDouble(valor_prop[0]);
        String max="";
        int index=0;
        String result []= new String[5];
        int contador=0;
        System.out.println("Generando Inmuebles mas valiosos: ");
        while(contador<5){
          for (int i=0; i<tmp.length; i++){
              if(Double.parseDouble(tmp[i])>base){
                  base=Double.parseDouble(tmp[i]);
                  max="Nombre: "+nombres[i]+" | Valor Propiedad: "+valor_prop[i];        
                  index=i;
              }
          }
        result [contador]=max;
        contador++;
        tmp[index]="0";
        index=0;
        max="";
            if(Double.parseDouble(tmp[0])!=0){
                base=Double.parseDouble(tmp[0]);
            }else{
                int inc=1;
                while(Double.parseDouble(tmp[inc])==0){
                     inc++;
                }    
            }
        }
        return result;
        
    } 

    /*
      Metodo que calcula las 10 propiedades con mas metros cuadrados
      @return String[] un arreglo con las propiedades que cumplen la condicion.
    */
    public String[] topMetros(){
        String tmp []= m2;
        double base=Double.parseDouble(m2[0]);
        String max="";
        int index=0;
        String result []= new String[10];
        int contador=0;
        System.out.println("Generando Inmuebles mas valiosos: ");
        while(contador<10){
          for (int i=0; i<tmp.length; i++){
              if(Double.parseDouble(tmp[i])>base){
                  base=Double.parseDouble(tmp[i]);
                  max="Nombre: "+nombres[i]+" | Metros: "+m2[i];        
                  index=i;
              }
          }
        result [contador]=max;
        contador++;
        tmp[index]="0";
        index=0;
        max="";
            if(Double.parseDouble(tmp[0])!=0){
                base=Double.parseDouble(tmp[0]);
            }else{
                int inc=1;
                while(Double.parseDouble(tmp[inc])==0){
                     inc++;
                }    
            }
        }
        return result;
        
    }     
}

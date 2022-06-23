import java.util.Scanner;

public class simpletron37 {
        private int acumulador;	// registro de la instuccion ejecutada 
        private int [] memoria = new int [100];	// espacio de memoria para los programas escritos en LMS
        private int registroDeInstruccion;	// registro temporal de las instrucciones
        private int contadorDeInstrucciones = 0;	
        private int codigoDeOperacion;	// la instruccion, Operacion a ejecutar
        private int operando; //tomara los primeros numeros de la palabra digitada
        
        public simpletron37 ( ) //constructor de simpletron
        {
            ImprimeBienvenida ();  }	
            // Imprime la bienvenida a simpletron
        public void ImprimeBienvenida ( )  {
            System.out.println("\n\n*** Bienvenido a Simpletron ***");
            System.out.println("*** Por favor, introduzca en su programa una instruccion ***");
            System.out.println("*** (o palabra de datos) a la vez. Yo le mostrare ***");
            System.out.println("*** el numero de la ubicacion y un signo de interrogracion(?) ***");
            System.out.println("*** Entonces usted escribira la palabra para esa ubicacion ***");
            System.out.println("*** Teclee -9999 para dejar de introducir su programa.***\n\n");
          }	
          // Lee las instrucciones digitadas por el usuario, para al digitar -9999
        public void Simulador () {
    
            Scanner sc = new Scanner ( System.in );
            int instruccionDigitada = 0;	
            int apuntadorDeMemoria = 0;	
        
            do{ 
                System.out.printf ("%d %s  ", apuntadorDeMemoria, "?" );
                instruccionDigitada = sc.nextInt();
                if ( instruccionDigitada != -9999 ) 
                    memoria [ apuntadorDeMemoria ] = instruccionDigitada;
                apuntadorDeMemoria++;
              
            } while ( instruccionDigitada != -9999 );	

            
                System.out.println("\n\n*** Se completo la carga del programa.***");
                System.out.println("*** Empieza la ejecucion del programa. ***\n\n ");	
        
            for ( int code : memoria ){
                
                if ( code != 0 ){
                    cargar ();
                    ejecutar ( operando, codigoDeOperacion ); } }
    
        sc.close();
        }	
        
	
    
        public void cargar( ) {  // cargar la instruccion en el registro y ejecutarla
            codigoDeOperacion = memoria [ contadorDeInstrucciones ] / 100;
            operando = memoria [ contadorDeInstrucciones ] % 100;
        }	
    

        public void ejecutar (int operandos, int operation ) 
        {
            
            switch ( operation ) 
            {
                case 10: // Lee la palabra introuducida en un espacio de memoria
                    Scanner sc = new Scanner ( System.in );
                    System.out.print ( "Escriba un entero (positivo o negativo): " );
                    memoria [ operandos ] = sc.nextInt ();	// guarda el resultado en el espacio de especificado
                    break;
                case 11:	// escribe una ubicacion especifica de memoria
                    System.out.println ("El resultado de esta codigoDeOperacion es "+ memoria [ operandos]);
                    break;
                case 20: // carga una palabra en una ubicacion especifica de memoria y la coloca en el acumulador
                    acumulador = memoria [ operandos ];
                    break;
                case 21: // almacena la palabra del acumulador 
                    memoria [ operandos ] = acumulador;
                    break;
                case 30: 
                //  Suma una palabra de una ubicación específi ca de memoria a la
                //  palabra en el acumulador (deja el resultado en el acumulador)
                    acumulador += memoria [ operandos ];
                    break;
                case 31: 
                //  Resta una palabra de una ubicación específi ca de memoria a la
                //  palabra en el acumulador (deja el resultado en el acumulador)
                    acumulador -= memoria [ operandos ];
                    break;
                case 32:	
                //  Divide una palabra de una ubicación específica de memoria entre la
                //  palabra en el acumulador (deja el resultado en el acumulador).
                 if (memoria[operandos] == 0){
                     System.out.println("\n\n***La ejecucion de Simpletron se termino en forma anormal***");
                    System.exit(0);}
                    else acumulador /=  memoria [ operandos ];  
                    break;
                case 33: 
                //  Multiplica una palabra de una ubicación específi ca de memoria por 
                //  la palabra en el acumulador (deja el resultado en el acumulador).
                    acumulador *= memoria [ operandos ];
                    break;
                case 40:	
                //  Bifurca hacia una ubicación específi ca de memoria
                    contadorDeInstrucciones = operandos;
                    break;
                case 41:	
                // Bifurca hacia una ubicación específi ca de memoria si el acumuladores negativo.
                    if ( acumulador < 0 )
                        contadorDeInstrucciones = operandos;
                    break;
                case 42:	
                // Bifurca hacia una ubicación específi ca de memoria si el acumulador es cero
                    if ( acumulador == 0 )
                        contadorDeInstrucciones = operandos;
                    break;
                case 43: 	
                // Alto. El programa completó su tarea
                    VaciadoInicial ();	
                    System.out.println ( "\n*** Termino la ejecucion de Simpletron. ***");
                    System.exit ( 0 );
                    break;
            }	
            contadorDeInstrucciones++;	// incrementa, y se mueve a la siguiente instruccion
        }	// fin ejecutar
    
        // Imprime el registro guardado en la memoria
        public void VaciadoInicial( ){
            System.out.println("\nREGISTROS:"); 
            System.out.printf("Acumulador: +%d\n", acumulador); 
            System.out.printf( "contadorDeInstrucciones: %d\n", contadorDeInstrucciones);
            System.out.printf( "registroDeInstrucciones: %d\n",registroDeInstruccion);
            System.out.printf("codigoDeOperacion: %d\n", codigoDeOperacion); 
            System.out.printf("Operando: %d\n", operando);
            System.out.println( "MEMORIA:" );

            for ( int i = 0; i < 10; i++ ) {System.out.printf ( "%6d", i); }
    
            System.out.println ();
            int contador = 0;	// contador para pasar por todo el ciclo
    
            // imprime las celdas verticales y horizontales
            for (int i = 0; i < 10; i++ ){
                if ( contador %10 == 0 )
                    System.out.printf ("%2d ", contador);
                for (int j = 0; j < 10; j++){	
                    if ( memoria [ contador ] == 0 )
                        System.out.printf ( "%s%s", "+", "0000 ");
                    else 
                        System.out.printf ("%s%4d ", "+", memoria [contador]);
                    contador++; }	
            System.out.println ();	 }	
        }//fin VaciadoInicial

}

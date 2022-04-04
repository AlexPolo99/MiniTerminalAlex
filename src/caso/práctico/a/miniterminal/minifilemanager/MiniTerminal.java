package caso.práctico.a.miniterminal.minifilemanager;

import java.util.Scanner;

public class MiniTerminal {

    public static void main(String[] args){
        String teclado;
        boolean info;
        Scanner sc = new Scanner(System.in);

        MiniFileManager user = new MiniFileManager(); //Creamos un objeto MiniFileManager con el que vamos a interactuar

        System.out.println("**************************************************************************TERMINAL LINUX*********************************************************************************************");
        do {  
            System.out.print(">");
            teclado = sc.nextLine();
            if (teclado.equals("pwd")) {
                System.out.println(user.getPWD());  //Llamamos al metodo con el objeto, si no habria que poner los metodos de la otra clase en static para poder usarlos.
            } else if (teclado.equals("ls")) {
                info = false;
                user.printList(info);
            } else if (teclado.equals("ll")) {
                info = true;
                user.printList(info);
            } else if (teclado.equals("help")) {   //Llamamos al metodo help para que nos muestre todos los comandos.
                user.help();
            } else if (teclado.contains(" ")) { //usamos contains para saber si teclado contiene espacios
                String[] splitter = teclado.split(" "); //Creamos el splitter para separar las palabras por espacios cuando teclado contenga estos espacios.
                if (splitter[0].equals("cd") && splitter[1].equals("..")) { //Si la posicion 0 del array splitter es igual a cd y la posicion 1 es .. se llamara al metodo.
                    user.cdPuntos();
                } else if (teclado.equals("cd " + splitter[1])) {
                    user.changeDir(splitter[1]);
                } else if (splitter[0].equals("mkdir") && splitter[1].equals("" + splitter[1])) {
                    user.mkdir(splitter[1]);
                } else if (splitter[0].equals("rm") && splitter[1].equals("" + splitter[1])) {
                    user.remove(splitter[1]);
                } else if (teclado.equals("mv " + splitter[1] + " " + splitter[2])) {
                    user.move(splitter[1], splitter[2]);
                }
            } else if (!teclado.equals("exit")) {
                System.out.println(teclado + ": comando mal introducido. Use 'help' si necesita ayuda.");
            }
        } while (!teclado.equals("exit"));
        System.out.println("**************************************************************************PROGRAMA FINALIZADO****************************************************************************************");
    }
}

/*
Implementa un programa que funcione como una pequeña terminal Linux con algunos comandos
(simplificados) que permitan al usuario realizar distintas operaciones de gestión de archivos. Los
comandos que el usuario podrá ejecutar son:
● pwd: Muestra cual es la carpeta actual.
● cd <DIR>: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.
● ls: Muestra la lista de directorios y archivos de la carpeta actual (primero directorios, luego
archivos, ambos ordenados alfabéticamente).
● ll: Como ls pero muestra también el tamaño y la fecha de última modificación.
● mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.
● rm <FILE>: Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la carpeta. Si
tiene subcarpetas, las dejará intactas y mostrará un aviso al usuario.
● mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’.
● help: Muestra una breve ayuda con los comandos disponibles.
● exit: Termina el programa.
Clase MiniTerminal: Clase principal (con función main) que se encargará de interactuar con el
usuario e interpretar los comandos (qué comando se pide, argumentos, etc.). Utilizará la segunda
clase para realizar las operaciones de gestión de archivos. Manejará todas las posibles excepciones.
Clase MiniFileManager: Tendrá los atributos y métodos que necesites para realizar las distintas
operaciones relacionadas con la gestión de archivos. Necesitarás al menos un método por cada
operación. Se lanzará una excepción si se produce un error o la operación solicitada no es posible.
Algunos ejemplos que podrías implementar:
● String getPWD(): Devuelve una cadena de texto con la carpeta actual.
● boolean changeDir(String dir): Cambia la carpeta actual a dir. Devuelve ‘true’ si fué posible.
● void printList(boolean info): Muestra una lista con los directorios y archivos de la carpeta
actual. Si info es ‘true’ mostrará también su tamaño y fecha de modificación.
● etc.
 */




 /*             NO HAGAS CASO A ESTO.
nombre = teclado.substring(teclado.indexOf(" "),teclado.length());    // Obtengo el nombre que se escribe despues del comando y seguido del espacio.
 */
 /*String[] splitterCd = user.getPWD().split("\\");    //Con getParent se consigue la carpeta padre entonces habria que poner la ruta actual hasta getParent.
                    rutaCd=user.getPWD().substring(user.getPWD().indexOf("\\"),splitterCd.length);  //Elimino la ultima \carpeta.

public void info(String nombre) throws Exception {      //COMANDO INFO
        long total = 0;
        try {
            File file = new File("" + nombre);    //Creamos a un file el nombre del archivo para ver donde se encuentra.
            if (file.isFile()) {    //Si es un archivo muestra la informacion.
                System.out.println("*********************************");
                System.out.println("[A] " + file.getName() + " | Tamaño: " + file.length() + "Bytes / " + file.length() / 1e+6 + " Megabytes | Ultima modificacion: " + new SimpleDateFormat().format(file.lastModified()));
                System.out.println("*****************************");
                System.out.println("");
            } else {             //Si es un directorio muestra el tamaño de la carpeta padre mas todo lo que contiene.
                File[] lista = file.listFiles();
                for (int i = 0; i < lista.length; i++) {    //Recorremos la carpeta y vamos acumulando el tamaño.
                    File f = lista[i];
                    total = total + f.length();
                }
                total = file.length() + total;  //Sumamos a los bytes de la carpeta padre los bytes de los directorios y archivos que contiene.
                System.out.println("[*] " + file.getName() + " | Tamaño: " + total + "Bytes/ " + total / 1e+6 + " Megabytes | Ultima modificacion: " + new SimpleDateFormat().format(file.lastModified()));
                System.out.println("");
            }
        } catch (Exception e) {    //Excepcion que va a indicar el problema que hay si no se encuentra el directorio o archivo introducido.
            System.out.println("No existe el archivo o directorio que ha introducido.");
        }
    }

 */

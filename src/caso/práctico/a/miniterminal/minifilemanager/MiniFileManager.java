package caso.práctico.a.miniterminal.minifilemanager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class MiniFileManager {

    final private File home = new File("");   //Creamos un File llamado hombre con una ruta vacia para guardar en rutaActual la ruta en la que nos encontramos.
    private File rutaActual = new File(home.getAbsoluteFile().toString());

    public void help() {         //Metodo help que muestra la informacion del terminal.
        System.out.println("● pwd: Muestra cual es la carpeta actual.\n"
                + "● cd <DIR>: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.\n"
                + "● ls: Muestra la lista de directorios y archivos de la carpeta actual (primero directorios, luego\n"
                + "   archivos, ambos ordenados alfabéticamente).\n"
                + "● ll: Como ls pero muestra también el tamaño y la fecha de última modificación.\n"
                + "● mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.\n"
                + "● rm <FILE>: Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la carpeta. Si\n"
                + "   wtiene subcarpetas, las dejará intactas y mostrará un aviso al usuario.\n"
                + "● mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’.\n"
                + "● exit: Termina el programa.");
    }

    public String getPWD() {    //Mostramos la ruta actual
        return "" + rutaActual;
    }
                //En algunos metodos no muestro un mensaje porque podemos usar el comando pwd o ver en la carpeta si se han dado los cambios.
    public void changeDir(String dir){  //Cambia el directorio poniendo al final de la ruta la carpeta a la que quiere acceder el usuario.
            File file = new File("" + dir);
            if(file.exists()){
                rutaActual = file.getAbsoluteFile();  //Se actualiza la rutaActual al nombre del directorio que ha puesto el usuario.
                System.out.println("Se encuentra en: "+dir);
            }else{ 
                System.out.println("No existe el directorio al que intenta acceder"); 
            }
    } 

    public void cdPuntos() { //Volvemos al directorio anterior.
        File file = new File(rutaActual.getParent());   //Creamos un FILE con la ruta superior a la actual
        rutaActual = file;    //Igualamos la ruta actual a file para que obtenga la ruta en la carpeta superior.
    }

    public void move(String nombre1, String nombre2) {
        String ruta = rutaActual.getAbsolutePath();
        File f1 = new File(ruta, nombre1);   //Guardamos los nombres en Files
        File f2 = new File(ruta, nombre2);
        if (f1.renameTo(f2)) {  //Movemos o cambiamos de nombre los files.
            System.out.println("Carpeta movida o renombrada.");
        } else {
            System.out.println("No se ha podido mover o renombrar la carpeta.");
        }
    }

    public void printList(boolean info) {   //muestra la informacion de el archivo o carpeta.
        if (info == false) {        // si info es false significa que hemos utilizado ls.
            if (rutaActual.isFile()) {
                System.out.println("************************");
                System.out.println("[A] " + rutaActual.getName());  //Si es un archivo muestra el nombre
                System.out.println("************************");
                System.out.println("");
            } else {
                File[] lista = rutaActual.listFiles();                 //Si es un directorio muestra el nombre de todo lo que contiene.
                System.out.println("*************************************");
                for (File i : lista) {  //Usamos un for each que ira creando files de cada posicion hasta que ocupe la longitud de la lista.
                    File fD = i;
                    if (fD.isDirectory()) {
                        System.out.println("[*] " + fD.getName());
                    }
                }
                for (File i : lista) {
                    File fA = i;
                    if (fA.isFile()) {
                        System.out.println("[A] " + fA.getName());
                    }
                }
                System.out.println("*************************************");
                System.out.println("");
            }
        } else {                        // si info es true significa que hemos utilizado ll.
            if (rutaActual.isFile()) {
                System.out.println("************************");                 //Mostramos toda la informacion del archivo.
                System.out.println("[A] " + rutaActual.getName() + " | Tamaño: " + rutaActual.length() + "bytes | Ultima modificacion: " + new SimpleDateFormat().format(rutaActual.lastModified()));
                System.out.println("************************");
                System.out.println("");
            } else {
                File[] lista = rutaActual.listFiles();
                Arrays.sort(lista);
                System.out.println("*************************************");
                for (File i : lista) {
                    File f =i;
                    if (f.isDirectory()) {
                        System.out.println("[*] " + f.getName() + " | Tamaño: " + f.length() + "bytes | Ultima modificacion: " + new SimpleDateFormat().format(f.lastModified()));
                    }
                }

                for (File i : lista) {  //Usamos un for each
                    File f =i;
                    if (f.isFile()) {
                        System.out.println("[A] " + f.getName() + " | Tamaño: " + f.length() + "bytes | Ultima modificacion: " + new SimpleDateFormat().format(f.lastModified()));
                    }
                }
                System.out.println("*************************************");
                System.out.println("");
            }
        }
    }

    public void remove(String nombre) {
        boolean aviso = false;

        File file = new File("" + nombre);    //Guardamos en un File el archivo que queremos eliminar.
        if (file.isFile()) {
            file.getAbsoluteFile().delete();    //Eliminamos el archivo.
            System.out.println("Archivo " + file + " eliminado.");  //Mostramos que el archivo ha sido eliminado.
        } else {
            File[] lista = file.listFiles();    //Si es una carpeta borramos todos los archivos y luego la carpeta.
            for (File i : lista) {
                File fD = i;
                if (fD.isDirectory() && fD != file) {   //Si esta carpeta contiene subcarpetas ponemos el aviso en true.
                    aviso = true;
                }
            }
            if (aviso == true) {        //Si el aviso es true va a mostrar un aviso de que solo eliminara los archivos pero no los directorios.
                System.out.println("Esta carpeta contiene subcarpetas. Los archivos y los directorios permaneceran intactos. "
                        + "Si quiere eliminarlos use rm en la subcarpeta.");
            } else {
                for (File i : lista) {
                    File f = i;
                    if (f.isFile()) {
                        f.delete();
                    }
                }
                file.delete();  //Eliminamos el directoro si no tiene subcarpetas.
                System.out.println("Carpeta " + file.getName() + " Eliminada.");
            }
        }
    }

    public void mkdir(String nombre) {  
        File f = new File(rutaActual + "\\" + nombre);  // Creamos un File con la ruta de la carpeta actual + el nombre de la nueva carpeta creada.
        f.mkdir();  //Con el metodo mkdir creamos la carpeta con la ruta introducida en f.
        System.out.println("Directorio " + nombre + " creado");
    }
}

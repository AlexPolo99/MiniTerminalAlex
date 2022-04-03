package caso.práctico.a.miniterminal.minifilemanager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class MiniFileManager {

    final private File home = new File("");
    private File rutaActual = new File(home.getAbsoluteFile().toString());

    public File getRutaActual() {
        return rutaActual;
    }

    public void help() {         //Metodo help que muestra la informacion del terminal.
        System.out.println("● pwd: Muestra cual es la carpeta actual.\n"
                + "● cd <DIR>: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.\n"
                + "● ls: Muestra la lista de directorios y archivos de la carpeta actual (primero directorios, luego\n"
                + "   archivos, ambos ordenados alfabéticamente).\n"
                + "● ll: Como ls pero muestra también el tamaño y la fecha de última modificación.\n"
                + "● mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.\n"
                + "● rm <FILE>: Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la carpeta. Si\n"
                + "   tiene subcarpetas, las dejará intactas y mostrará un aviso al usuario.\n"
                + "● mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’.\n"
                + "● exit: Termina el programa.");
    }

    public String getPWD() {    //Mostramos la ruta actual
        return "" + rutaActual;
    }
    //En este metodo no muestro la ruta para que el usuario tenga que usar pwd.

    public boolean changeDir(String dir) {  //Cambia el directorio poniendo al final de la ruta la carpeta a la que quiere acceder el usuario.
        File file = new File("" + dir);
        rutaActual = file.getAbsoluteFile();  //Se actualiza la rutaActual al nombre del directorio que ha puesto el usuario.
        return true;
    }

    public void cdPuntos() { //Volvemos al directorio anterior.
        File file = new File(rutaActual.getParent());   //Creamos un FILE con la ruta superior a la actual
        rutaActual = file;    //Igualamos la ruta actual a file para que obtenga la ruta en la carpeta superior.
    }

    public void move(String nombre1, String nombre2) {
        File f1 = new File("" + nombre1);
        File f2 = new File("" + nombre2);
        if (f1.renameTo(f2)) {
            System.out.println(f1);
            System.out.println("Se ha movido la carpeta.");
        } else {
            System.out.println("No se ha podido mover o renombrar la carpeta.");
        }
    }

    public void printList(boolean info) {
        if (info == false) {
            if (rutaActual.isFile()) {
                System.out.println("************************");
                System.out.println("[A] " + rutaActual.getName());
                System.out.println("************************");
                System.out.println("");
            } else {
                File[] lista = rutaActual.listFiles();
                System.out.println("*************************************");
                for (int i = 0; i < lista.length; i++) {
                    File f = lista[i];
                    if (f.isDirectory()) {
                        System.out.println("[*] " + f.getName());
                    }
                }
                for (int i = 0; i < lista.length; i++) {
                    File f = lista[i];
                    if (f.isFile()) {
                        System.out.println("[A] " + f.getName());
                    }
                }
                System.out.println("*************************************");
                System.out.println("");
            }
        } else {
            File[] lista = rutaActual.listFiles();
            Arrays.sort(lista);
            System.out.println("*************************************");
            for (int i = 0; i < lista.length; i++) {
                File f = lista[i];
                if (f.isDirectory()) {
                    System.out.println("[*] " + f.getName() + " | Tamaño: " + f.length() + "bytes | Ultima modificacion: " + new SimpleDateFormat().format(f.lastModified()));
                }
            }

            for (int i = 0; i < lista.length; i++) {
                File f = lista[i];
                if (f.isFile()) {
                    System.out.println("[A] " + f.getName() + " | Tamaño: " + f.length() + "bytes | Ultima modificacion: " + new SimpleDateFormat().format(f.lastModified()));
                }
            }
            System.out.println("*************************************");
            System.out.println("");
        }
    }

    public void remove(String nombre) {
        boolean aviso = false;

        File file = new File("" + nombre);    //Guardamos en un File el archivo que queremos eliminar.
        if (file.isFile()) {
            file.getAbsoluteFile().delete();    //Eliminamos el archivo.
            System.out.println("Archivo " + file + " eliminado.");
        } else {
            File[] lista = file.listFiles();    //C
            for (int i = 0; i < lista.length; i++) {
                File fD = lista[i];
                if (fD.isDirectory() && fD != file) {
                    aviso = true;
                }
            }
            if (aviso == true) {
                System.out.println("Esta carpeta contiene subcarpetas. Los archivos y los directorios permaneceran intactos. "
                        + "Si quiere eliminarlos use rm en la subcarpeta.");
            } else {
                for (int i = 0; i < lista.length; i++) {
                    File f = lista[i];
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
        File f = new File(rutaActual + "\\" + nombre);
        f.mkdir();
        System.out.println("Directorio " + nombre + " creado");
    }
}

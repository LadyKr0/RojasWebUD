// Lady Katherine Rojas Mendivelso
package asp.modelo;

import java.util.ArrayList;

public class AspiranteDAO {

    public static ArrayList<Aspirante> lista_A = new ArrayList<>();

    public static void agregar(Aspirante aspirante) {
        lista_A.add(aspirante);
    }

    public static ArrayList<Aspirante> listar() {
        return lista_A;
    }
}

package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.excepciones.*;
import uniandes.dpoo.hamburguesas.mundo.*;

public class RestauranteTest {
    private Restaurante restaurante;
    private final File archivoIngredientes = new File("ingredientes_test.txt");
    private final File archivoMenu = new File("menu_test.txt");
    private final File archivoCombos = new File("combos_test.txt");

    @BeforeEach
    void setUp() throws Exception {
        restaurante = new Restaurante();
        crearArchivosPrueba();
    }

    @AfterEach
    void tearDown() throws Exception {
        borrarArchivosPrueba();
    }

    @Test
    void testIniciarPedido() throws Exception {
        restaurante.iniciarPedido("Cliente1", "Dir1");
        assertNotNull(restaurante.getPedidoEnCurso());
        assertEquals("Cliente1", restaurante.getPedidoEnCurso().getNombreCliente());
    }

    @Test
    void testCerrarPedido() throws Exception {
        restaurante.iniciarPedido("Cliente1", "Dir1");
        restaurante.cerrarYGuardarPedido();
        assertNull(restaurante.getPedidoEnCurso());
        assertEquals(1, restaurante.getPedidos().size());
    }

    @Test
    void testExcepcionPedidoDuplicado() {
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("A", "1");
            restaurante.iniciarPedido("B", "2");
        });
    }

    @Test
    void testExcepcionCerrarSinPedido() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> restaurante.cerrarYGuardarPedido());
    }

    @Test
    void testCargarDatosValidos() throws Exception {
        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        assertEquals(2, restaurante.getIngredientes().size());
        assertEquals(3, restaurante.getMenuBase().size());
        assertEquals(1, restaurante.getMenuCombos().size());
    }

    @Test
    void testCargarIngredienteRepetido() {
        assertThrows(IngredienteRepetidoException.class, () -> {
            Files.writeString(archivoIngredientes.toPath(), "Lechuga;2000\nLechuga;1500");
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        });
    }

    @Test
    void testCargarComboRepetido() {
        assertThrows(ProductoRepetidoException.class, () -> {
            Files.writeString(archivoCombos.toPath(), 
                "Combo1;10%;Hamburguesa;Papas;Gaseosa\n" +
                "Combo1;15%;Hamburguesa;Gaseosa");
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        });
    }

    @Test
    void testCargarComboConProductoFaltante() {
        assertThrows(ProductoFaltanteException.class, () -> {
            Files.writeString(archivoCombos.toPath(), "Combo2;20%;Hamburguesa;ProductoInexistente");
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        });
    }

    @Test
    void testGuardarFactura() throws Exception {
        restaurante.iniciarPedido("Test", "Calle");
        Pedido pedido = restaurante.getPedidoEnCurso();
        pedido.agregarProducto(new ProductoMenu("Prueba", 10000));
        restaurante.cerrarYGuardarPedido();

        File factura = new File("./facturas/factura_0.txt");
        assertTrue(factura.exists());
        
        String contenido = Files.readString(factura.toPath());
        assertTrue(contenido.contains("Prueba"));
        assertTrue(contenido.contains("10000"));
    }

    @Test
    void testGetPedidos() throws Exception {
        restaurante.iniciarPedido("Cliente1", "Dir1");
        Pedido pedido = restaurante.getPedidoEnCurso();
        pedido.agregarProducto(new ProductoMenu("Hamburguesa", 25000));
        restaurante.cerrarYGuardarPedido();

        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertEquals(1, pedidos.size());
        assertEquals(2, pedidos.get(0).getIdPedido());
        assertEquals("Cliente1", pedidos.get(0).getNombreCliente());
    }

    private void crearArchivosPrueba() throws IOException {
        Files.writeString(archivoIngredientes.toPath(), "Lechuga;2000\nTomate;1500");
        Files.writeString(archivoMenu.toPath(), "Hamburguesa;25000\nPapas;8000\nGaseosa;4000");
        Files.writeString(archivoCombos.toPath(), "Combo1;10%;Hamburguesa;Papas;Gaseosa");
    }

    private void borrarArchivosPrueba() throws IOException {
        Files.deleteIfExists(archivoIngredientes.toPath());
        Files.deleteIfExists(archivoMenu.toPath());
        Files.deleteIfExists(archivoCombos.toPath());
        Files.deleteIfExists(Path.of("./facturas/factura_0.txt"));
    }
}
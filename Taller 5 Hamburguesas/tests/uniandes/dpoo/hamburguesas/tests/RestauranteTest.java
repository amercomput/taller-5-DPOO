package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;


public class RestauranteTest {
	private Restaurante restaurante; 
	private Pedido pedido1;
	private Pedido pedido2;	
	private Producto producto1;
	private Producto producto2;
	private Producto producto3;
    private ProductoAjustado producto4;
    private ProductoMenu producto5;
    private ProductoMenu producto6;
    private ProductoMenu producto7;
    private String nombreCliente = "Alberto";
    private String direccionCliente = "calle 24 #16a-23";
    private ProductoMenu hamburguesa;
    private Ingrediente ingrediente1;
    private Ingrediente ingrediente2;
    private Ingrediente ingrediente3;
    private Combo combo;
    private ArrayList<ProductoMenu> listaProductos2 = new ArrayList<>();
    private Pedido pedido3;
    @BeforeEach
    void setUp() throws Exception {
        producto1 = new ProductoMenu("perro caliente", 15000);    
        producto2 = new ProductoMenu("salchipapa doble", 30000);
        producto3 = new ProductoMenu("hamburguesa doble carne", 35000);
        pedido1 = new Pedido(nombreCliente, direccionCliente);
        pedido1.agregarProducto(producto1);
        pedido1.agregarProducto(producto2);
        
        hamburguesa = new ProductoMenu("Hamburguesa Mexicana", 25000);
        producto4 = new ProductoAjustado(hamburguesa);
        ingrediente1 = new Ingrediente("queso", 5000);
        ingrediente2 = new Ingrediente("cerdo", 5000);
        ingrediente3 = new Ingrediente("chorizo",5000);
        producto4.setIngredienteAdicional(ingrediente1);
        producto4.setIngredienteAdicional(ingrediente2);
        producto4.setIngredienteAdicional(ingrediente3);
        
        producto5 = new ProductoMenu("Hamburguesa criolla", 20000);
        producto6 = new ProductoMenu("Vaso de gaseosa", 4000);
        producto7 = new ProductoMenu("Palitos de queso", 8000);
        listaProductos2.add(producto5);
        listaProductos2.add(producto6);
        listaProductos2.add(producto7);
        combo = new Combo("Combo del dia", 0.0, listaProductos2);
        pedido2 = new Pedido("Juliana", "Calle 167, #34-22");
        pedido2.agregarProducto(producto4);
        pedido2.agregarProducto(combo);
    }

}

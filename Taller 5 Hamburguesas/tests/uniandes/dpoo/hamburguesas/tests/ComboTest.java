package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	private Combo combo1;
	private Combo combo2;
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	private ProductoMenu producto3;
	private ProductoMenu producto4;
	private ArrayList<ProductoMenu> listaProductos;
	private ArrayList<ProductoMenu> listaProductos2;
	@BeforeEach
    void setUp( ) throws Exception
    {listaProductos = new ArrayList<ProductoMenu>();
    listaProductos2 = new ArrayList<ProductoMenu>();
	producto1 = new ProductoMenu("Papas fritas", 8000);
    producto2 = new ProductoMenu("Hamburguesa criolla", 20000);
    producto3 = new ProductoMenu("Vaso de gaseosa", 4000);
    producto4 = new ProductoMenu("Palitos de queso", 8000);
    listaProductos.add(producto1);
    listaProductos.add(producto2);
    listaProductos.add(producto3);
    listaProductos.add(producto4);
    listaProductos2.add(producto1);
    listaProductos2.add(producto2);
    listaProductos2.add(producto3);
    combo1 = new Combo("Combo especial", 0.05, listaProductos);
    combo2 = new Combo("Combo del dia", 0.0, listaProductos2);
    }
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetNombre( )
    {
        assertEquals( "Combo especial", combo1.getNombre( ), "El nombre del combo no es el esperado." );
        assertEquals( "Combo del dia", combo2.getNombre( ), "El nombre del combo no es el esperado." );
    
    }
    
    @Test 
    void testGetPrecio() {
    	assertEquals(38000, combo1.getPrecio(), "El precio no es el esperado para el combo seleccionado");
    	assertEquals(32000, combo2.getPrecio(), "El precio no es el esperado para el combo seleccionado");
    }
    
    @Test 
    void testGenerarTextoFactura() {
        String expected = "Combo Combo especial\n Descuento: 0.05\n            38000\n";
        assertEquals(expected, combo1.generarTextoFactura(), "El formato de la factura del combo es incorrecto.");
        
        String expected2 = "Combo Combo del dia\n Descuento: 0.0\n            32000\n";
        assertEquals(expected2, combo2.generarTextoFactura(), "El formato de la factura del combo es incorrecto.");
    }
    
    
    
    
	
	
	
	
	
	
	
	
	
	
	
	
	
}

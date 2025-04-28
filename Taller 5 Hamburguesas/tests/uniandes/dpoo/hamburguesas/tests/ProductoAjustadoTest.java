package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	private ProductoMenu hamburguesa;
	private ProductoAjustado producto1;
	private Ingrediente ingrediente1;
	private Ingrediente ingrediente2;
	private Ingrediente ingrediente3;
    @BeforeEach
    void setUp( ) throws Exception
    {   hamburguesa = new ProductoMenu("Hamburguesa Mexicana", 25000);
        producto1 = new ProductoAjustado(hamburguesa);
        ingrediente1 = new Ingrediente("queso", 5000);
        ingrediente2 = new Ingrediente("cerdo", 5000);
        ingrediente3 = new Ingrediente("chorizo",5000);
    	producto1.setIngredienteAdicional(ingrediente1);
    	producto1.setIngredienteAdicional(ingrediente2);
    	producto1.setIngredienteAdicional(ingrediente3);
        
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetNombre( )
    {
        assertEquals( "Hamburguesa Mexicana", producto1.getNombre( ), "El nombre del producto no es el esperado." );
    } 
    
    @Test   
    void testSetIngredienteAdicional() {
    	assertEquals(producto1.getIngredientesAgregados().get(0),ingrediente1,"El ingrediente agregado no es el esperado");
    	assertEquals(producto1.getIngredientesAgregados().get(1),ingrediente2,"El ingrediente agregado no es el esperado");
    	assertEquals(producto1.getIngredientesAgregados().get(2),ingrediente3,"El ingrediente agregado no es el esperado");
    }
    

    
    @Test
    void testGetPrecio() {
    	assertEquals(producto1.getPrecio(),40000," El precio del producto no es el esperado");
    	
    }
    
    @Test
    void testGenerarTextoFactura() {
        producto1.removeIngrediente(ingrediente1);
        String nombreBase = producto1.getNombre();
        
        String expected = nombreBase
            + "    +cerdo                5000"
            + "    +chorizo                5000" 
            + "    -queso"
            + "            35000\n";
        
        assertEquals(expected, producto1.generarTextoFactura(), "El formato es incorrecto.");
    }
    
    @Test 
    void testRemoveIngrediente() { 	
    	producto1.removeIngrediente(ingrediente1);
    	assertEquals(ingrediente1,producto1.getIngredientesEliminados().get(0), "El ingrediente no fue eliminado de manera correcta ");
    }
    
	

}

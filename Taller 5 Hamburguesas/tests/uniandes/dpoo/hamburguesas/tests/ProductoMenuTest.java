package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	 private ProductoMenu producto1;

	    @BeforeEach
	    void setUp( ) throws Exception
	    {
	        producto1 = new ProductoMenu( "Hamburguesa Mexicana", 25000 );
	    }

	    @AfterEach
	    void tearDown( ) throws Exception
	    {
	    }
	    
	    @Test
	    void testGetNombre( )
	    {
	        assertEquals( "Hamburguesa Mexicana", producto1.getNombre( ), "El nombre del ingrediente no es el esperado." );
	    }

	    @Test
	    void testGetCostoAdicional( )
	    {
	        assertEquals( 25000, producto1.getPrecio( ), "El costo adicional del ingrediente no es el esperado." );
	    }
	    
	    @Test
	    void testGenerarTextoFactura() {
	        String expected = "Hamburguesa Mexicana\n            25000\n";
	        assertEquals(expected, producto1.generarTextoFactura(), "El texto de la factura no coincide con el formato esperado.");
	    }
	    
	    
	    
	    

	}


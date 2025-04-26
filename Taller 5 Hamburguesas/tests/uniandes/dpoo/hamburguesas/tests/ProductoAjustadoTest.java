package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	private ProductoMenu hamburguesa;
	private ProductoAjustado producto1;
    @BeforeEach
    void setUp( ) throws Exception
    {   hamburguesa = new ProductoMenu("Hamburguesa Mexicana", 25000);
        producto1 = new ProductoAjustado(hamburguesa);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
	

}

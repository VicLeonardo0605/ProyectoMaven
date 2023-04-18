package app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Categoria;
import model.Producto;
import model.Proveedor;

public class mainProducto {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("JPA_sesion01");
		EntityManager em = fabrica.createEntityManager();

		List<Producto> lstProductos = em.createQuery("Select a From Producto a", Producto.class).getResultList();
		System.out.println("==============================================");
		
		/*Mostrar cantidad de productos*/
		System.out.println("nro de Productos:" +lstProductos.size());
		System.out.println("==============================================");
		
		/*Listar todos los productos*/
		System.out.println("Lista de Productos:");
		System.out.println("-----------------");
		for(Producto u: lstProductos){
			System.out.println("Producto: "+u.getDesProd()+ " " + u.getEstProd()+ " "+ u.getPreProd());
		}
		System.out.println("==============================================");
		
		/*Búsqueda de usuario con id=2*/
		System.out.println("Buscar producto con id=P0002");
		System.out.println("-----------------------");
		Producto us = em.find(Producto.class,"P0002");
		System.out.println(us);
		System.out.println("==============================================");

		/*Insertar nuevo usuario*/
		System.out.println("Insertar nuevo producto: Ponds Oliva, de Categoria = 2 (cliente) y id_prod=P0001");
		System.out.println("-------------------------------------------------------------------------");
		em.getTransaction().begin();
		Producto producto = new Producto();
		Date fecha = new Date();
		//Seleccionamos el tipo de id=2 para crear el usuario del tipo Cliente
		Categoria cat = em.find(Categoria.class, 2);
		Proveedor prov = em.find(Proveedor.class, 2);
		//Asignamos valores a los atributos
		producto.setIdProd("P0021");
		producto.setDesProd("Ponds Oliva");
		producto.setStkProd(50);
		producto.setPreProd(new BigDecimal(950));
		producto.setTbCategoria(cat);
		producto.setEstProd((byte) 1);
		producto.setTbProveedor(prov);
		em.persist(producto);
		em.getTransaction().commit();
		System.out.println("==============================================");
		
		/*Actualizar usuario*/
		System.out.println("Actualizamos producto con id=P0021: y actualizamos campo DESPROD= Ponds Carbon");
		System.out.println("-------------------------------------------------------------------");
		em.getTransaction().begin();
		Producto usuarioActualizar = em.find(Producto.class, "P0021");
		usuarioActualizar.setDesProd("Ponds_Carbon");
		em.persist(usuarioActualizar);
		em.getTransaction().commit();
		System.out.println("==============================================");

		/*Eliminar usuario*/
		System.out.println("Eliminar usuario con id=12");
		System.out.println("-------------------------");
		em.getTransaction().begin();
		Producto usuarioEliminar = em.find(Producto.class, "P0021");
		em.remove(usuarioEliminar);
		em.getTransaction().commit();
		System.out.println("==============================================");		
		fabrica.close();
		em.close();
				//SEXO 
	}
	
	

}

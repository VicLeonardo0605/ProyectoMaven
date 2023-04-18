package app;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Tipo;
import model.Usuario;

public class main {

	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("JPA_sesion01");
		EntityManager em = fabrica.createEntityManager();

		List<Usuario> lstUsuarios = em.createQuery("Select a From Usuario a", Usuario.class).getResultList();
		System.out.println("==============================================");
		
		/*Mostrar cantidad de usuarios*/
		System.out.println("nro de usuarios:" +lstUsuarios.size());
		System.out.println("==============================================");
		
		/*Listar todos los usuarios*/
		System.out.println("Lista de usuarios:");
		System.out.println("-----------------");
		for(Usuario u: lstUsuarios){
			System.out.println("usuario: "+u.getNomUsua()+ " " + u.getApeUsua()+ " "+ u.getClaUsua());
		}
		System.out.println("==============================================");
		
		/*Búsqueda de usuario con id=2*/
		System.out.println("Buscar usuario con id=2");
		System.out.println("-----------------------");
		Usuario us = em.find(Usuario.class,2);
		System.out.println(us);
		System.out.println("==============================================");

		/*Insertar nuevo usuario*/
		System.out.println("Insertar nuevo usuario: Pepe Perez, de Tipo = 2 (cliente) y cla_usua=1001");
		System.out.println("-------------------------------------------------------------------------");
		em.getTransaction().begin();
		Usuario usuario = new Usuario();
		Date fecha = new Date();
		//Seleccionamos el tipo de id=2 para crear el usuario del tipo Cliente
		Tipo tipo = em.find(Tipo.class, 2);
		//Asignamos valores a los atributos
		usuario.setCodUsua(6);
		usuario.setNomUsua("Pepe");
		usuario.setApeUsua("Perez");
		usuario.setUsrUsua("pepeperez");
		usuario.setFnaUsua(fecha);
		usuario.setTbTipo(tipo);
		usuario.setEstUsua(1);
		em.persist(usuario);
		em.getTransaction().commit();
		System.out.println("==============================================");
		
		/*Actualizar usuario*/
		System.out.println("Actualizamos usuario con id=6: y actualizamos campo cla_usua=10001");
		System.out.println("-------------------------------------------------------------------");
		em.getTransaction().begin();
		Usuario usuarioActualizar = em.find(Usuario.class, 6);
		usuarioActualizar.setClaUsua("10001");
		em.persist(usuarioActualizar);
		em.getTransaction().commit();
		System.out.println("==============================================");

		/*Eliminar usuario*/
		System.out.println("Eliminar usuario con id=6");
		System.out.println("-------------------------");
		em.getTransaction().begin();
		Usuario usuarioEliminar = em.find(Usuario.class, 6);
		em.remove(usuarioEliminar);
		em.getTransaction().commit();
		System.out.println("==============================================");		
		fabrica.close();
		em.close();
	}

}

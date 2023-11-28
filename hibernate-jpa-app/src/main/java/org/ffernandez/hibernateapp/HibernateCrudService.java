package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.services.ClienteService;
import org.ffernandez.hibernateapp.services.ClienteServiceImpl;
import org.ffernandez.hibernateapp.util.JpaUtil;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public class HibernateCrudService {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        ClienteService service = new ClienteServiceImpl(em);

        System.out.println("===== Listar =====");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);

        System.out.println("===== Por Id =====");
        Optional<Cliente> clientePorId = service.porId(1L);

        clientePorId.ifPresent(System.out::println);

        System.out.println("===== Nuevo cliente =====");
        Cliente nuevo = new Cliente();
        nuevo.setNombre("Denise");
        nuevo.setApellido("Fernandez");
        nuevo.setFormaPago("debito");
        service.guardar(nuevo);
        System.out.println("Cliente guardado con éxito");
        service.listar().forEach(System.out::println);

        System.out.println("===== Editar cliente =====");
        Long id = nuevo.getId();
        clientePorId = service.porId(id);
        clientePorId.ifPresent(c -> {
            c.setFormaPago("credito");
            service.guardar(c);
            System.out.println("Cliente editado con éxito");
            service.listar().forEach(System.out::println);
        });

        System.out.println("===== Eliminar cliente =====");
        id = nuevo.getId();
        clientePorId = service.porId(id);
        clientePorId.ifPresent(c -> {
            service.eliminar(c.getId());
            System.out.println("Cliente eliminado con éxito");
            service.listar().forEach(System.out::println);
        });

        em.close();

    }
}

package org.curso.ayi.jpa.app;

import jakarta.persistence.EntityManager;
import org.curso.ayi.jpa.app.configuration.JpaUtilDb;
import org.curso.ayi.jpa.app.entity.Cliente;
import org.curso.ayi.jpa.app.services.IClienteService;
import org.curso.ayi.jpa.app.services.Impl.ClienteServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class JpaCrud {

    public static void main(String[] args) {
        EntityManager em = JpaUtilDb.getEntityManager();

        IClienteService service = new ClienteServiceImpl(em);

/*        System.out.println("========== insertar nuevo cliente ===========");
        Cliente cliente = new Cliente();
        cliente.setNombre("Luci");
        cliente.setApellido("Mena");
        cliente.setFormaPago("paypal");
        cliente.setFechaCreacion(LocalDate.now());

        service.insert(cliente);
        System.out.println("cliente guardado con exito");
        service.getAll().forEach(System.out::println);


        System.out.println("========== listar ==========");
        List<Cliente> clientes = service.getAll();
        clientes.forEach(System.out::println);*/

/*        System.out.println("========== obtener por id ==========");
        Optional<Cliente> optionalCliente = service.getById(1L);
        optionalCliente.ifPresent(System.out::println);*/

        System.out.println("=========== editar cliente ==========");
        Cliente cliente = new Cliente();
        cliente.setId(6L);
        cliente.setNombre("Martin");
        cliente.setApellido("Guzman");
        cliente.setFormaPago("Efectivo");
        cliente.setFechaCreacion(LocalDate.now());

        service.insert(cliente);


/*        System.out.println("========== eliminar cliente ===========");
        service.eliminar(7L);*/

        /*//*if (optionalCliente.isPresent()) {
            service.eliminar(id);
        }*/

        em.close();



    }

}

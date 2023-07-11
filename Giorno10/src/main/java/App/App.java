package App;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.Evento;
import entities.EventoDAO;
import entities.TipoEvento;
import util.JpaUtil;

public class App {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		Evento primoEvento = new Evento("Mobile World Congress", LocalDate.now(), "Fiera internazionaòe",
				TipoEvento.PUBBLICO, 10000);
		Evento secondoEvento = new Evento("Conferenza Apple", LocalDate.now(), "FPresentazione iphone",
				TipoEvento.PUBBLICO, 10000);

		EventoDAO Ev = new EventoDAO(em);

		Ev.save(primoEvento);
		Ev.save(secondoEvento);

		Evento eventoTrovato = Ev.findById(UUID.fromString("247fa5bb-e712-4329-b1f2-daa18e4ff97b"));
		System.out.println("Elemento trovato tramite id:" + eventoTrovato);

		Ev.deleteById(UUID.fromString("eb282352-9865-42a7-adb8-ae9608e2c448"));

		Ev.refresh(UUID.fromString("7fab6983-4201-4196-9c74-36323c5e3277"));

		em.close();
		emf.close();
	}

}

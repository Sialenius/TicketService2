package control;

import DAO.UserService;
import DAO.MyApplicationContextConfiguration;
import model.*;

import DAO.UserDAO;
import DAO.TicketDAO;

import model.enums.ConcertHall;
import model.enums.StadiumSector;
import model.enums.TicketType;
import model.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import service.TicketDataReader;
import view.Printable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
@Transactional
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;


    public void index() {
        List<Ticket> tickets = ticketDAO.index();

        tickets.forEach(System.out::println);
    }


}

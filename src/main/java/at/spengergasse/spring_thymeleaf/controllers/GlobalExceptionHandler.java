package at.spengergasse.spring_thymeleaf.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;
import org.hibernate.HibernateException;
import jakarta.persistence.PersistenceException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
        TransactionException.class,
        DataAccessException.class,
        HibernateException.class,
        PersistenceException.class,
        SQLException.class
    })
    public String handleDatabaseError(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Die Verbindung zur Datenbank konnte nicht hergestellt werden. Bitte stellen Sie sicher, dass MySQL (XAMPP) gestartet ist.");
        return "error_db";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNotFoundError(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Der angeforderte Datensatz wurde nicht gefunden. Er wurde möglicherweise bereits gelöscht.");
        return "error_db";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralError(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Ein unerwarteter Fehler ist aufgetreten: " + ex.getMessage());
        return "error_db";
    }
}

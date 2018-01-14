package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.base.CrudService;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author Martin Palenik
 */
public interface BookService extends CrudService<Book> {
    Set<Book> getAllBooksLoanedAfterDate(Date date) throws DataAccessException;
    void removeBookFromUserLoan(Member member, Long book);
}
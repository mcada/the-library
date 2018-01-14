package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.base.CrudService;

import java.util.List;

/**
 * Service layer for loan entity.
 *
 * @author Jan Tlamicha(xtlamich)
 */
public interface LoanService extends CrudService<Loan> {

    /**
     * Returns all loans for the specific member 
     *
     * @param member for witch loans should be returned
     * @return all loans for specified member 
     */
    
    List<Loan> allLoansOfMember(Member member) throws DataAccessException;

    List<Book> allBooksOfMember(Member member) throws DataAccessException;

    }

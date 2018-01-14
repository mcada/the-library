package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.dao.BookDao;
import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
import cz.fi.muni.pa165.library.persistance.dao.LoanItemDao;
import cz.fi.muni.pa165.library.persistance.dao.base.CrudDao;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.base.CrudServiceImpl;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation for Book service.
 *
 * @author Martin Palenik
 */
@Service
public class BookServiceImpl extends CrudServiceImpl<Book> implements BookService {

    @Inject
    private LoanDao loanDao;

    @Inject
    private LoanItemDao loanItemDao;
    
    @Inject
    public BookServiceImpl(BookDao bookDao, LoanDao loanDao) {
        super(bookDao);
        this.loanDao = loanDao;
    }

    @Override
    public Set<Book> getAllBooksLoanedAfterDate(Date date) throws DataAccessException {
        try {
            if (date == null) {
                throw new DataAccessException("Date is null");
            }
            
            Set<Book> loanedBooks = new HashSet<>();

            //maybe we can support this search via DAO and db query so it is faster
            for(Loan loan : loanDao.findAll()) {
                if(loan.getLoanCreated().after(date)) {
                    for (LoanItem item : loan.getLoanItems()) {
                        loanedBooks.add(item.getBook());
                    }
                }
            }
            return loanedBooks;
        } catch (Exception ex){
            throw new LibraryServiceException("Problem with data", ex);
        }  
    }

    @Override
    public void removeBookFromUserLoan(Member member, Long book) {
        try {
            List<Loan> allLoans = loanDao.allLoansOfMember(member);
            for(Loan l: allLoans) {
                Set<LoanItem> loanItems = l.getLoanItems();
                for(LoanItem li:loanItems) {
                    if(li.getBook().getId().longValue() == book.longValue()) {
                        l.removeLoanItem(li);
                        loanItemDao.delete(li);
                        return;
                    }
                }
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
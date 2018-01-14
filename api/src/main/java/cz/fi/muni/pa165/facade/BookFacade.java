package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.dto.CreateBookDTO;
import cz.fi.muni.pa165.facade.base.CrudFacade;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;

/**
 * Facade interface LoanItem
 *
 * @author Martin Palenik
 */
public interface BookFacade extends CrudFacade<BookDTO> {

    public Long create(CreateBookDTO dto)throws DataAccessException;

    void removeBookFromUserLoan(Long memberId, Long book);

}

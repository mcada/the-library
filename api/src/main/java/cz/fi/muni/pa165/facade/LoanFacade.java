package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.dto.CreateLoanDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;

import java.util.List;

/**
 * Facade for Loan entity.
 *
 * @author Jan Tlamicha
 */
public interface LoanFacade {

    /**
     *  Persist loan in database
     * @param loan Loan object to be created.
     * @return All created Loan ids.
     * @throws IllegalArgumentException If error is caused, exception is thrown.
     */
    Long makeLoan(CreateLoanDTO loan) throws DataAccessException, IllegalArgumentException;

    /**
     * Finding loan by id.
     * @param id Loan id.
     * @return  Loan.
     */
    LoanDTO findById(Long id) throws DataAccessException;

    /**
     * Finds all loans.
     * @return List of all Loans.
     */
    List<LoanDTO> findAll() throws DataAccessException;

    /**
     * Return all loans of member.
     * @param memberId Member ID.
     * @return List of all loans belonging to the member.
     */
    List<LoanDTO> allLoansOfMember(Long memberId) throws DataAccessException;

    /**
     * Delete loan by loan ID.
     * @param loanId ID of loan.
     */
    void delete(Long loanId) throws DataAccessException;
    /**
     * Update loan.
     * @param loanDTO Loan DTO.
     */
    void update(LoanDTO loanDTO) throws DataAccessException;

    List<BookDTO> allBooksOfMember(Long memberId)throws DataAccessException;

    void gimmeThatBook(Long id,Long book);

    }
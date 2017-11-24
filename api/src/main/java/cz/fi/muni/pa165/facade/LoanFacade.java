package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.CreateLoanDTO;
import cz.fi.muni.pa165.dto.LoanDTO;

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
    List<Long> createLoan(CreateLoanDTO loan) throws IllegalArgumentException;

    /**
     * Finding loan by id.
     * @param id Loan id.
     * @return  Loan.
     */
    LoanDTO findById(Long id);

    /**
     * Finds all loans.
     * @return List of all Loans.
     */
    List<LoanDTO> findAll();

    /**
     * Return all loans of member.
     * @param memberId Member ID.
     * @return List of all loans belonging to the member.
     */
    List<LoanDTO> allLoansOfMember(Long memberId) throws IllegalArgumentException;

    /**
     * Delete loan by loan ID.
     * @param loanId ID of loan.
     */
    void delete(Long loanId) throws IllegalArgumentException;


}

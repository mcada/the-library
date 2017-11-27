package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.exception.LibraryServiceException;
import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import java.util.List;
import javax.inject.Inject;

import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.base.CrudService;
import cz.fi.muni.pa165.service.base.CrudServiceImpl;
import org.springframework.stereotype.Service;
/**
 * @author Jan Tlamicha(xtlamich)
 */
@Service
public class LoanServiceImpl extends CrudServiceImpl<Loan> implements LoanService {
    private LoanDao loanDao;

    @Inject
    public LoanServiceImpl(LoanDao loanDao) {
        super(loanDao);
        this.loanDao = loanDao;
    }

    @Override
    public List<Loan> allLoansOfMember(Member member) throws DataAccessException {
        try {
            if (member == null) {
                throw new DataAccessException("Member is null");
            }
            return loanDao.allLoansOfMember(member);
        } catch (Exception ex){
            throw new LibraryServiceException("Problem with data", ex);
        }
    }

}

package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.dto.CreateLoanDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.BookService;
import cz.fi.muni.pa165.service.LoanItemService;
import cz.fi.muni.pa165.service.LoanService;
import cz.fi.muni.pa165.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Date;

/**
 * LoanFacade implementation
 * @author Jan Tlamicha
 */
@Service
@Transactional
public class LoanFacadeImpl implements LoanFacade {

    @Inject
    private MappingService mappingService;
    @Inject
    private LoanService loanService;
    @Inject
    private MemberService memberService;
    @Inject
    private LoanItemService loanItemService;
    @Inject
    private BookService bookService;

    public LoanFacadeImpl(LoanService loanService,
                          MemberService memberService,
                          LoanItemService loanItemService,
                          MappingService mappingService){
        this.loanService = loanService;
        this.mappingService = mappingService;
        this.memberService = memberService;
        this.loanItemService = loanItemService;
    }


    @Override
    public Long makeLoan(CreateLoanDTO loan) throws DataAccessException, IllegalArgumentException {
        Member member = memberService.findById(loan.getMemberId());
        if(member == null || loan.getLoanitemIds().isEmpty()){
            throw new IllegalArgumentException("No loan book items.");
        }
        Loan newLoan = mappingService.map(loan, Loan.class);
        newLoan.setMember(member);
        newLoan.setLoanCreated(new Date());
        Set<LoanItem> loanItemList = new HashSet<>();
        for(Long loanItemId : loan.getLoanitemIds()){
            LoanItem item = loanItemService.findById(loanItemId);
            loanItemList.add(item);
        }
        newLoan.setLoanItems(loanItemList);
        loanService.create(newLoan);
        return newLoan.getId();
    }

    @Override
    public LoanDTO findById(Long id) throws DataAccessException {
        return mappingService.map(loanService.findById(id), LoanDTO.class);
    }

    @Override
    public List<LoanDTO> findAll() throws DataAccessException {
        return mappingService.map(loanService.findAll(), LoanDTO.class);
    }

    @Override
    public List<LoanDTO> allLoansOfMember(Long memberId)throws DataAccessException {
        Member member = memberService.findById(memberId);
        return mappingService.map(loanService.allLoansOfMember(member), LoanDTO.class);
    }

    @Override
    public List<BookDTO> allBooksOfMember(Long memberId)throws DataAccessException {
        Member member = memberService.findById(memberId);
        return mappingService.map(loanService.allBooksOfMember(member), BookDTO.class);
    }

    @Override
    public void delete(Long loanId) throws DataAccessException {
        Loan loan = loanService.findById(loanId);
        loanService.delete(loan);
    }

    @Override
    public void update(LoanDTO loanDTO) {
        Loan loan = mappingService.map(loanDTO,Loan.class);
        loanService.update(loan);
    }
    @Override
    public void gimmeThatBook(Long id,Long book) {
        //mam id uzivatele, id knihy co chce pucit --> musim vytvorit loan, do nej strcit novy loanItem s knihou
        Loan loan = new Loan();

        LoanItem loanItem = new LoanItem();
        loanItem.setBook(bookService.findById(book));
        loanItem.setLoan(loan);
        Set<Loan> loanSet = new HashSet<>();
        loanSet.add(loan);

        loan.setLoanCreated(new Date());

        loanItemService.create(loanItem);

        loan.setMember(memberService.findById(id));
        loanService.create(loan);
    }


}

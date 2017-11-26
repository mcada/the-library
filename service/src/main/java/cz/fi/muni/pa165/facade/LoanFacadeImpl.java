package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.CreateLoanDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.facade.base.CrudFacadeImpl;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.LoanItemService;
import cz.fi.muni.pa165.service.LoanService;
import cz.fi.muni.pa165.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class LoanFacadeImpl extends CrudFacadeImpl<LoanDTO, Loan> implements LoanFacade {

    @Inject
    public LoanFacadeImpl(LoanService loanService, MappingService mappingService) {
        super(loanService, mappingService, LoanDTO.class, Loan.class);
    }

    @Inject
    private ListMapper mapper;

    @Inject
    private LoanService loanService;

    @Inject
    private MemberService memberService;

    @Inject
    private LoanItemService loanItemService;


    @Override
    public List<LoanDTO> allLoansOfMember(Long memberId)throws DataAccessException {
        Member member = memberService.findById(memberId);
        return mapper.map(loanService.allLoansOfMember(member), LoanDTO.class);
    }



}
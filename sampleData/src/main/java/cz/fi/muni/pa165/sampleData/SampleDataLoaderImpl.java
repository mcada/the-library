package cz.fi.muni.pa165.sampleData;

import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Loan;
import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.BookService;
import cz.fi.muni.pa165.service.LoanItemService;

import cz.fi.muni.pa165.service.LoanService;
import cz.fi.muni.pa165.service.MemberService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xtlamich
 */
@Named
@Transactional
public class SampleDataLoaderImpl implements SampleDataLoader {

    private static final Logger log = LoggerFactory.getLogger(SampleDataLoaderImpl.class);

    @Inject
    private BookService bookService;

    @Inject
    private LoanItemService loanItemService;

    @Inject
    private MemberService memberService;

    @Inject
    private LoanService loanService;

    @Override
    public void loadData() throws IOException, ParseException, DataAccessException {
        Book book = new Book();
        book.setAuthor("Arthur C. Clarke");
        book.setTitle("2001 Space Oddysey");
        bookService.create(book);

        Book book1 = new Book();
        book1.setAuthor("Frederik George Pohl");
        book1.setTitle("The Gateway Trip: Tales and Vignettes of the Heechee");
        bookService.create(book1);

        Loan loan = new Loan();

        LoanItem loanItem = new LoanItem();
        loanItem.setBook(book);
        loanItem.setLoan(loan);
        Set<Loan> loanSet = new HashSet<>();
        loanSet.add(loan);


        Member member = new Member();
        member.setEmail("Test1@test.com");
        member.setFirstName("Firstname");
        member.setIsAdmin(false);
        member.setSurname("Tester");
        member.setAddress("address");
        member.setJoinedDate(new Date());

        loan.setLoanCreated(new Date());
        member.setLoans(loanSet);

        Member admin = new Member();
        admin.setEmail("admin@admin.com");
        admin.setAddress("address");
        admin.setFirstName("Admin");
        admin.setSurname("Surname");
        admin.setJoinedDate(new Date());
        loanItemService.create(loanItem);

        memberService.registerMember(admin, "password");
        memberService.registerMember(member, "password");
        memberService.makeAdmin(admin);
        loan.setMember(member);
        loanService.create(loan);
    }


}

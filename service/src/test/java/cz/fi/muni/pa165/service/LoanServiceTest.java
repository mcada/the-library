//package cz.fi.muni.pa165.service;
//
//import cz.fi.muni.pa165.library.persistance.dao.LoanDao;
//import cz.fi.muni.pa165.library.persistance.entity.Book;
//import cz.fi.muni.pa165.library.persistance.entity.Loan;
//import cz.fi.muni.pa165.library.persistance.entity.LoanItem;
//import cz.fi.muni.pa165.library.persistance.entity.Member;
//import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.testng.Assert;
//
//
//import javax.persistence.EntityManager;
//import java.util.*;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class LoanServiceTest {
//
//    @Mock
//    private LoanDao loanDao;
//
//    @Mock
//    private LoanItemService loanItemService;
//
//    @Mock
//    private MemberService memberService;
//
//    @Mock
//    private EntityManager em;
//
//    //@Autowired
//    @InjectMocks
//    private LoanService loanService = new LoanServiceImpl(loanDao);
//
//    private Member member1;
//    private Book book1;
//    private LoanItem loanItem1;
//    private Loan loan1;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        member1 = new Member();
//        member1.setAddress("address");
//        member1.setEmail("aaa@google.com");
//        member1.setFirstName("FirstName");
//        member1.setSurname("Surname");
//        member1.setJoinedDate(new Date());
//        book1 = new Book();
//        book1.setAuthor("Author");
//        book1.setTitle("Title");
//        loan1 = new Loan();
//        loan1.setId(1L);
//        loan1.setMember(member1);
//        loan1.setLoanCreated(new Date());
//        Set<LoanItem> loanItems = new HashSet<>();
//        loanItem1 = new LoanItem();
//        loanItem1.setBook(book1);
//        loanItem1.setLoan(loan1);
//        loanItems.add(loanItem1);
//        loan1.setLoanItems(loanItems);
//
//        Set<Loan> loans = new HashSet<>();
//        loans.add(loan1);
//        member1.setLoans(loans);
//    }
//
//    @Test
//    public void testCreate() throws DataAccessException {
//        loanService.create(loan1);
//        Mockito.verify(loanDao).create(loan1);
//        assert loan1.getId() >= 0;
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void createNullLoan() throws DataAccessException {
//        loanService.create(null);
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void createNullMember() throws DataAccessException {
//        loan1.setMember(null);
//        loanService.create(loan1);
//        verify(loanDao).create(loan1);
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void deleteNullLoan() throws DataAccessException {
//        loanService.delete(null);
//    }
//
//    @Test
//    public void deleteLoan() throws DataAccessException {
//        loanService.delete(loan1);
//        verify(loanDao).delete(loan1);
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void findByIdNotExist() throws DataAccessException {
//        when(loanDao.findById(0L)).thenThrow(DataAccessException.class);
//        loanService.findById(0L);
//    }
//    @Test
//    public void fincById() throws DataAccessException {
//        when(loanDao.findById(loan1.getId())).thenReturn(loan1);
//        Loan returnedLoan = loanService.findById(loan1.getId());
//        Assert.assertEquals(returnedLoan, loan1);
//    }
//
//    @Test
//    public void findAll() throws DataAccessException {
//        List<Loan> loans = new ArrayList<>();
//        loans.add(loan1);
//        when(loanDao.findAll()).thenReturn(loans);
//        List<Loan> returnedLoans = loanService.findAll();
//        Assert.assertEquals(returnedLoans, loans);
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void allLoansOfMemberNull() throws DataAccessException {
//        loanService.allLoansOfMember(null);
//    }
//
//    @Test
//    public void allLoansOfMember() throws DataAccessException {
//        List<Loan> loans = new ArrayList<>();
//        loans.add(loan1);
//        when(loanDao.allLoansOfMember(member1)).thenReturn(loans);
//        loanService.allLoansOfMember(member1);
//        Assert.assertEquals(loans, member1.getLoans());
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void updateNull() throws DataAccessException {
//        loanService.update(null);
//    }
//
//    @Test
//    public void update() throws DataAccessException {
//        when(loanDao.findById(loan1.getId())).thenReturn(loan1);
//        loan1.setLoanItems(null);
//        loanService.update(loan1);
//        verify(loanDao).update(loan1);
//    }
//
//
//}

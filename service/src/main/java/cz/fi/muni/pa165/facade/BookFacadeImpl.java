package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.MappingService;
import cz.fi.muni.pa165.dto.BookDTO;
import cz.fi.muni.pa165.dto.CreateBookDTO;
import cz.fi.muni.pa165.facade.base.CrudFacadeImpl;
import cz.fi.muni.pa165.library.persistance.entity.Book;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.BookService;
import javax.inject.Inject;

import cz.fi.muni.pa165.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Palenik
 */
@Service
@Transactional
public class BookFacadeImpl extends CrudFacadeImpl<BookDTO, Book> implements BookFacade {
   
    @Inject
    private MappingService mapper;
    
    @Inject
    private BookService service;

    @Inject
    private MemberService memberService;
    
    @Inject
    public BookFacadeImpl(BookService bookService, MappingService mappingService) {
        super(bookService, mappingService, BookDTO.class, Book.class);
    }

    @Override
    public Long create(CreateBookDTO dto) throws DataAccessException {
        Book book = mapper.map(dto, Book.class);
        service.create(book);
        return book.getId();
    }

    @Override
    public void removeBookFromUserLoan(Long memberId,Long book) {
        Member member = memberService.findById(memberId);
        service.removeBookFromUserLoan(member, book);

    }
}
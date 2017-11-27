package cz.fi.muni.pa165.service;
import  cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import cz.fi.muni.pa165.service.base.CrudService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 *
 * @author Tomáš Chomo tchomo
 */
@Service
public interface MemberService extends CrudService<Member>{
    /**
     * Registers member
     *
     * @param member to registerMember
     * @param unhashedPassword password string
     */
    void registerMember(Member member, String unhashedPassword) throws DataAccessException;

    /**
     * Checks if member is admin
     *
     * @param member to check
     * @return whether member is an admin
     */
    boolean isAdmin(Member member);

    /**
     * Makes user admin
     * @param member to make admin
     */
    void makeAdmin(Member member) throws DataAccessException;

    Set<Member> getActiveMembers() throws DataAccessException;
    
    
}
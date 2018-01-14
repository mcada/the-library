     /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.controller;

import cz.fi.muni.pa165.dto.LoanDTO;
import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import cz.fi.muni.pa165.dto.MemberDTO;
import cz.fi.muni.pa165.dto.CreateMemberDTO;
import cz.fi.muni.pa165.facade.LoanFacade;
import cz.fi.muni.pa165.facade.MemberFacade;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author tchomo
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    
    @Autowired
    private MemberFacade memberFacade;  
    @Autowired
    private LoanFacade loanFacade;
    
    @Inject
    public MemberController(MemberFacade memberFacade,LoanFacade loanFacade) {
        this.memberFacade = memberFacade;
        this.loanFacade = loanFacade;
    }
  
    
    
    @RequestMapping("/{id}")
    public String showMember(@PathVariable long id, Model model) throws DataAccessException {
        MemberDTO dto = memberFacade.findById(id);
        log.debug("getting member...id {}",id);
        if (dto == null){
            log.error("DTO NULL");
            return "404";
        }
        List<LoanDTO> allLoans = loanFacade.allLoansOfMember(dto.getId());
        model.addAttribute("member", dto);
        model.addAttribute("allLoans", allLoans);
        return "member/show";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
        public String delete(@PathVariable long id,
                         Model model,
                         UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        MemberDTO memberDTO = memberFacade.findById(id);
        log.error("deleting member...id {}",id);
        memberFacade.delete(id);
        redirectAttributes.addFlashAttribute("alert_success", "Member " + memberDTO.getEmail()+ " was deleted.");
        return "redirect:" + uriBuilder.path("/member/list").toUriString();
    }
    
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createMemberView(Model model) {
        if (!model.containsAttribute("member")) {
            model.addAttribute("member", new CreateMemberDTO());
        }
        model.addAttribute("action", "Create");
        return "member/create";
    }


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createMember(@Valid @ModelAttribute("member") CreateMemberDTO dto,
                                BindingResult result,
                                Model model, 
                                UriComponentsBuilder uriBuilder,
                                RedirectAttributes redirectAttributes) throws DataAccessException {

        Long id = memberFacade.registerMember(dto);
        redirectAttributes.addFlashAttribute("alert_success", "Member " + dto.getEmail()+ " was created.");
        return "redirect:" + id;
    }
    
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String listView(Model model) {
        List<MemberDTO> members = memberFacade.findAll();
        model.addAttribute("members", members);
        return "member/list";
    }

}

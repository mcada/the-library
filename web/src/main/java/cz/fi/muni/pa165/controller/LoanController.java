package cz.fi.muni.pa165.controller;

import cz.fi.muni.pa165.dto.CreateLoanDTO;
import cz.fi.muni.pa165.dto.LoanDTO;
import cz.fi.muni.pa165.facade.LoanFacade;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 *
 * @xtlamich
 */

@Controller
@RequestMapping("/loans")
public class LoanController {
    private static final Logger log = LoggerFactory.getLogger(LoanItemController.class);

    @Autowired
    private LoanFacade loanFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws DataAccessException {
        model.addAttribute("loans", loanFacade.findAll());
        log.info("inside of loanItemController, trying to access list");
        return "loans/list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        try {
            model.addAttribute("loan", loanFacade.findById(id));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "loans/detail";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("LoanCreate") CreateLoanDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(LoanCreate={})", formBean);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "loans/create";
        }
        long id = 0;
        try {
            id = loanFacade.makeLoan(formBean);
        } catch (DataAccessException e) {
            return "loans/create";
        }

        redirectAttributes.addFlashAttribute("alert_success", "LoanItem " + id + " was created");
        return "redirect:" + uriBuilder.path("/loans/list").toUriString();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        LoanDTO loan = null;
        try {
            loan = loanFacade.findById(id);
            loanFacade.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("alert_failed", "Loan with id\"" + loan.getId() + "\" was not deleted.");
            return "loans/list";
        }
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Loan with id\"" + loan.getId() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/loans/list").toUriString();
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateLoan(@ModelAttribute("loan") LoanDTO loan, @PathVariable("id") long id,
                                 Model model, UriComponentsBuilder uriBuilder,  RedirectAttributes redirectAttributes) {

        loan.setId(id);
        try {
            loanFacade.update(loan);
        } catch (DataAccessException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("alert_failed", "Loan with id\"" + loan.getId() + "\" was not updated.");
            return "loans/list";
        }
        log.debug(loan.toString() + " updated");
        return "redirect:" + uriBuilder.path("/loans/list").toUriString();
    }

}

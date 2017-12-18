/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *
 * @author tchomo
 */
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

    final static Logger log = LoggerFactory.getLogger(CharacterEncodingFilter.class);

    public void doFilter(ServletRequest r, ServletResponse s, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
        request.setCharacterEncoding("utf-8");
        log.trace("doFilter {}", request.getRequestURL());
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("filter initialized ...");
    }

    public void destroy() {
    }
}

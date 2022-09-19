package com.example.demo;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AskController {
	@Autowired
	AskRepo askrepo; 
	Logger log = Logger.getAnonymousLogger();
	
	@RequestMapping("/Ask")
	public ModelAndView Stocks(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =  new ModelAndView();
		List<Ask> asklist = askrepo.findAll();
		log.info("All the asks from database is added to the asklist");
		if (asklist!=null) {
		log.info("asklist is not empty");
		mv.addObject("asklist", asklist);
		log.info("asklist added to the page");
		mv.setViewName("Ask.jsp");
		}
		return mv;
	}
	
//	e.setEmpno(Integer.parseInt(request.getParameter("no")));
	@RequestMapping("/AddAsk")
    public ModelAndView load(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("NewAsk");
            return mv;
        }
	
	@RequestMapping("/NewAsk")
	public ModelAndView newAskConfig(HttpServletRequest request,HttpServletResponse response) {
		System.out.println(request.getParameter("quantity"));
		ModelAndView mv=new ModelAndView();
		Ask ask = new Ask(); 
		String stock=request.getParameter("stock");
	    ask.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		//ask.setUserid(Integer.parseInt(request.getParameter("userid")));
		ask.setStock(stock);
		//ask.setPricePoint(Double.parseDouble(request.getParameter("pricePoint"))); 
		
		log.info("control went to register ms");
		mv.setViewName("Ask");
		
		Ask a = askrepo.save(ask); 
		return mv;
	}
	

}

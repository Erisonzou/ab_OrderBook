package com.example.demo;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StockController {
	
	String currentStock;
	
	@Autowired 
	StockDAO dao;
	
	Logger log = Logger.getAnonymousLogger();
	
	@RequestMapping("/AskAndBid")
	public ModelAndView askAndBidList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =  new ModelAndView();
		
		List<Ask> stocklist = dao.returnAskbyStock(request.getParameter("stockname"));
		currentStock = request.getParameter("stockname");
		log.info("All the stocks from database is added to the stocklist");
		if (stocklist!=null) {
		log.info("stocklist is not empty");
		mv.addObject("stocklist", stocklist);
		log.info("stocklist added to the page");
		mv.setViewName("AskAndBids.jsp");
		}
		return mv;
	}
	
	@RequestMapping("/orderApplication")
	public ModelAndView askandBidCreation(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =  new ModelAndView();
		mv.setViewName("AskAndBidCreation.jsp");
		return mv;
	}
	
	@RequestMapping("/AskorBidCreation")
	public ModelAndView AskAndBidInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =  new ModelAndView();
		String orderType = request.getParameter("orderType");
		log.info("Grabbed the OrderType");
		
		if (orderType.equals("Ask")) {
			log.info("orderType is Ask");
			Ask a = new Ask();
			
			a.setStock(currentStock);
			a.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			a.setPrice(Double.parseDouble(request.getParameter("price")));
			log.info("set the info into the ask object");
			
			Ask aa = dao.insert(a);
			log.info("save the info into the database");
			
			if (aa!=null) {
				log.info("aa is not null");
				mv.setViewName("Congrats.jsp");
				log.info("set to congrats.jsp");
			}
			
		}else if (orderType.equals("Bid")) {
			log.info("orderType is bid");
			Bid b = new Bid();
			
			b.setStock(currentStock);
			b.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			b.setPrice(Double.parseDouble(request.getParameter("price")));
			
			log.info("set the info into the bid object");
			
			Bid bb = dao.insert(b);
			log.info("save the info into database");
			
			if (bb!=null) {
				log.info("bb is not null");
				mv.setViewName("Congrats.jsp");
				log.info("sent to congrats.jsp");
			}
		}
		
		return mv;
	}
	
}

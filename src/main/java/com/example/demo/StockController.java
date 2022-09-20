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
	
	Ask currentask;
	Bid currentbid;
	List<Bid> currentBidStock;
	List<Ask> currentAskStock;
	String currentStock;
	
	
	@Autowired 
	StockDAO dao;
	
	Logger log = Logger.getAnonymousLogger();
	
	@RequestMapping("/AskAndBid")
	public ModelAndView askAndBidList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =  new ModelAndView();
		
		List<Ask> Asklist = dao.returnAskbyStock(request.getParameter("stockname"));
		List<Bid> Bidlist = dao.returnBidbyStock(request.getParameter("stockname"));
		currentStock = request.getParameter("stockname");
		log.info("All the stocks from database is added to the stocklist");
		if (Asklist!=null && Bidlist != null) {
		log.info("stocklist is not empty");
		
		mv.addObject("bidlist", Bidlist);
		mv.addObject("asklist", Asklist);
		
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
			
			currentask = a;
			
			dao.executeOrder(a);
			
			mv.setViewName("Congrats.jsp");
			
		}else if (orderType.equals("Bid")) {
			log.info("orderType is bid");
			
			Bid b = new Bid();
			
			b.setStock(currentStock);
			b.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			b.setPrice(Double.parseDouble(request.getParameter("price")));
			log.info("set the info into the bid object");
			
			currentbid = b;
			
			dao.executeOrder(b);
			
			mv.setViewName("Congrats.jsp");
		}
		
		return mv;
	}
	
}

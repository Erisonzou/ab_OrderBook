package com.example.demo;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	StockRepo Srepo;	

	@Autowired 
	StockDAO dao;
	
	Ask currentask;
	Bid currentbid;
	String currentStock;
	String CurrentUser;
	
	Logger log = Logger.getAnonymousLogger();
	
	@RequestMapping("/")
	public ModelAndView loadpage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =  new ModelAndView();
		mv.setViewName("login.jsp");
		return mv;
	}
	
	
	@RequestMapping("/login")
	public ModelAndView loginlogic(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv =  new ModelAndView();
		log.info("entered into the login request mapping");
		
		String username = request.getParameter("user");
		String password=request.getParameter("pwd");
		
		CurrentUser = username;
		
		log.info("got the data from the front page");
		
		if(repo.findbyuser(username)!=null&&repo.findbypassword(password)!=null) {
			
			if(repo.findbyuser(username).equals(repo.findbypassword(password))) 
			{
				log.info("login is success");
				mv.setViewName("display.jsp");
				mv.addObject("userid",username);
				
				List<Stocks> stocklist = Srepo.findAll();
				log.info("All the stocks from database is added to the stocklist");
				if (stocklist!=null) {
					log.info("stocklist is not empty");
					mv.addObject("stocklist", stocklist);
					log.info("stocklist added to the page");
					}
				
				log.info("control passed to display.jsp");	
			}
			else
			{	
			mv.setViewName("fail.jsp");
			log.info("control pass to fail.jsp");
				
			}
		}
		else
		{	
		mv.setViewName("fail.jsp");
		log.info("control pass to fail.jsp");
			
		}
		
		return mv;
	}
	
RestTemplate temp=new RestTemplate();
	
	@ResponseBody
	@RequestMapping("/registermsconfig")
	public ModelAndView registermsconfig(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mv=new ModelAndView();
		String user=request.getParameter("username");
		String password=request.getParameter("password");
		String firstname=request.getParameter("fname");
		String lastname=request.getParameter("lname");
		log.info("inside register ms and data set");
		
		String url="http://localhost:8084/register-user/"+firstname+"/"+lastname+"/"+user+"/"+password;
		temp.getForObject(url, String.class);
		log.info("control went to register ms");
		mv.setViewName("DoneRegistration.jsp");
		return mv;
	}
	
	@RequestMapping("/Stocks")
	public ModelAndView Stocks(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =  new ModelAndView();
		List<Stocks> stocklist = Srepo.findAll();
		log.info("All the stocks from database is added to the stocklist");
		if (stocklist!=null) {
		log.info("stocklist is not empty");
		mv.addObject("stocklist", stocklist);
		log.info("stocklist added to the page");
		mv.setViewName("Stock.jsp");
		}
		return mv;
	}
	
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
			
			dao.executeOrder(a,CurrentUser);
			
			mv.setViewName("Congrats.jsp");
			
		}else if (orderType.equals("Bid")) {
			log.info("orderType is bid");
			
			Bid b = new Bid();
			
			b.setStock(currentStock);
			b.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			b.setPrice(Double.parseDouble(request.getParameter("price")));
			log.info("set the info into the bid object");
			
			currentbid = b;
			
			dao.executeOrder(b,CurrentUser);
			
			mv.setViewName("Congrats.jsp");
		}
		
		return mv;
	}
	

	@RequestMapping("/Transactions")
	public ModelAndView Transactions(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =  new ModelAndView();
		List<BookOrder> BookOrderList = dao.returnTransactions();
		mv.addObject("BookOrderlist",BookOrderList);
		mv.setViewName("Transactions.jsp");
		return mv;
	}
	
}


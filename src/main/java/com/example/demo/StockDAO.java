package com.example.demo;


import java.time.LocalDateTime;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockDAO {

	@Autowired
	StockRepo StockRepo;
	
	@Autowired
	AskRepo askRepo;
	
	@Autowired
	BidRepo bidRepo;
	
	@Autowired
	BookOrderRepo OrderRepo;

	
	
	//grab all bid related to the stock
	public List<Bid> returnBidbyStock(String Stockname)
	{
		List<Bid> tempbid = bidRepo.findAll();
		List<Bid> stockList = new ArrayList<Bid>();
		for (Bid b:tempbid) {
			if (b.getStock().equals(Stockname)) {
				stockList.add(b);
			}
		}
		Collections.sort(stockList, new BidComparator());
		return stockList;
	}
	
	
	//grab all ask related to the stock
	public List<Ask> returnAskbyStock(String Stockname)
	{
		List<Ask> tempask = askRepo.findAll();
		List<Ask> stockList = new ArrayList<Ask>();
		for (Ask a:tempask) {
			if (a.getStock().equals(Stockname)) {
				stockList.add(a);
			}
		}
		Collections.sort(stockList, new AskComparator());
		return stockList;	
	}
	
	public void executeOrder(Ask a) 
	{
		List<Bid> bidList = bidRepo.findbyprice(a.getPrice(),a.getStock());
		
		Collections.sort(bidList, new BidComparator());
		
		//
		
		for (Bid bb:bidList) {
			
			LocalDateTime now = LocalDateTime.now();
			
			if (bb.getQuantity() > a.getQuantity())
			{
				
				
				bb.setQuantity(bb.getQuantity() - a.getQuantity());
				bidRepo.save(bb);
				
				
				BookOrder newOrder = new BookOrder();
				newOrder.setUser("bob");
				newOrder.setOrderType("ask");
				newOrder.setStock(a.getStock());
				newOrder.setPrice(bb.getPrice());
				newOrder.setQuantity(a.getQuantity());
				newOrder.setTime(now);
				newOrder.setOrderStatus("Filled");
				OrderRepo.save(newOrder);
				
				break;
			}else if (bb.getQuantity() == a.getQuantity()) 
			{
				bidRepo.delete(bb);
				
				BookOrder newOrder = new BookOrder();
				newOrder.setUser("bob");
				newOrder.setOrderType("ask");
				newOrder.setStock(a.getStock());
				newOrder.setPrice(bb.getPrice());
				newOrder.setQuantity(a.getQuantity());
				newOrder.setTime(now);
				newOrder.setOrderStatus("Filled");
				OrderRepo.save(newOrder);
				
				break;
			}
			else if (bb.getQuantity() < a.getQuantity()){
				a.setQuantity(a.getQuantity() - bb.getQuantity());
				bidRepo.delete(bb);
				
				BookOrder newOrder = new BookOrder();
				newOrder.setUser("bob");
				newOrder.setOrderType("ask");
				newOrder.setStock(a.getStock());
				newOrder.setPrice(bb.getPrice());
				newOrder.setQuantity(bb.getQuantity());
				newOrder.setTime(now);
				newOrder.setOrderStatus("Partially Filled");
				OrderRepo.save(newOrder);
				
			}
		}
		
		if (a.getQuantity() > 0)
		{
			askRepo.save(a);
			LocalDateTime now = LocalDateTime.now();
			
			BookOrder newOrder = new BookOrder();
			newOrder.setUser("bob");
			newOrder.setOrderType("ask");
			newOrder.setStock(a.getStock());
			newOrder.setPrice(a.getPrice());
			newOrder.setQuantity(a.getQuantity());
			newOrder.setTime(now);
			newOrder.setOrderStatus("not Filled");
			OrderRepo.save(newOrder);
		}
		
	}
	
	public void executeOrder(Bid a) 
	{
		List<Ask> AskList = askRepo.findbyprice(a.getPrice(),a.getStock());
		
		Collections.sort(AskList, new AskComparator());
		
		
		for (Ask bb:AskList) {
			
			LocalDateTime now = LocalDateTime.now();
			
			if (bb.getQuantity() > a.getQuantity())
			{
				
				
				bb.setQuantity(bb.getQuantity() - a.getQuantity());
				a.setQuantity(0);
				
				askRepo.save(bb);
				
				BookOrder newOrder = new BookOrder();
				newOrder.setUser("bob");
				newOrder.setOrderType("Bid");
				newOrder.setStock(a.getStock());
				newOrder.setPrice(bb.getPrice());
				newOrder.setQuantity(a.getQuantity());
				newOrder.setTime(now);
				newOrder.setOrderStatus("Filled");
				OrderRepo.save(newOrder);
				
				break;
			}else if (bb.getQuantity() == a.getQuantity()) 
			{
				a.setQuantity(0);
				askRepo.delete(bb);
				
				BookOrder newOrder = new BookOrder();
				newOrder.setUser("bob");
				newOrder.setOrderType("Bid");
				newOrder.setStock(a.getStock());
				newOrder.setPrice(bb.getPrice());
				newOrder.setQuantity(a.getQuantity());
				newOrder.setTime(now);
				newOrder.setOrderStatus("Filled");
				OrderRepo.save(newOrder);
				
				break;
			}
			else if (bb.getQuantity() < a.getQuantity()){
				a.setQuantity(a.getQuantity() - bb.getQuantity());
				askRepo.delete(bb);
				
				BookOrder newOrder = new BookOrder();
				newOrder.setUser("bob");
				newOrder.setOrderType("Bid");
				newOrder.setStock(a.getStock());
				newOrder.setPrice(bb.getPrice());
				newOrder.setQuantity(bb.getQuantity());
				newOrder.setTime(now);
				newOrder.setOrderStatus("Partially Filled");
				OrderRepo.save(newOrder);
				
			}
		}
		
		if (a.getQuantity() > 0)
		{
			LocalDateTime now = LocalDateTime.now();
			bidRepo.save(a);
			
			BookOrder newOrder = new BookOrder();
			newOrder.setUser("bob");
			newOrder.setOrderType("bid");
			newOrder.setStock(a.getStock());
			newOrder.setPrice(a.getPrice());
			newOrder.setQuantity(a.getQuantity());
			newOrder.setTime(now);
			newOrder.setOrderStatus("not Filled");
			OrderRepo.save(newOrder);
			
		}
		
	}
}


class AskComparator implements Comparator<Ask> {
	 
    // Function to compare
    public int compare(Ask a1, Ask a2)
    {
        if (a1.getPrice() == a2.getPrice())
            return 0;
        else if (a1.getPrice() > a2.getPrice())
            return 1;
        else
            return -1;
    }
}

class BidComparator implements Comparator<Bid> {
	 
    // Function to compare
    public int compare(Bid b1, Bid b2)
    {
        if (b1.getPrice() == b2.getPrice())
            return 0;
        else if (b1.getPrice() > b2.getPrice())
            return -1;
        else
            return 1;
    }
}

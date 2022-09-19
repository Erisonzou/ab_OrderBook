package com.example.demo;

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
	
	public Ask insert(Ask a)
	{
		return askRepo.save(a);
	}
	
	public Bid insert(Bid b) {
		return bidRepo.save(b);
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
            return 1;
        else
            return -1;
    }
}

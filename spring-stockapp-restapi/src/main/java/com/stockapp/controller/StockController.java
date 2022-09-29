package com.stockapp.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.model.Buyer;
import com.stockapp.model.Stock;
import com.stockapp.service.IBuyerService;
import com.stockapp.service.IStockService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("stocks-api")
public class StockController {

	private IStockService stockService;
	private IBuyerService buyerService;

	@Autowired
	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}

	@Autowired
	public void setBuyerService(IBuyerService buyerService) {
		this.buyerService = buyerService;
	}

	// http://localhost:8081/stocks-api/stocks
	@PostMapping("/stocks")
	public ResponseEntity<Void> addStock(@RequestBody @Valid Stock stock) {
		stockService.addStock(stock);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "one stock is added");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
	}

	// http://localhost:8081/stocks-api/stocks
	@PutMapping("/stocks")
	public ResponseEntity<Void> updateStock(@RequestBody Stock stock) {
		stockService.updateStock(stock);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "one stock is updated");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
	}

	// http://localhost:8081/stocks-api/stocks/1
	@DeleteMapping("/stocks/{stockId}")
	public ResponseEntity<Void> deleteStock(@PathVariable("stockId") int stockId) {
		stockService.deleteStock(stockId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "one stock is deleted");
		headers.add("info", "one stock is deleted");
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// http://localhost:8081/stocks-api/stocks
	@GetMapping("/stocks")
	public ResponseEntity<List<Stock>> getAllStocks() {
		// response body
		List<Stock> stocks = stockService.getAll();
		HttpHeaders headers = new HttpHeaders();
		// response headers
		headers.add("desc", "All stocks");
		headers.add("info", "Getting stocks from db");
		ResponseEntity<List<Stock>> responseEntity = new ResponseEntity<List<Stock>>(stocks, headers, HttpStatus.OK);
		return responseEntity;
	}

	// http://localhost:8081/stocks-api/stocks/stockId/1
	@GetMapping("/stocks/stockId/{stockId}")
	public ResponseEntity<Stock> getById(@PathVariable("stockId") int stockId) {
		Stock stock = stockService.getByStockId(stockId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "one stock");
		headers.add("info", "getting one stock from db");
		ResponseEntity<Stock> responseEntity = new ResponseEntity<Stock>(stock, headers, HttpStatus.OK);
		return responseEntity;
	}

	// custom Querry
	// http://localhost:8081/stocks-api/stocks/type/NSE
	@GetMapping("/stocks/type/{type}")
	public ResponseEntity<List<Stock>> findStockDetailByType(@PathVariable("type") String type) {
		List<Stock> stock = stockService.getByDetailStockType(type);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "one stock");
		headers.add("info", "getting one stock from db");
		ResponseEntity<List<Stock>> responseEntity = new ResponseEntity<List<Stock>>(stock, headers, HttpStatus.OK);
		return responseEntity;
	}

	// custom Querry
	// http://localhost:8081/stocks-api/stocks/termname/SHORTWEEK
	@GetMapping("/stocks/termname/{termName}")
	public ResponseEntity<List<Stock>> findStockTermName(@PathVariable("termName") String termName) {
		List<Stock> stock = stockService.getByTermTermName(termName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting stocks by term");
		headers.add("info", "getting stocks by term");
		ResponseEntity<List<Stock>> responseEntity = new ResponseEntity<List<Stock>>(stock, headers, HttpStatus.OK);
		return responseEntity;
	}

	// custom Querry
	// http://localhost:8081/stocks-api/stocks/buyername/ARUN PADITAR
	@GetMapping("/stocks/buyername/{buyerName}")
	public ResponseEntity<List<Stock>> findStocksByBuyer(@PathVariable("buyerName") String buyerName) {
		List<Stock> stock = stockService.getStocksByBuyer(buyerName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting stocks by buyername");
		headers.add("info", "getting stocks by buyername");
		ResponseEntity<List<Stock>> responseEntity = new ResponseEntity<List<Stock>>(stock, headers, HttpStatus.OK);
		return responseEntity;
	}

	// custom Querry
	// http://localhost:8081/stocks-api/stocks/accountnumber/100001
	@GetMapping("/stocks/accountnumber/{accountNumber}")
	public ResponseEntity<List<Stock>> findStocksByAccountnumber(@PathVariable("accountNumber") long accountNumber) {
		List<Stock> stock = stockService.getStocksByBuyerAccountNumber(accountNumber);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting stocks by accountnumber");
		headers.add("info", "getting stocks by accountnumber");
		ResponseEntity<List<Stock>> responseEntity = new ResponseEntity<List<Stock>>(stock, headers, HttpStatus.OK);
		return responseEntity;
	}

	// custom Querry
	// http://localhost:8081/stocks-api/stocks/currentprice/200
	@GetMapping("/stocks/currentprice/{currentPrice}")
	public ResponseEntity<List<Stock>> findStocksByPrice(@PathVariable("currentPrice") double currentPrice) {
		List<Stock> stock = stockService.getByStockPrice(currentPrice);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting stocks by price");
		headers.add("info", "getting stocks by price");
		ResponseEntity<List<Stock>> responseEntity = new ResponseEntity<List<Stock>>(stock, headers, HttpStatus.OK);
		return responseEntity;
	}

	// http://localhost:8081/stocks-api/buyers
	@GetMapping("/buyers")
	public ResponseEntity<List<Buyer>> getAllBuyers() {
		// response body
		List<Buyer> buyers = buyerService.getAll();
		HttpHeaders headers = new HttpHeaders();
		// response headers
		headers.add("desc", "All buyers");
		headers.add("info", "Getting buyers from db");
		ResponseEntity<List<Buyer>> responseEntity = new ResponseEntity<List<Buyer>>(buyers, headers, HttpStatus.OK);
		return responseEntity;
	}

	// http://localhost:8081/stocks-api/buyers/stockname/NIFTY50
	@GetMapping("/buyers/stockname/{stockName}")
	public ResponseEntity<List<Buyer>> findBuyersByStockName(@PathVariable("stockName") String stockName) {
		// response body
		List<Buyer> buyers = buyerService.getBuyersByStock(stockName);
		HttpHeaders headers = new HttpHeaders();
		// response headers
		headers.add("desc", "All buyers");
		headers.add("info", "Getting buyers from db");
		ResponseEntity<List<Buyer>> responseEntity = new ResponseEntity<List<Buyer>>(buyers, headers, HttpStatus.OK);
		return responseEntity;
	}
}

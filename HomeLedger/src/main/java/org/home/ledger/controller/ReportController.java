package org.home.ledger.controller;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.home.ledger.model.Particulars;
import org.home.ledger.service.ParticularService;
import org.home.ledger.service.ReportService;
import org.home.ledger.utils.DateUtils;
import org.home.ledger.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.jasperreports.engine.JRException;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	@Autowired
	private ParticularService particularService;
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/generateReport")
	public String generateReport(HttpServletRequest request) throws FileNotFoundException, JRException, MessagingException {
		boolean reportGenerated = reportService.exportReport();
		if(reportGenerated) {
			httpSession.setAttribute("status", "success");
			return "redirect:/reportGenerated";
		}
		else {
			httpSession.setAttribute("status", "failed");
			return "redirect:/reportNotGenerated";
		}
	}
	@GetMapping("/generateReportByPeriod")
	public String generateReportByPeriod(HttpServletRequest request,@RequestParam("periodFrom") String periodFrom, @RequestParam("periodThru") String periodThru) throws FileNotFoundException, JRException, MessagingException, ParseException {
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse(periodFrom);
		Date thru = new SimpleDateFormat("yyyy-MM-dd").parse(periodThru);
		boolean reportGenerated = reportService.exportReportByPeriod(from, thru);
		if(reportGenerated) {
			httpSession.setAttribute("status", "success");
			httpSession.setAttribute("periodFrom", periodFrom);
			httpSession.setAttribute("periodThru", periodThru);
			return "redirect:/reportGeneratedByPeriod";
		}
		else {
			httpSession.setAttribute("status", "failed");
			httpSession.setAttribute("periodFrom", periodFrom);
			httpSession.setAttribute("periodThru", periodThru);
			return "redirect:/reportNotGeneratedByPeriod";
		}
	}
	@GetMapping("/generateReportByMonth")
	public String generateReportByMonth(HttpServletRequest request,@RequestParam("periodMonth") String periodMonth) throws FileNotFoundException, JRException, MessagingException, ParseException {
		String fromD = "01-"+periodMonth;
		Date from = new SimpleDateFormat("dd-MMM-yyyy").parse(fromD);
		String tempPeriodThru = DateUtils.computeEndDate(from);
		Date thru = new SimpleDateFormat("yyyy-MM-dd").parse(tempPeriodThru);
		String periodFrom = new SimpleDateFormat("yyyy-MM-dd").format(from);
		String periodThru = new SimpleDateFormat("yyyy-MM-dd").format(thru);
		boolean reportGenerated = reportService.exportReportByPeriod(from, thru);
		if(reportGenerated) {
			httpSession.setAttribute("status", "success");
			httpSession.setAttribute("periodMonth", periodMonth);
			httpSession.setAttribute("periodFrom", periodFrom);
			httpSession.setAttribute("periodThru", periodThru);
			return "redirect:/reportGeneratedByMonth";
		}
		else {
			httpSession.setAttribute("status", "failed");
			httpSession.setAttribute("periodMonth", periodMonth);
			httpSession.setAttribute("periodFrom", periodFrom);
			httpSession.setAttribute("periodThru", periodThru);
			return "redirect:/reportNotGeneratedByMonth";
		}
	}
	
	@GetMapping("/reportGenerated")
	public String reportGenerated(HttpServletRequest request) throws FileNotFoundException, JRException {
		request.setAttribute("MODE", "VIEW_ALL_SPENDS");
		List<Particulars> particulars = particularService.getAllSpends();
		Collections.sort(particulars,Particulars.particularComparator);
		BigDecimal totalAmount = particularService.getTotalAmount();
		if(totalAmount == null)
			totalAmount=new BigDecimal(0.00);
		totalAmount = NumberUtils.round(totalAmount+"");
		request.setAttribute("particulars", particulars);
		request.setAttribute("totalAmount", totalAmount);
		return "home";
	}
	@GetMapping("/reportGeneratedByPeriod")
	public String reportGeneratedByPeriod(HttpServletRequest request) throws FileNotFoundException, JRException, ParseException {
		String periodFrom = (String) httpSession.getAttribute("periodFrom");
		String periodThru = (String) httpSession.getAttribute("periodThru");
		request.setAttribute("MODE", "VIEW_SPENDS_BY_PERIOD");
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse(periodFrom);
		Date thru = new SimpleDateFormat("yyyy-MM-dd").parse(periodThru);
		List<Particulars> particulars = particularService.getSpendsByPeriod(from, thru);
		Collections.sort(particulars,Particulars.particularComparator);
		BigDecimal totalAmount = particularService.getTotalAmountByPeriod(from, thru);
		if(totalAmount == null)
			totalAmount=new BigDecimal(0.00);
		totalAmount = NumberUtils.round(totalAmount+"");
		request.setAttribute("particulars", particulars);
		request.setAttribute("totalAmount", totalAmount);
		return "home";
	}
	@GetMapping("/reportGeneratedByMonth")
	public String reportGeneratedByMonth(HttpServletRequest request) throws FileNotFoundException, JRException, ParseException {
		String periodFrom = (String) httpSession.getAttribute("periodFrom");
		String periodThru = (String) httpSession.getAttribute("periodThru");
		request.setAttribute("MODE", "VIEW_SPENDS_BY_MONTH");
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse(periodFrom);
		Date thru = new SimpleDateFormat("yyyy-MM-dd").parse(periodThru);
		List<Particulars> particulars = particularService.getSpendsByPeriod(from, thru);
		Collections.sort(particulars,Particulars.particularComparator);
		BigDecimal totalAmount = particularService.getTotalAmountByPeriod(from, thru);
		if(totalAmount == null)
			totalAmount=new BigDecimal(0.00);
		totalAmount = NumberUtils.round(totalAmount+"");
		List<String> months = new ArrayList<String>();		
		List<Date> dates = particularService.getDates();
		Set<Date> unSortedMonths = new HashSet<>();
		for (Date date : dates) {
			String tempDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			tempDate = tempDate.substring(0,tempDate.lastIndexOf("-"))+"-01";
			Date temp = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
			unSortedMonths.add(temp);
		}
		List<Date> unSortedMonthsList = new ArrayList<>(unSortedMonths);
		Collections.sort(unSortedMonthsList, new Comparator<Date>() {
			@Override
			public int compare(Date o1, Date o2) {
				return o2.compareTo(o1);
			}
		});
		for(Date d: unSortedMonthsList) {
			String date = new SimpleDateFormat("MMM-yyyy").format(d);
			months.add(date);
		}
		request.setAttribute("months", months);
		request.setAttribute("particulars", particulars);
		request.setAttribute("totalAmount", totalAmount);
		return "home";
	}
	@GetMapping("/reportNotGenerated")
	public String reportNotGenerated(HttpServletRequest request) throws FileNotFoundException, JRException {
		request.setAttribute("MODE", "VIEW_ALL_SPENDS");
		List<Particulars> particulars = particularService.getAllSpends();
		Collections.sort(particulars,Particulars.particularComparator);
		BigDecimal totalAmount = particularService.getTotalAmount();
		if(totalAmount == null)
			totalAmount=new BigDecimal(0.00);
		totalAmount = NumberUtils.round(totalAmount+"");
		request.setAttribute("particulars", particulars);
		request.setAttribute("totalAmount", totalAmount);
		return "home";
	}
	@GetMapping("/reportNotGeneratedByPeriod")
	public String reportNotGeneratedByPeriod(HttpServletRequest request) throws FileNotFoundException, JRException, ParseException {
		String periodFrom = (String) httpSession.getAttribute("periodFrom");
		String periodThru = (String) httpSession.getAttribute("periodThru");
		request.setAttribute("MODE", "VIEW_SPENDS_BY_PERIOD");
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse(periodFrom);
		Date thru = new SimpleDateFormat("yyyy-MM-dd").parse(periodThru);
		List<Particulars> particulars = particularService.getSpendsByPeriod(from, thru);
		Collections.sort(particulars,Particulars.particularComparator);
		BigDecimal totalAmount = particularService.getTotalAmountByPeriod(from, thru);
		if(totalAmount == null)
			totalAmount=new BigDecimal(0.00);
		totalAmount = NumberUtils.round(totalAmount+"");
		request.setAttribute("particulars", particulars);
		request.setAttribute("totalAmount", totalAmount);
		return "home";
	}
	@GetMapping("/reportNotGeneratedByMonth")
	public String reportNotGeneratedByMonth(HttpServletRequest request) throws FileNotFoundException, JRException, ParseException {
		String periodFrom = (String) httpSession.getAttribute("periodFrom");
		String periodThru = (String) httpSession.getAttribute("periodThru");
		request.setAttribute("MODE", "VIEW_SPENDS_BY_MONTH");
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse(periodFrom);
		Date thru = new SimpleDateFormat("yyyy-MM-dd").parse(periodThru);
		List<Particulars> particulars = particularService.getSpendsByPeriod(from, thru);
		Collections.sort(particulars,Particulars.particularComparator);
		BigDecimal totalAmount = particularService.getTotalAmountByPeriod(from, thru);
		if(totalAmount == null)
			totalAmount=new BigDecimal(0.00);
		totalAmount = NumberUtils.round(totalAmount+"");
		List<String> months = new ArrayList<String>();		
		List<Date> dates = particularService.getDates();
		Set<Date> unSortedMonths = new HashSet<>();
		for (Date date : dates) {
			String tempDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			tempDate = tempDate.substring(0,tempDate.lastIndexOf("-"))+"-01";
			Date temp = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
			unSortedMonths.add(temp);
		}
		List<Date> unSortedMonthsList = new ArrayList<>(unSortedMonths);
		Collections.sort(unSortedMonthsList, new Comparator<Date>() {
			@Override
			public int compare(Date o1, Date o2) {
				return o2.compareTo(o1);
			}
		});
		for(Date d: unSortedMonthsList) {
			String date = new SimpleDateFormat("MMM-yyyy").format(d);
			months.add(date);
		}
		request.setAttribute("months", months);
		request.setAttribute("particulars", particulars);
		request.setAttribute("totalAmount", totalAmount);
		return "home";
	}
}

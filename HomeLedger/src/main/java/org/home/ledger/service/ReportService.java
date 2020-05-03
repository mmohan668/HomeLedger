package org.home.ledger.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.home.ledger.model.Particulars;
import org.home.ledger.model.User;
import org.home.ledger.repository.ParticularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	@Autowired
	private ParticularRepository particularRepository;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private NotificationService notificationService;
	
	public boolean exportReport() throws FileNotFoundException, JRException, MessagingException {
		List<Particulars> particulars = new ArrayList<Particulars>();
		List<Date> listOfDates = new ArrayList<>();
		Date sysDate = new Date();
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(sysDate);
		String path = "E:\\Reports\\HomeLedger_"+date+".pdf";
		particularRepository.findAll().forEach(p -> particulars.add(p));
		for(Particulars particular: particulars)
			listOfDates.add(particular.getParticularDate());
		Date minDate = Collections.min(listOfDates);
		Date maxDate = Collections.max(listOfDates);
		String formattedMinDate = new SimpleDateFormat("dd-MMM-yyyy").format(minDate);
		String formattedMaxDate = new SimpleDateFormat("dd-MMM-yyyy").format(maxDate);
		String period = formattedMinDate +" TO "+ formattedMaxDate;
		BigDecimal totalAmount = particularRepository.totalAmount();
		Collections.sort(particulars, Particulars.particularComparator);
		File file = ResourceUtils.getFile("classpath:particulars.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(particulars);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("totalAmount", totalAmount+"");
		parameters.put("period", period+"");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,path);
		User user = (User) httpSession.getAttribute("user");
		user.setPassword("");
		notificationService.sendReport(user, period, totalAmount+"", path);
		File fileToDelete = new File(path);
		fileToDelete.delete();
		return true;
	}
	public boolean exportReportByPeriod(Date from, Date thru) throws FileNotFoundException, JRException, MessagingException {
		List<Particulars> particulars = new ArrayList<Particulars>();
		List<Date> listOfDates = new ArrayList<>();
		Date sysDate = new Date();
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(sysDate);
		String path = "E:\\Reports\\HomeLedger_"+date+".pdf";
		particularRepository.findByParticularDateBetween(from, thru).forEach(p -> particulars.add(p));
		for(Particulars particular: particulars)
			listOfDates.add(particular.getParticularDate());
		Date minDate = Collections.min(listOfDates);
		Date maxDate = Collections.max(listOfDates);
		String formattedMinDate = new SimpleDateFormat("dd-MMM-yyyy").format(minDate);
		String formattedMaxDate = new SimpleDateFormat("dd-MMM-yyyy").format(maxDate);
		String period = formattedMinDate +" TO "+ formattedMaxDate;
		BigDecimal totalAmount = particularRepository.totalAmountbyPeriod(from, thru);
		Collections.sort(particulars, Particulars.particularComparator);
		File file = ResourceUtils.getFile("classpath:particulars.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(particulars);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("totalAmount", totalAmount+"");
		parameters.put("period", period+"");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,path);
		User user = (User) httpSession.getAttribute("user");
		user.setPassword("");
		notificationService.sendReport(user, period, totalAmount+"", path);
		File fileToDelete = new File(path);
		fileToDelete.delete();
		return true;
	}
}

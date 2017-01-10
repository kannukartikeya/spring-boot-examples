package poc.pbdefault.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.mashape.unirest.http.exceptions.UnirestException;

import poc.pbdefault.domain.Factors;
import poc.pbdefault.domain.PDModel;
import poc.pbdefault.mqtt.MqttPublishSubscribeUtility;
import poc.pbdefault.repositories.FactorRepository;
import poc.pbdefault.repositories.PDModelRepository;
import poc.pbdefault.service.DominoService;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class PDModelController {

	@Autowired
	PDModelRepository repository;

	@Autowired
	FactorRepository deviceRepository;


	@RequestMapping(value="/pdModels",method=RequestMethod.GET)
	public String pdList(Model model) {
		model.addAttribute("pdModels", repository.findAll());
		return "pdModels";
	}


	@RequestMapping(value="/",method=RequestMethod.GET)
	public String pdListRoot(Model model) {
		model.addAttribute("pdModels", repository.findAll());
		return "pdModels";
	}
	
	
	@RequestMapping(value="/pdModels",method=RequestMethod.POST)
	public String roomAdd(@RequestParam int last_fico_range_high, @RequestParam int last_fico_range_low,@RequestParam float revol_util, @RequestParam int inq_last_6mths, @RequestParam(value="is_rent", defaultValue="false")  boolean is_rent,double loan_amt,double annual_inc, Model model) {
		PDModel pdModel = new PDModel();
		pdModel.setLast_fico_range_high(last_fico_range_high);
		pdModel.setLast_fico_range_low(last_fico_range_low);
		pdModel.setRevol_util(revol_util);
		pdModel.setInq_last_6mths(inq_last_6mths);
		pdModel.setLoan_amt(loan_amt);
		pdModel.setAnnual_inc(annual_inc);
		if(is_rent)
		pdModel.setIs_rent(is_rent);
		else 
			pdModel.setIs_rent(false);	
			
		try {
			DominoService.getPBDetails(pdModel);
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repository.save(pdModel);

		
		/*model.addAttribute("pdModel", pdModel);
		model.addAttribute("devices", deviceRepository.findAll());*/
		return "redirect:/pdModels/";
	}

	@RequestMapping(value="/",method=RequestMethod.POST)
	public String pdModelAdd(@RequestParam int last_fico_range_high, @RequestParam int last_fico_range_low,@RequestParam float revol_util, @RequestParam int inq_last_6mths, @RequestParam(value="is_rent", defaultValue="false")  boolean is_rent, double loan_amt,double annual_inc,Model model) {
		PDModel pdModel = new PDModel();
		pdModel.setLast_fico_range_high(last_fico_range_high);
		pdModel.setLast_fico_range_low(last_fico_range_low);
		pdModel.setRevol_util(revol_util);
		pdModel.setInq_last_6mths(inq_last_6mths);
		pdModel.setLoan_amt(loan_amt);
		pdModel.setAnnual_inc(annual_inc);
		if(is_rent)
		pdModel.setIs_rent(is_rent);
		else 
			pdModel.setIs_rent(false);	
			
		try {
			DominoService.getPBDetails(pdModel);
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repository.save(pdModel);

		
		/*model.addAttribute("pdModel", pdModel);
		model.addAttribute("devices", deviceRepository.findAll());*/
		return "redirect:/index/";
	}
}

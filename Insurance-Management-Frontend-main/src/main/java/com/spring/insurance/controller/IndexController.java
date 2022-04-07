package com.spring.insurance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	String aboutus = "";
	String products="";

		//About us tabs
	@RequestMapping("/history")
	public String history(Model m) {
		aboutus = "History Panel";
		m.addAttribute("tergate", aboutus);
		return "aboutus/history";
	}
	
	//company history
	@RequestMapping("/info")
	public String information(Model m) {
		aboutus = "Information Panel";
		m.addAttribute("tergate",aboutus);
		return "aboutus/history";
	}
	
	
	//company missin and vision
	@RequestMapping("/mision")
	public String mission(Model m) {
		aboutus = "Mission And Vision";
		m.addAttribute("tergate",aboutus);
		return "aboutus/history";
	}
	
	//conatact us
		@RequestMapping("/contact")
		public String Home(Model m) {
			aboutus = "Contact us";
			m.addAttribute("tergate",aboutus);
			return "aboutus/history";
		}
	
		//products tabs
	@RequestMapping("/life")
	public String Life(Model m) {
		this.products="Life Insurance";
		m.addAttribute("product",products);
		return"products/Products";
	}
	
	
	//abot health insurance
	@RequestMapping("/health")
	public String Health(Model m) {
		this.products="Health Insurance";
		m.addAttribute("product",products);
		return"products/Products";
	}
	
	
	//about Child insurance
	@RequestMapping("/child")
	public String Child(Model m) {
		this.products="Child Insurance";
		m.addAttribute("product",products);
		return"products/Products";
	}
	
	
	//about Hazz insurance
	@RequestMapping("/hazz")
	public String Hazz(Model m) {
		this.products="Hazz Insurance";
		m.addAttribute("product",products);
		return"products/Products";
	}
	
	
	//about Pension insurance
	@RequestMapping("/pention")
	public String Pention(Model m) {
		this.products="Pention Insurance";
		m.addAttribute("product",products);
		return"products/Products";
	}
	
	
	//about Education insurance
	@RequestMapping("/education")
	public String Education(Model m) {
		this.products="Education Insurance";
		m.addAttribute("product",products);
		return"products/Products";
	}
	
	
	//about Denmoahar insurance
	@RequestMapping("/mohar")
	public String FourPay(Model m) {
		this.products="Denmahar Insurance";
		m.addAttribute("product",products);
		return"products/Products";
	}
	
	@RequestMapping("/calculator")
	public String Calculator() {
		
		return"calculator";
	}
	

}
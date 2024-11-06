package com.jsfcourse.calc;

import java.io.Serializable;
import java.util.ResourceBundle;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredBB implements Serializable{
	private Double kwota;
	private Double oprocentowanie;
	private Double years;
	private Double rata;
	
	@Inject
	@ManagedProperty("#{txtCalcErr}")
	private ResourceBundle txtCalcErr;
	
	
	@Inject
	@ManagedProperty("#{txtCalc}")
	private ResourceBundle txtCalc;

	@Inject
	FacesContext ctx;
	

	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	public Double getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(Double oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public Double getYears() {
		return years;
	}

	public void setYears(Double years) {
		this.years = years;
	}

	public Double getRata() {
		return rata;
	}

	public void setRata(Double rata) {
		this.rata = rata;
	}

	public boolean doTheMath() {
		try {
			rata = (kwota+(kwota*oprocentowanie/100))/(years*12);
			rata = Math.round(rata * 100.0) / 100.0;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	public String calc() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + rata, null));
		}
		return null;
	}
}

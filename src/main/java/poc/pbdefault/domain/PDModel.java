package poc.pbdefault.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class PDModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	//@Size(min=2, max=30, message = "Username size should be in the range [2...30]")
	private int last_fico_range_high;
	private int last_fico_range_low;
	private double revol_util;
	private int inq_last_6mths	;
	private boolean is_rent;
	private String score;
	private String prob_default;
	private double loan_amt;
	private double annual_inc;
	
	public double getLoan_amt() {
		return loan_amt;
	}

	public void setLoan_amt(double loan_amt) {
		this.loan_amt = loan_amt;
	}

	public double getAnnual_inc() {
		return annual_inc;
	}

	public void setAnnual_inc(double annual_inc) {
		this.annual_inc = annual_inc;
	}




	
	public String getProb_default() {
		return prob_default;
	}

	public void setProb_default(String prob_default) {
		this.prob_default = prob_default;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@ManyToMany
	private List<Factors> factors;

	public PDModel() {
		super();
	}

	public PDModel(int last_fico_range_high, int last_fico_range_low, double  revol_util,int inq_last_6mths,boolean is_rent,double loan_amt,double annual_inc)
	{
		super();
		this.last_fico_range_high = last_fico_range_high;
		this.last_fico_range_low = last_fico_range_low;
		this.revol_util = revol_util;
		this.inq_last_6mths = inq_last_6mths;
		this.is_rent=is_rent;
		this.loan_amt = loan_amt;
		this.annual_inc = annual_inc;
	}

	
	public PDModel(int last_fico_range_high, int last_fico_range_low, double  revol_util,int inq_last_6mths,boolean is_rent,String score,String pd, double loan_amt,double annual_inc)
	{
		super();
		this.last_fico_range_high = last_fico_range_high;
		this.last_fico_range_low = last_fico_range_low;
		this.revol_util = revol_util;
		this.inq_last_6mths = inq_last_6mths;
		this.is_rent=is_rent;
		this.score=score;
		this.prob_default = pd;
		this.loan_amt = loan_amt;
		this.annual_inc = annual_inc;
	}


	public int getLast_fico_range_high() {
		return last_fico_range_high;
	}

	public void setLast_fico_range_high(int last_fico_range_high) {
		this.last_fico_range_high = last_fico_range_high;
	}

	public int getLast_fico_range_low() {
		return last_fico_range_low;
	}

	public void setLast_fico_range_low(int last_fico_range_low) {
		this.last_fico_range_low = last_fico_range_low;
	}

	public double getRevol_util() {
		return revol_util;
	}

	public void setRevol_util(double revol_util) {
		this.revol_util = revol_util;
	}

	public int getInq_last_6mths() {
		return inq_last_6mths;
	}

	public void setInq_last_6mths(int inq_last_6mths) {
		this.inq_last_6mths = inq_last_6mths;
	}

	public boolean isIs_rent() {
		return is_rent;
	}

	public void setIs_rent(boolean is_rent) {
		this.is_rent = is_rent;
	}

	public List<Factors> getFactors() {
		return factors;
	}

	public void setFactors(List<Factors> factors) {
		this.factors = factors;
	}

	/*public boolean hasDevice(Factors device) {
		for (Factors containedDevice: getDevices()) {
			if (containedDevice.getId() == device.getId()) {
				return true;
			}
		}
		return false;
	}*/

}
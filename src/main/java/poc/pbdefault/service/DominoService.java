package poc.pbdefault.service;
    import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import poc.pbdefault.domain.PDModel;
     
    public class DominoService {
/*  	public static void main(String[] args) throws UnirestException {
    	PDModel pdmodel = new PDModel(10,10,1.2,2,new Boolean(true),2000.0,2000.0);
    	getPBDetails(pdmodel);
    	}*/
        public static void getPBDetails(PDModel pdModel) throws UnirestException {
/*            HttpResponse<JsonNode> response = Unirest.post("https://app.dominodatalab.com/v1/kannu/iristest/endpoint")
                .header("X-Domino-Api-Key", "dnfc1TeQnffWAZHajlJNvmmJr8DMIXZVxIhWlUEMm9ZnTCe6d3qbhyhkBmMxRNET")
                .header("Content-Type", "application/json")
                .body(new JsonNode("{\"parameters\": [ 5.6, 3.2, 1.7,0.8]}"))
                .asJson();*/
        	String requestbody = "{\"parameters\": [" + pdModel.getLast_fico_range_high() +","+
					  pdModel.getLast_fico_range_low() +","+
					  pdModel.getRevol_util()+","+
					  pdModel.getInq_last_6mths() +","+
					  pdModel.isIs_rent() +","+
					  pdModel.getLoan_amt() +","+
					  pdModel.getAnnual_inc()+"]}";
        	
        	System.out.println("Request body is "  + requestbody);
/*            HttpResponse<JsonNode> response = Unirest.post("https://app.dominodatalab.com/v1/kannu/defaulter/endpoint")
                    .header("X-Domino-Api-Key", "dnfc1TeQnffWAZHajlJNvmmJr8DMIXZVxIhWlUEMm9ZnTCe6d3qbhyhkBmMxRNET")
                    .header("Content-Type", "application/json")
                    .body(new JsonNode(requestbody))
                    .asJson();*/
            HttpResponse<JsonNode> response = Unirest.post("https://trial.dominodatalab.com/v1/kartik/defaulter/endpoint")
                    .header("X-Domino-Api-Key", "iiaPA42kplRz0lG8TRDOTmgHLDThnU3gJsOAK6SLB8U68VWDeCg5U5PcoF7zz9R9")
                    .header("Content-Type", "application/json")
                    .body(new JsonNode(requestbody))
                    .asJson();
            //.body(new JsonNode("{\"parameters\": [0,759,755,83.7,1,TRUE]}"))
            //System.out.println(response.getStatus());
            //System.out.println(response.getHeaders());
            System.out.println(response.getBody().getObject().toString(2));
            
            String classification = response.getBody().getObject().getJSONArray("result").get(0).toString();
            String probability_default = response.getBody().getObject().getJSONArray("result").get(1).toString();

            System.out.println(classification);
            System.out.println(probability_default);
            pdModel.setScore(classification);
            pdModel.setProb_default(probability_default);
            
           // return pdModel;
            
        }
      
        
    }


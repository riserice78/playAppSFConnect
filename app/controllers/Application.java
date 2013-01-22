package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.AccountModel;
import play.data.Form;
import play.db.ebean.Model;
import service.ConnectionManager;

import java.util.ArrayList;
import java.util.List;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;

import static play.libs.Json.toJson;


public class Application extends Controller {
  private static ArrayList<AccountModel> amList;
	
  public static Result index() {
	  return ok(index.render("Please enter account name", form(AccountModel.class)));
//	  return ok(index.render("Please enter account name"));
  }

  public static Result searchAccount(){
	  Form<AccountModel> form = form(AccountModel.class).bindFromRequest();
  	
	  if(form.hasErrors()){
		  return badRequest(index.render("Please enter the account name", form));
//		  return badRequest(index.render("Please enter the account name"));
	  }else{
		  AccountModel account = form.get();
		  PartnerConnection connection = ConnectionManager.getConnectionManager().getConnection();
		  QueryResult result = null;
		  try {
			  System.out.println("before query : " + account.name);
			  result = connection.query("Select Id, Name, BillingCity, BillingState from Account where Name like '%" + account.name + "%'");
			  System.out.println("Records found for " + account.name +": "+result.getSize());
		  } catch (ConnectionException e) {
			  e.printStackTrace();
		  } catch (NullPointerException npe) {
			  System.out.println("NullPointerException: "+npe.getCause().toString());
		  }

		  SObject[] sObjList = result.getRecords();
		  amList = new ArrayList<AccountModel>();
		  for(int i=0; i<sObjList.length; i++){		
			SObject acc = sObjList[i];
			AccountModel am = new AccountModel();
			am.id = acc.getId();
			am.name = ""+acc.getField("Name");
			am.billingCity = ""+acc.getField("BillingCity");
			am.billingState = ""+acc.getField("BillingState");
			System.out.println("adding to array : " + am.id + " " + am.name + " of " + am.billingCity + ", " + am.billingState);
			amList.add(am);
		  }
	  }
  		return redirect(routes.Application.index());
	}
  
  public static Result getAccounts(){
	return ok(toJson(amList));
  }
  
}

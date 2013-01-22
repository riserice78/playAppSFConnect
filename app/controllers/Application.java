package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.AccountModel;
import models.UserAuth;
import play.data.DynamicForm;
import play.data.Form;
import service.ConnectionManager;

import java.util.ArrayList;
import java.util.List;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;

import static play.libs.Json.toJson;


public class Application extends Controller {

   public static Result login() {
	  return ok(login.render("Login Page"));
   }
   public static Result index2() {
   	return ok(index.render("Please enter account name", form(AccountModel.class)));	
   }
   
    public static Result index() {
	    //get form values
	    DynamicForm bindedForm = form().bindFromRequest();
	    //input login process here
		PartnerConnection connection = ConnectionManager.getConnectionManager(bindedForm.get("uname"), bindedForm.get("pword")).getConnection();
		//go to next page
		return ok(index.render("Please enter account name", form(AccountModel.class)));
    }

}

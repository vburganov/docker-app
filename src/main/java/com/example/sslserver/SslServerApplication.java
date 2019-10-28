package com.example.sslserver;

import java.util.Date;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	  TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
	      @Override
	      protected void postProcessContext(Context context) {
	        SecurityConstraint securityConstraint = new SecurityConstraint();
	        securityConstraint.setUserConstraint("CONFIDENTIAL");
	        SecurityCollection collection = new SecurityCollection();
	        collection.addPattern("/*");
	        securityConstraint.addCollection(collection);
	        context.addConstraint(securityConstraint);
	      }
	    };
	  
	  tomcat.addAdditionalTomcatConnectors(redirectConnector());
	  return tomcat;
	}

	private Connector redirectConnector() {
	  Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	  connector.setScheme("http");
	  connector.setPort(8099);
	  connector.setSecure(false);
	  connector.setRedirectPort(8443);
	  
	  return connector;
	}
}
@RestController
class SecuredServerController{

	public static Logger log = org.slf4j.LoggerFactory.getLogger(SecuredServerController.class);
	
	@RequestMapping("/secured")
	public String secured(){
		log.info("Inside secured()");
		return "Hello user !!! : " + new Date();
	}
	
	
	@RequestMapping(method=RequestMethod.POST, path = "/crmapi/rest/v2/authentication/token")
	public String getToken() throws InterruptedException {
		System.out.println("/crmapi/rest/v2/authentication/token");
		Thread.sleep(1000);
		return "{" + 
				"  \"data\": {" + 
				"    \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c3IiOiJNUEFkbWluaXN0cmF0b3IiLCJvcmciOiJldmIyY19yMTIiLCJvdW4iOiIxIiwiZXhwIjoiMTQ5NDMzNjc0OCIsImlhdCI6IjE0OTQzMjk1NDgiLCJqdGkiOiIwNTE5MDA4MDQ2MUU0RkMxQjQ3MTFBNDRFNEIzNUIxMSJ9.RO35RvA_XghX8SugPfQZMnAJvfIBtZm-OD9bQwNSOjc\"" + 
				"  }," + 
				"  \"status\": {" + 
				"    \"code\": \"OK\"," + 
				"    \"description\": \"\"," + 
				"    \"message\": \"\"" + 
				"  }" + 
				"}";
	}
	
	@RequestMapping(method=RequestMethod.POST, path = "/customer_events/purchases/create")
	public String purchaseCreate() throws InterruptedException {
		System.out.println("/customer_events/purchases/create");
		Thread.sleep(1000);
		return ""
				+"{                                                                                                                                       "
				+"  \"status\": {                                                                                                                        "
				+"    \"message\": \"&РћВ РџРѓРћС—РџС“РџР‚РћВ¬РћС‘РћВ·РџС“РћВµ РћС•РћВ±РћР…РћВ¬!\",                                                                                                                 "
				+"    \"description\": \"\",                                                                                                             "
				+"    \"code\": \"OK\"                                                                                                                   "
				+"  },                                                                                                                                   "
				+"  \"data\": {                                                                                                                          "
				+"    \"products_set\": [                                                                                                                "
				+"      {                                                                                                                                "
				+"        \"total_amount\": 60,                                                                                                          "
				+"        \"net_amount\": 50,                                                                                                            "
				+"        \"id\": \"BE28ECA140834D92B8E9038DE8A95FE8\",                                                                                  "
				+"        \"quantity\": 1,                                                                                                               "
				+"        \"vat_amount\": 10,                                                                                                            "
				+"        \"product\": {                                                                                                                 "
				+"          \"product_type\": {                                                                                                          "
				+"            \"service_type\": \"USAGE\",                                                                                               "
				+"            \"physical_good_type\": \"NONTRACEABLE\",                                                                                  "
				+"            \"used_for_provisioning\": true,                                                                                           "
				+"            \"classification\": \"SERVICES\",                                                                                          "
				+"            \"composition_method\": \"FLAT\",                                                                                          "
				+"            \"id\": \"78F758444F6B8437F1A27F61D1BE84A5\",                                                                              "
				+"            \"name\": \"VOD\",                                                                                                         "
				+"            \"description\": \"Video on Demand Movies\",                                                                               "
				+"            \"alternative_code\": \"VOD\"                                                                                              "
				+"          },                                                                                                                           "
				+"          \"id\": \"02192DC60AF606C2124118AABCBFAAAA\",                                                                                "
				+"          \"description\": \"DEFAULT_PRODUCT\",                                                                                        "
				+"          \"code\": \"DEFAULT_PRODUCT\",                                                                                               "
				+"          \"alternative_code\": \"SRDP\"                                                                                               "
				+"        }                                                                                                                              "
				+"      }                                                                                                                                "
				+"    ],"
				+"    \"soundable_transaction\" : 1,"                                                                                                                           
				+"    \"id\": \"01B0FD5A49814677BBF15662482C39FE\",                                                                                      "
				+"    \"number\": \"CE12345\",                                                                                                           "
				+"    \"udf_float_1\": 1,                                                                                                                "
				+"    \"non_winning_message\": \"<p>Try Again!!@#$%^&amp;*(). РџвЂљРћВµРџРѓРџвЂћРџвЂ¦РћС‘Рћв„–РћС—РџР‚РћВ±Сљ!<\\/p>\","
				+"    \"total_awarded_amount\": 0.75,                                                                                                    "
				+"    \"total_spend_amount\": 0,                                                                                                         "
				+"    \"external_system_reference_number\": null,                                                                                        "
				+"    \"award_reward_transactions_set\": [                                                                                               "
				+"      {                                                                                                                                "
				+"        \"life_cycle_state\": \"POSTED\",                                                                                              "
				+"        \"amount\": 0.5,                                                                                                                 "
				+"        \"rewards_participant\": {                                                                                                     "
				+"          \"accounts_receivable\": {                                                                                                   "
				+"            \"account_owner\": {                                                                                                       "
				+"              \"last_name\": \"Kestora\",                                                                                              "
				+"              \"middle_name\": null,                                                                                                   "
				+"              \"life_cycle_state\": \"FINANCIAL\",                                                                                     "
				+"              \"company_profile\": null,                                                                                               "
				+"              \"title\": null,                                                                                                         "
				+"              \"first_name\": \"Polina\",                                                                                              "
				+"              \"type\": \"PERSON\",                                                                                                    "
				+"              \"id\": \"F038161F36C845B98CEE7361A8C92157\",                                                                            "
				+"              \"name\": null,                                                                                                          "
				+"              \"company_name\": null,                                                                                                  "
				+"              \"demographics\": {                                                                                                      "
				+"                \"gender\": \"FEMALE\",                                                                                                "
				+"                \"passport_issued_by_country\": null,                                                                                  "
				+"                \"social_security_number\": null,                                                                                      "
				+"                \"passport_number\": null,                                                                                             "
				+"                \"industry_sector\": null,                                                                                             "
				+"                \"name_day\": {                                                                                                        "
				+"                  \"month\": null,                                                                                                     "
				+"                  \"day\": null                                                                                                        "
				+"                },                                                                                                                     "
				+"                \"date_of_birth\": {                                                                                                   "
				+"                  \"month\": 3,                                                                                                        "
				+"                  \"day\": 4,                                                                                                          "
				+"                  \"year\": 1988                                                                                                       "
				+"                },                                                                                                                     "
				+"                \"id_number\": \"112233\",                                                                                             "
				+"                \"industry\": null,                                                                                                    "
				+"                \"id_issued_by_country\": null                                                                                         "
				+"              }                                                                                                                        "
				+"            },                                                                                                                         "
				+"            \"life_cycle_state\": \"ACTIVE\",                                                                                          "
				+"            \"number\": \"ACR0000000164\",                                                                                             "
				+"            \"id\": \"C6CBAC948E53498F83FF52E6CD8B7221\",                                                                              "
				+"            \"name\": \"ACR0000000164 Polina Kestora\"                                                                                 "
				+"          },                                                                                                                           "
				+"          \"number\": \"RP0000000019\",                                                                                                "
				+"          \"id\": \"EA95AA28BE1F4C8B9C983487278210D3\"                                                                                 "
				+"        },                                                                                                                             "
				+"        \"number\": \"1178\",                                                                                                          "
				+"        \"id\": \"9C0A9B29A07B42CDBFA0279B6D4FAB37\"                                                                                   "
				+"      }                                                                                                                                "
				+"    ]                                                                                                                                  "
				+"  }                                                                                                                                    "
				+"}                                                                                                                                      "
				;
	}
	
	@RequestMapping(method=RequestMethod.GET, path = "/reward_transactions/awards/show")
	public String show(@RequestParam Map<String,String> allParams) throws InterruptedException {
		System.out.println("/reward_transactions/awards/show");
		Thread.sleep(1000);
		return ""
				+"{                                                                                                                                         "
				+"  \"status\": {                                                                                                                           "
				+"    \"message\": \"\",                                                                                                                    "
				+"    \"description\": \"\",                                                                                                                "
				+"    \"code\": \"OK\"                                                                                                                      "
				+"  },                                                                                                                                      "
				+"  \"data\": {                                                                                                                             "
				+"    \"award_expiration_transaction\": {                                                                                                   "
				+"      \"life_cycle_state\": \"POSTED\",                                                                                                   "
				+"      \"amount\": 16.5,                                                                                                                   "
				+"      \"rewards_participant\": {                                                                                                          "
				+"        \"accounts_receivable\": {                                                                                                        "
				+"          \"life_cycle_state\": \"ACTIVE\",                                                                                               "
				+"          \"number\": \"ACR000334\",                                                                                                      "
				+"          \"id\": \"086FB2E9A502EF1C3E023C07AE3D7795\",                                                                                   "
				+"          \"name\": \"Georgia Rewards\"                                                                                                   "
				+"        },                                                                                                                                "
				+"        \"number\": \"14\",                                                                                                               "
				+"        \"id\": \"B6BA1AD810A52569932F9065A5CA43D9\"                                                                                      "
				+"      },                                                                                                                                  "
				+"      \"number\": \"14\",                                                                                                                 "
				+"      \"id\": \"3A6E1BD5D6A74FE58D0A94A78F4766CF\"                                                                                        "
				+"    },                                                                                                                                    "
				+"    \"reward_offer\": {                                                                                                                   "
				+"      \"life_cycle_state\": \"NOT_EFFECTIVE\",                                                                                            "
				+"      \"number\": \"13\",                                                                                                                 "
				+"      \"id\": \"3213950603C66B555958B9E482E04F92\",                                                                                       "
				+"      \"name\": \"10% cashback (on each purchased item) on customers purchasing SedShoesor SedShirts costing over 100 euros each\",       "
				+"      \"description\": \"10% cashback (on each purchased item) on customers purchasingSedShoes or SedShirts costing over 100 euros each\","
				+"      \"alternative_code\": \"1C(EPIOCPSOSCO1EE\",                                                                                         "
				+"\"marketing_information_short_description\": \"<p>You are the winner! О•О№ПѓО±О№ Ої ОЅО№ОєО·П„О®П‚!</p>\""
				+"    },                                                                                                                                    "
				+"    \"customer_events_set\": [                                                                                                            "
				+"      {                                                                                                                                   "
				+"        \"life_cycle_state\": \"POSTED\",                                                                                                 "
				+"        \"classification\": null,                                                                                                         "
				+"        \"number\": \"306\",                                                                                                              "
				+"        \"type\": \"PURCHASE\",                                                                                                           "
				+"        \"id\": \"67BE5F0C77D445F9A5459015E48AED50\"                                                                                      "
				+"      }                                                                                                                                   "
				+"    ],                                                                                                                                    "
				+"    \"voucher\": {                                                                                                                        "
				+"      \"id\": \"3213950603C66B555958B9E482E04F92\",                                                                                       "
				+"      \"value\": 20.1,"
				+"      \"type\" : {"
				+"		\"description\":\" ОЅО№ОєО·П„О® ОЅО№ОєО·П„О® ОЅО№ОєО·П„О® ОЅО№ОєО·П„О® ОЅО№ОєО·П„О® ОЅО№ОєО·П„О®\""
				+ "		}                                                                                                                 "
				+"    },                                                                                                                                    "
				+"    \"promotional_voucher_text\": \"The promotional voucher text found in the new customfield of the reward offer in html format\",       "
				+"    \"winning_message\": \"the winning message from Short Description of the reward offermarketing information in html format\"           "
				+"  }                                                                                                                                       "
				+"}                                                                                                                                         "
				;
	}
}


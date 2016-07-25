package com.bridgelabz.dao;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.PlayerModel;
import com.bridgelabz.model.TeamModel;
@Repository
public class IplDaoImpl implements IplDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public Session session;
	
	// Searching Technique
	
		public  ArrayList<?> jsonsearch(String fieldname,String text) 
	  	{
		    new PlayerModel();
		    Session session = sessionFactory.openSession();
			boolean listFound = false;
			Query query;
			String SQL_QUERY =" from PlayerModel as o where o."+fieldname+" LIKE "+ "'%"+text+"%'";
			query = session.createQuery(SQL_QUERY);
			List<?> list = query.list();
			System.out.println("list is :"+list);
			if ((list != null) && (list.size() > 0)) 
			{
				listFound= true;
				System.out.println(listFound);
			}
			session.close();
			return (ArrayList<?>) list;     
		  
	  	}
		
		
		//Sorting Technique
		
		public  List<?> jsonsorting(String field) 
	  	{
		  new PlayerModel();
		  Session session = sessionFactory.openSession();
		  String SQL_QUERY =" from PlayerModel ORDER BY "+field;
		  Query query = session.createQuery(SQL_QUERY);
		  System.out.println("Query executing :"+query);
	      List<?> list = query.list();
	      System.out.println("list is :"+list);
			if ((list != null) && (list.size() > 0)) 
				{
				///
				}
				session.close();
				return list;     
		  
		  	}
		
		
		// Sorting By field name and user VALUE and  
		 public  List<?> jsonsearch(String fieldname,String text,String sortby) 
		  	{
			    new PlayerModel();
			    Session session = sessionFactory.openSession();
				boolean listFound = false;
				Query query;
				if(sortby==null)
				{
					String SQL_QUERY1 =" from PlayerModel as o where o."+fieldname+" LIKE "+ "'%"+text+"%'";
					query = session.createQuery(SQL_QUERY1);
				}
				else
				{
				String SQL_QUERY =" from PlayerModel as o where o."+fieldname+" LIKE "+ "'%"+text+"%'"+" ORDER BY "+sortby;
				query = session.createQuery(SQL_QUERY);
				}
				
		         List<?> list = query.list();
				if ((list != null) && (list.size() > 0)) 
				{
					
					listFound= true;
					System.out.println(listFound);
				}
				session.close();
				return list;     
			  
		  	}

		 //////////////////PlayerModel/////////////
					
			@SuppressWarnings("unchecked")
			public ArrayList<PlayerModel> iplplayergenerate(String ipl) {
				
				Long count=0l;
				session = sessionFactory.openSession();
				String hql_query="select count(team) from PlayerModel  as o where o.team=?";
				Query query = session.createQuery(hql_query);
				query.setParameter(0, ipl);
				count = (Long)query.uniqueResult();
					if (count!=0) 
					{	System.out.println("count not = o");
						String hql="from PlayerModel as o where o.team=?";
				        query = session.createQuery(hql);
				        query.setParameter(0, ipl);
				        System.out.println("where clause executing");
				        @SuppressWarnings("rawtypes")
						List list1 = query.list();
				        System.out.println(list1);
						if ((list1 != null) && (list1.size() > 0)) 
						{
							System.out.println("List Not Null and Size Greater Than Zero");
							return (ArrayList<PlayerModel>) list1;
							
						}
					}
				else
				
				{
					
			ArrayList<PlayerModel> arraylist=new ArrayList<PlayerModel>();
			JSONParser parser= new JSONParser();
				        
			try {
				 String filepath = "/home/bridgelabz/Documents/iplapplication2016/src/main/jsonfile/IPLPlayer/"+ipl+".json";
				 FileReader fileread=new FileReader(filepath);
				        	
				 Object obj=parser.parse(fileread);
				 JSONArray array =(JSONArray)obj;
				        	
				for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject=(JSONObject)array.get(i);
				JSONArray jsonArray=(JSONArray)jsonObject.get(ipl);
								
				for (int j = 0; j < jsonArray.size(); j++) {
					PlayerModel player=new PlayerModel();
					JSONObject jsonObjectArray=(JSONObject)jsonArray.get(j);
					player.setTeam(jsonObjectArray.get("player_teamname").toString());
					player.setName(jsonObjectArray.get("player_name").toString());
					player.setRole(jsonObjectArray.get("player_role").toString());
					player.setBatting(jsonObjectArray.get("player_batting_style").toString());
					player.setBowler(jsonObjectArray.get("player_bowling_style").toString());
					player.setNation(jsonObjectArray.get("player_nationality").toString());
					player.setDob(stringToDate(jsonObjectArray.get("player_dob").toString()));
					arraylist.add(player);
			
					try{
							
						//////////save  with transaction in the database////////////
						
							System.out.println("save Block");
							Session session = sessionFactory.openSession();
							Transaction tx1 = session.beginTransaction();
							session.save(player);
							System.out.println("Saved data");
							tx1.commit();
							}catch(Exception e){
							e.printStackTrace();
									}
				
								}
				
				        	}
				        }
						catch (Exception e){
										
										e.printStackTrace();
											}
			
				        	String hql="from PlayerModel as o where o.team=?";
					        query = session.createQuery(hql);
					        query.setParameter(0, ipl);
							@SuppressWarnings("rawtypes")
							List list1 = query.list();
					        System.out.println("list is :"+list1);
							if ((list1 != null) && (list1.size() > 0)) 
							{
							
								return (ArrayList<PlayerModel>) list1;
								
							}
							session.close();
							
							}
							
					return null;
							
				}	
		

///////////////////////////////// String to Date////////////////////////////////////
			
			public Date stringToDate(String str){
			DateFormat f = new SimpleDateFormat("dd MMMM yyyy");
    	    try {
    	    	
			return f.parse(str);
			
    	    } catch (java.text.ParseException e) {
			
			e.printStackTrace();
    	    }
    	    return null;
			}

	
/////////////////////////////TeamModel///////////////////////////////////////
			
			@SuppressWarnings("unchecked")
			public ArrayList<TeamModel> iplteamgenerate(String iplteam) {
				
				{
					Long count=0l;
					session = sessionFactory.openSession();
					String hql_query="select count(tname) from TeamModel  as o where o.tname=?";
					Query query = session.createQuery(hql_query);
					query.setParameter(0, iplteam);
					count = (Long)query.uniqueResult();
						if (count!=0) 
						{
							System.out.println("count not Equal = 0");
							String hql="from TeamModel as o where o.tname=?";
					        query = session.createQuery(hql);
					        query.setParameter(0,iplteam);
					        @SuppressWarnings("rawtypes")
							List list1 = query.list();
							if ((list1 != null) && (list1.size() > 0)) 
							{	
								System.out.println("list not Equal to Null");
								return (ArrayList<TeamModel>) list1;
								
							}
						}
						
						
			else{
				ArrayList<TeamModel> arraylist = new ArrayList<TeamModel>();
				JSONParser parser = new JSONParser();

			try {
				String file = "/home/bridgelabz/Pictures/iplapplication2016/src/main/jsonfile/IPLTeam/"+iplteam+".json";
				FileReader fileread = new FileReader(file);
				Object obj = parser.parse(fileread);
				JSONArray array = (JSONArray) obj;

			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject = (JSONObject) array.get(i);
				String name = jsonObject.get("Name").toString();
				System.out.println("json  name"+name);
				JSONArray jsonArray = (JSONArray) jsonObject.get(iplteam);
			
			for (int j = 0; j < jsonArray.size(); j++) {
				System.out.println("inner for ");
				TeamModel teammodel = new TeamModel();
				JSONObject jsonObjectArray = (JSONObject) jsonArray.get(j);
				teammodel.setName(jsonObjectArray.get("team_name").toString());
				teammodel.setCouch(jsonObjectArray.get("team_coach").toString());
				teammodel.setCaption(jsonObjectArray.get("team_captain").toString());
				teammodel.setVeneue(jsonObjectArray.get("team_home_venue").toString());
				teammodel.setOwner(jsonObjectArray.get("team_owner").toString());
				arraylist.add(teammodel);
				//save  with transaction
				System.out.println("Entered in try block of save");
				Session session = sessionFactory.openSession();
				Transaction tx1 = session.beginTransaction();
				session.save(teammodel);
				System.out.println("Saved data");
				tx1.commit();
			}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		String hql="from TeamModel as o where o.tname=?";
        Query q = session.createQuery(hql);
        q.setParameter(0, iplteam);
        @SuppressWarnings("rawtypes")
		List list = q.list();
		if ((list != null) && (list.size() > 0)) 
		{
			return (ArrayList<TeamModel>)list;
			
		}
		
		session.close();
			
		}
				
		return null;
				
				
		}
				
	}


}
	

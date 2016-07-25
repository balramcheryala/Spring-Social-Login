package com.bridgelabz.ipl.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.ipl.database.model.PlayerModel;
import com.bridgelabz.ipl.database.model.TeamModel;

public interface IplDao {
	public  List<?> jsonsorting(String field) ;
	public  ArrayList<?>jsonsearch(String fieldname,String text);
	public ArrayList<PlayerModel> iplplayergenerate(String ipl);
	public ArrayList<TeamModel> iplteamgenerate(String iplteam);
}

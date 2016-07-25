package com.bridgelabz.dao;

import java.util.ArrayList;
import java.util.List;

public interface IplDao {
	public  List<?> jsonsorting(String field) ;
	public  ArrayList<?>jsonsearch(String fieldname,String text);
}

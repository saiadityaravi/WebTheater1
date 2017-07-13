package com.sa.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.sa.DAO.TheaterDAO;

@Controller
public class Operations {

	@RequestMapping(value="/admin")
	public String adminHandler(){
		return "admin";
	}
	@RequestMapping(value="/createTheater")
	public ModelAndView createTheater(HttpServletRequest req, HttpServletResponse resp){
		String name =(String)req.getParameter("thearename");
		String address= (String)req.getParameter("address");
		int screens=Integer.parseInt(req.getParameter("screens"));
		int rows=Integer.parseInt(req.getParameter("rows"));
		String movie[] ={"mummy", "wonderWomen"}; 
		int seats=Integer.parseInt(req.getParameter("seats"));
		ModelAndView mv = new ModelAndView("admin");
		int i=TheaterDAO.createTheater(name, address);
		if(i==1){
			for (int j = 1; j < screens+1; j++) {
				TheaterDAO.createScreen(j, rows, seats, movie[j-1]);
			}
		}else {
			req.setAttribute("Message", "Error in creating Theater");
		}
		req.setAttribute("Message", "Theater with name '"+name+"' has been created with '"+screens+"' screens with rows '"+rows+"'");
		return mv;

	}
	
	@RequestMapping(value="customer")
	public ModelAndView bookTickets(){
		List<screen> screens = new ArrayList<screen>();
		ModelAndView mv = new ModelAndView("customer");
		ResultSet rs = TheaterDAO.getTheater();
		ResultSet rs1 = TheaterDAO.getScreenList();
		screen screen= null;
		try {
			while (rs.next()) {
				mv.addObject("name", rs.getString(2));
				mv.addObject("address",rs.getString(3));
			}
			
			while (rs1.next()) {
				screen = new screen();
				screen.setId(rs1.getInt(1));
				screen.setCols(rs1.getInt(3));
				screen.setRows(rs1.getInt(2));
				screen.setScreen(rs1.getString(4));
				screens.add(screen);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("screen", screens);
		return mv;
	}
	
	@RequestMapping(value="select")
	public ModelAndView select(HttpServletRequest req, HttpServletRequest resp){
		List<Seats> seats = new ArrayList<Seats>();
		int screenSelected = Integer.parseInt((String)req.getParameter("movie"));
		ModelAndView mv = new ModelAndView("select");
		mv.addObject("selectedscreen", screenSelected);
		int rows=TheaterDAO.getCount(screenSelected);
		ResultSet rs=TheaterDAO.getSeats(screenSelected);
		Seats seat = null;
		try {
			while (rs.next()) {
				seat = new Seats();
				seat.setRow(rs.getString(1));
				seat.setReserved(rs.getBoolean(2));
				seats.add(seat);
				System.out.println(seat.isReserved());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("rows",rows);
		mv.addObject("seats",seats);
		return mv;
	}
	@RequestMapping(value="confirmation", method=RequestMethod.POST)
	public ModelAndView confirmation(HttpServletRequest req, HttpServletRequest resp){
		String selected[]=req.getParameterValues("selected");
		int count = selected.length;
		for (int i = 0; i < selected.length; i++) {
			TheaterDAO.bookSeats(Integer.parseInt((String)req.getParameter("screen_id")), selected[i].trim());
		}
		ModelAndView mv = new ModelAndView("confirmation");
		mv.addObject("screen",Integer.parseInt((String)req.getParameter("screen_id")));
		mv.addObject("count" ,selected.length);
		mv.addObject("selected",selected);
		return mv;
	}
	
}

/*Leslie Ducray
 * 2014
 * The System servlet handles all requests regarding the update or addition of Substances and streams at a system level. most of these requests will originate from "systemAdmin.jsp"
 */
package web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.StreamBean;
import model.beans.SubstanceBean;
import model.data.cmsQuerySubstance;
import model.data.cmsStreamQuery;

/**
Leslie Ducray - 2014
handles request to update streams, update substances and add new substances from the web application.
 */

public class SystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
RequestDispatcher 	requestDispatch;
	
	
	private enum validActions
	{
		init,substanceUpdate, updateStreams, newSubstance;
	}
    
    public SystemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination	= "/systemAdmin.jsp";
		String jsp_path	= "/WEB-INF/jsp";
		String userMessage	= null;
		HttpSession session = request.getSession();  
		
		if (request.getRequestedSessionId().equals(session.getId()))
		{  
		    
		   	String logUserName 	= session.getAttribute("username").toString();
			
			String action= request.getParameter("requestAction");	
				
				
			switch (validActions.valueOf(action))
			{
				case init:
					request.setAttribute("substanceBeans",cmsQuerySubstance.qrySubstances(null,"N"));
					request.setAttribute("streams",cmsStreamQuery.qryStreams());
					break;
					
				case substanceUpdate:
					
				
					List<SubstanceBean> substances = cmsQuerySubstance.qrySubstances(null,"N");
					List<SubstanceBean> newValues = new  ArrayList<SubstanceBean>();//empty list to be populated by each substance's new values (some values may not have been changed, they are updated anyway!)
					for(int i =0; i< substances.size(); i++)// for each existing substance
					{
						
							SubstanceBean newSubValues = new SubstanceBean();
							newSubValues.setSubstance(substances.get(i).getSubstance());
							if(request.getParameter("subActive"+substances.get(i).getSubstance())==null)
							{
								String active = "N";
								newSubValues.setActive(active);
							}
							else
							{
							newSubValues.setActive(request.getParameter("subActive"+substances.get(i).getSubstance()));
							}
							newSubValues.setMaxValue(request.getParameter("maxVal"+substances.get(i).getSubstance()));
							newSubValues.setResetValue(request.getParameter("resetVal"+substances.get(i).getSubstance()));
							if(request.getParameter("regFlag"+substances.get(i).getSubstance())==null)
							{
								String reg = "N";
								newSubValues.setStreamRegressionFlag(reg);
							}
							else
							{
							newSubValues.setStreamRegressionFlag(request.getParameter("regFlag"+substances.get(i).getSubstance()));
							}
							newSubValues.setUpdatedBy(request.getParameter("username"));
							newValues.add(newSubValues);
						
					}
					userMessage = cmsQuerySubstance.updateSubstances(newValues);
					request.setAttribute("userMsg", userMessage);
					request.setAttribute("substanceBeans",cmsQuerySubstance.qrySubstances(null,"N"));
					request.setAttribute("streams",cmsStreamQuery.qryStreams());
					break;
					
				case updateStreams:
					
					List<StreamBean> streams = cmsStreamQuery.qryStreams();
					List<StreamBean> newVals = new  ArrayList<StreamBean>();
					for(int i =0; i< streams.size(); i++)
					{
							StreamBean newStreamValues = new StreamBean();
							newStreamValues.setStreamId(request.getParameter(streams.get(i).getStreamId()).toString());
							newStreamValues.setPointConversion(Float.parseFloat(request.getParameter("point"+newStreamValues.getStreamId()).toString()));
							newStreamValues.setMaxPoints(Integer.parseInt(request.getParameter("max"+newStreamValues.getStreamId()).toString()));
							newStreamValues.setUpdateBy(request.getParameter("username"));
							newVals.add(newStreamValues);
					}
					userMessage = cmsStreamQuery.updateStreams(newVals);
					request.setAttribute("substanceBeans",cmsQuerySubstance.qrySubstances(null,"N"));
					request.setAttribute("streams",cmsStreamQuery.qryStreams());
					request.setAttribute("userMsg2", userMessage);
					break;
					
				case newSubstance:
					
					SubstanceBean newSub = new SubstanceBean();
					newSub.setSubstance(request.getParameter("newSubstance"));
					if(request.getParameter("subactive") != null)
					{
						newSub.setActive(request.getParameter("subactive"));
					}
					else
					{
						newSub.setActive("N");
					}
					newSub.setMaxValue(request.getParameter("submax"));
					newSub.setResetValue(request.getParameter("subreset"));
					
					if(request.getParameter("regFlag")!=null)
					{
						newSub.setStreamRegressionFlag(request.getParameter("regFlag"));
					}
					else
					{
						newSub.setStreamRegressionFlag("N");
					}
					newSub.setUpdatedBy(request.getParameter("username"));
					
					userMessage = cmsQuerySubstance.newSubstance(newSub);
					request.setAttribute("substanceBeans",cmsQuerySubstance.qrySubstances(null,"N"));
					request.setAttribute("streams",cmsStreamQuery.qryStreams());
					request.setAttribute("userMsg", userMessage);
					break;
			
			
			}
			
			
			request.setAttribute("username", logUserName);
			requestDispatch = request.getRequestDispatcher(jsp_path+destination);
			requestDispatch.forward(request,response);
			
		}
		
		else
		{
			response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
			response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
			session = request.getSession(true);
			session.removeAttribute("userDetails");
			session.removeAttribute("userprofession");
			session.removeAttribute("username");
			session.removeAttribute("userAdmin");
			session.removeAttribute("userAccountant");
			session.removeAttribute("userReports");
								
			session.invalidate();
			jsp_path	= "";
			destination= "/index.jsp";	
			userMessage= "Session Timed Out";
			request.setAttribute("userMessage", userMessage);
			requestDispatch = request.getRequestDispatcher(jsp_path+destination);
			requestDispatch.forward(request,response);
		}
		
	}

	
}

package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCouponManager;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.service.TLxReceiveRedManager;
import com.rc.portal.service.TLxRedShareManager;
import com.rc.portal.service.TLxSendRedManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TLxReceiveRed;
import com.rc.portal.vo.TLxReceiveRedExample;
import com.rc.portal.vo.TLxRedShare;
import com.rc.portal.vo.TLxRedShareExample;
import com.rc.portal.vo.TLxSendRed;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TSysParameter;
import com.rc.portal.vo.TSysParameterExample;

public class LXRedPacketAction extends BaseAction {
	
	private TLxRedShareManager tlxredsharemanager;
	private OpenSqlManage opensqlmanage;
	private TLeaderManager tleadermanager;
	private TLxSendRedManager tlxsendredmanager;
	private TLxReceiveRedManager tlxreceiveredmanager;
	private TSysParameterManager tsysparametermanager;
	private TCouponManager tcouponmanager;
	
	
	public static void main(String[] args) {
		
		int len = 5;
		Random random = new Random();
		
		for(int i=0;i<100;i++){
			System.out.print(random.nextInt(len)+" ");
		}
	}
	

	
	
	/**
	 * 分享红包
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public String share() throws SQLException, IOException{
		
		TLxRedShareExample example = new TLxRedShareExample();
		example.createCriteria().andWeightEqualTo(1).andStatusEqualTo(1).andIsDeleteEqualTo(0);
		List<TLxRedShare> list = tlxredsharemanager.selectByExample(example);
		TLxRedShare tLxRedShare = null;
		if(list.size()>0)
		{
			tLxRedShare = list.get(0);
		}
		
		this.getRequest().setAttribute("red", tLxRedShare);
		
		long leaderId = 0;
		String lcode = "0";
		TMember tmember = (TMember) this.getSession().getAttribute("member");
		if(tmember == null){
			getResponse().sendRedirect("/login/login!index.action");
			return null;
		}
		
		
		
		System.out.println("tmember="+tmember);
		TLeaderExample tmemberexample = new TLeaderExample();
		tmemberexample.createCriteria().andMemberIdEqualTo(tmember.getId());
		List<TLeader> l = tleadermanager.selectByExample(tmemberexample);
		boolean isshare = false;
		if(l.size()>0)
		{
			TLeader leader = l.get(0);
			leaderId = leader.getId();
//			isshare = iShare(leader.getId());
			lcode = leader.getCode();
		}
		
		System.out.println("---------->"+tLxRedShare.getShareImageUrl());
		
		this.getRequest().setAttribute("ishare",isshare+"");
		this.getRequest().setAttribute("leaderid", leaderId);
		this.getRequest().setAttribute("lcode", lcode);
		this.getRequest().setAttribute("ico", tLxRedShare.getShareImageUrl());
		
		return "share";
	}
	
	
	
	public boolean iShare(long leaderid) throws SQLException{
		// 是否分享过
		boolean ishare = false;
		Map map = new HashMap();
		map.put("leaderid", leaderid);
		Integer cnt = (Integer)opensqlmanage.selectForObjectByMap(map, "t_lx_send_red.today_is_send");
		if(cnt>0){	// 今天已经发过
			ishare = true;
		}
		return ishare;
	}
	
	
	public long createRedPacket(String leaderId) throws NumberFormatException, SQLException{
		long redId = 0;
		boolean b= iShare(Long.parseLong(leaderId));
		if(b){
			Map map = new HashMap();
			map.put("leaderid", Long.parseLong(leaderId));
			redId = (Long)opensqlmanage.selectForObjectByMap(map, "t_lx_send_red.today_lx_red");
		}else{
			TLxSendRed red = new TLxSendRed();
			red.setRelationId(Long.parseLong(leaderId));
			red.setSendDt(new Date());
			red.setRedType(1);
			red.setCentent("");
			redId = tlxsendredmanager.insertSelective(red);
		}
		
		
		return redId;
	}
	
	/**
	 * 发放红包
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void sendRedPacket() throws SQLException, IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		String leaderId = this.getRequest().getParameter("leaderid");
		
		long redId = createRedPacket(leaderId);	// 红包ID
		
		
		String value = getConponByRed("leader_red_packet");
		long coupon = 0;
		if(value!=null)
		{
			 coupon = Long.parseLong(value); // 红包中优惠券的ID
			
		}
		
		TCoupon tcou = tcouponmanager.selectByPrimaryKey(coupon);
		
		System.out.println("coupon------->"+coupon+"----"+tcou.getDisPrice().intValue());
		
		
		
		out.print(redId+"_"+coupon+"_"+tcou.getDisPrice().intValue());
		out.close();
		
	}
	
	public void isHaved() throws IOException, NumberFormatException, SQLException{
		PrintWriter out = this.getResponse().getWriter();
		String leaderId = this.getRequest().getParameter("leaderid");
		long redid = getTodayRedByLeader(leaderId);
		TLxReceiveRedExample example = new  TLxReceiveRedExample();
		example.createCriteria().andRedIdEqualTo(redid);
		List list = tlxreceiveredmanager.selectByExample(example);
		out.print(list.size());
		out.close();
	}
	
	public void receiveRedPacket() throws SQLException, IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		long code = 0;
		String redid = this.getRequest().getParameter("redid");
		String mobile = this.getRequest().getParameter("mobile");
		TMember member = (TMember)this.getSession().getAttribute("member");
		TLxReceiveRedExample example = new  TLxReceiveRedExample();
		example.createCriteria().andRedIdEqualTo(member.getId());
		List list = tlxreceiveredmanager.selectByExample(example);
		if(list.size() >= 10){	// 红包已经领完
			code = -1;
		}else{
			example.clear();
			example.createCriteria().andRedIdEqualTo(Long.parseLong(redid)).andMemberIdEqualTo(member.getId());
			List l = tlxreceiveredmanager.selectByExample(example);
			if(l.size()>0){	// 已领过
				code = -2;
			}else{
				
				String value = getConponByRed("leader_red_packet");
				if(value!=null)
				{
					TLxReceiveRed record = new TLxReceiveRed();
					record.setRedId(Long.parseLong(redid));
					record.setMemberId(member.getId());
					record.setReceiveDt(new Date());
					long coupon = Long.parseLong(value);
					record.setCouponCardId(coupon);
					record.setMobile(mobile);
					Long id = tlxreceiveredmanager.insertSelective(record);
					code = id;
					
				}else{	// 没有找到优惠券ID
					code = -3;
				}
				
			}
		}
		out.print(code);
		out.close();
		
	}
	
	
	public void isGetToday() throws IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		String packetId = this.getRequest().getParameter("rid");
		String username = this.getRequest().getParameter("mobile");
		Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("redId", packetId);
		countMap.put("mobile", username);
		Integer count = (Integer) opensqlmanage.selectObjectByObject(countMap, "t_member.select_mobile_count");
		out.print(count);
		out.close();
	}
	
	public long getTodayRedByLeader(String leaderId){
		Map map = new HashMap();
		map.put("leaderid", Long.parseLong(leaderId));
		long redId =0;
		Object o  = opensqlmanage.selectForObjectByMap(map, "t_lx_send_red.today_lx_red");
		if(o!=null) redId = (Long)o;
		return redId;
	}
	
	public String getConponByRed(String key) throws SQLException{
		
		String value = null;
		TSysParameterExample example = new TSysParameterExample();
		example.createCriteria().andSysKeyEqualTo(key);
		List<TSysParameter> list = tsysparametermanager.selectByExample(example);
		if(list.size()==0){
			return value;
		}
		String systemValue = list.get(0).getSysValue();
		String[] array = systemValue.split(",");
		int r = new Random().nextInt(array.length);
		return array[r];
	}
	

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}

	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
	}

	public TLxSendRedManager getTlxsendredmanager() {
		return tlxsendredmanager;
	}

	public void setTlxsendredmanager(TLxSendRedManager tlxsendredmanager) {
		this.tlxsendredmanager = tlxsendredmanager;
	}

	public TLxReceiveRedManager getTlxreceiveredmanager() {
		return tlxreceiveredmanager;
	}

	public void setTlxreceiveredmanager(TLxReceiveRedManager tlxreceiveredmanager) {
		this.tlxreceiveredmanager = tlxreceiveredmanager;
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

	public TLxRedShareManager getTlxredsharemanager() {
		return tlxredsharemanager;
	}



	public void setTlxredsharemanager(TLxRedShareManager tlxredsharemanager) {
		this.tlxredsharemanager = tlxredsharemanager;
	}


	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub

	}




	public TCouponManager getTcouponmanager() {
		return tcouponmanager;
	}




	public void setTcouponmanager(TCouponManager tcouponmanager) {
		this.tcouponmanager = tcouponmanager;
	}
	
	

	
}

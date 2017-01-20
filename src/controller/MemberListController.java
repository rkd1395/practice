package controller;

import java.util.Map;

import dao.MemberDao;

public class MemberListController implements Controller {

	@Override
	public String excute(Map<String, Object> map) throws Exception {
		MemberDao memberDao = (MemberDao)map.get("memberDao");
		try {
			map.put("members", memberDao.selectList());
		} catch (Exception e) {
			map.put("error", e);
			return "/Error.jsp";
		}
		return "/member/MemberList.jsp";
	}
	

}

package com.members.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class MemberService {

	private MembersDAO_interface dao;

	public MemberService() {
		// TODO Auto-generated constructor stub
		dao = new MembersDAO();
	}

	public MembersVO addMember(String name, String password, String email, byte[] thumbnail) {
		MembersVO membersVO = new MembersVO();
		membersVO.setName(name);
		membersVO.setPassword(password);
		membersVO.setEmail(email);
		membersVO.setThumbnail(thumbnail);
		dao.insert(membersVO);
		return membersVO;
	}

	public MembersVO updateMembers(Integer memberId, String name, String phone, Integer membership, Integer memberStatus,
			byte[] thumbnail, String address) {
		MembersVO membersVO = new MembersVO();
		membersVO.setName(name);
		membersVO.setAddress(address);
		membersVO.setMembership(membership);
		membersVO.setMemberStatus(memberStatus);
		membersVO.setPhone(phone);
		membersVO.setThumbnail(thumbnail);
		membersVO.setMemberId(memberId);
		dao.update(membersVO);
		return membersVO;
	}
	
	public void updatePassword(String password, Integer memberId ) {
		MembersVO membersVO = new MembersVO();
		membersVO.setMemberId(memberId);
		membersVO.setPassword(password);
		dao.updatePassword(membersVO);
	}

	public MembersVO findByPrimaryKey(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}
	
	public MembersVO findByEmail(String email) {
		return dao.findByEmail(email);
	}

	public List<MembersVO> getAll() {
		if (dao.getAll() != null) {
			return dao.getAll();			
		} else {
			System.out.println("¨ú­È¥X²{¿ù»~");
			return null;
		}
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}

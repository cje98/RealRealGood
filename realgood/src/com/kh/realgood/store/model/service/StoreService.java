package com.kh.realgood.store.model.service;

import static com.kh.realgood.common.DBCP.getConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.kh.realgood.store.model.dao.StoreDAO;
import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.dto.StoreImg;
import com.kh.realgood.store.model.dto.StoreInfoMenu;

public class StoreService {
	private StoreDAO dao;
	
	public StoreService() throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		dao = new StoreDAO();
	}
	public List<Store> searchAddr(String group, String addr) throws Exception {
		
		Connection conn = getConnection();
		// dao 호출
		List<Store> searchAddr = dao.searchAddr(conn, group, addr);
		
		// 커넥션 반환
		conn.close();
		
		return searchAddr;
	}
	/** 이미지 찾기
	 * @param addrArr
	 * @return
	 */
	public List<StoreImg> searchStoreImg(List<Store> addrArr) throws Exception {
		Connection conn = getConnection();
		
		List<StoreImg> imgList = dao.searchStoreImg(conn, addrArr);
		
		conn.close();
		
		return imgList;
	}
	/** 관리자 - 가게 조회
	 * @return list
	 */
	public List<Store> selectStoreList() throws Exception {
		Connection conn = getConnection();
		
		List<Store> list = dao.selectStoreList(conn);
		
		conn.close();
		return list;
	}
	/** 관리자 - 가게 승인
	 * @param noArr
	 * @return result
	 * @throws Exception
	 */
	public int adminStore(String[] noArr) throws Exception{
		Connection conn = getConnection();
		
		int result = 0;
		
		for(String no : noArr) {
			
			result = dao.selectStoreImg(no,conn);
			
			if(result > 0){
				result = dao.adminStoreImg(no, conn);
			}
			result = dao.adminStore(no, conn);
			
			if(result == 0) break;
			
			
		}
		
		if(result > 0) conn.commit();
		else conn.rollback();
		
		conn.close();
		return result;
	}
	/** 관리자 - 가게 승인 내역 확인
	 * @param id
	 * @param storeNum 
	 * @return selectStore
	 * @throws Exception
	 */
	public StoreInfoMenu selectStore(String id) throws Exception{
		Connection conn = getConnection();
		
		StoreInfoMenu selectStore = dao.selectStore(id, conn);
		
		conn.close();
		return selectStore;
	}
	/** 가게 전체 조회
	 * @param addr
	 * @return sList
	 * @throws Exception
	 */
	public List<Store> searchStore(String addr) throws Exception{
		Connection conn = getConnection();
		
		List<Store> sList = dao.searchStore(conn, addr);
		
		conn.close();
		return sList;
	}
	/** 메뉴 조회
	 * @param storeNo
	 * @return mList
	 * @throws Exception
	 */
	public List<StoreInfoMenu> searchMenu(int storeNo) throws Exception{
		Connection conn = getConnection();
		
		List<StoreInfoMenu> mList = dao.searchMenu(conn, storeNo);
		
		
		conn.close();
		return mList;
	}
}

package com.kh.realgood.store.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.realgood.member.model.dao.MemberDAO;
import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.dto.StoreImg;
import com.kh.realgood.store.model.dto.StoreInfoMenu;

public class StoreDAO {
	private Properties prop = null;

	public StoreDAO() throws FileNotFoundException, IOException {
		String fileName = MemberDAO.class.getResource("/com/kh/realgood/sql/store/store.properties").getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));
	}

	public List<Store> searchAddr(Connection conn, String group, String addr) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Store> list = null;
		String query = prop.getProperty("searchAddr");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, addr);
			pstmt.setString(2, group);

			rset = pstmt.executeQuery();
			Store store = null;
			list = new ArrayList<Store>();
			while (rset.next()) {
				store = new Store(rset.getInt("STORE_NUM"), rset.getString("COR_NUM"), rset.getString("NAME"),
						rset.getString("STORE_CONTENT"), rset.getString("STORE_TEL"), rset.getString("GROUP_NAME"),
						rset.getString("STORE_ADDR"), rset.getString("STORE_ZIP"), rset.getDate("ENROLL_DATE"));
				store.setStoreTitleImg(storeTitleImg(conn, store.getStoreNum()));
				list.add(store);
			}

		} finally {
			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
		}

		return list;
	}



	/** 가게 대표이미지 설정
	 * @param storeNum
	 * @return
	 */
	private String storeTitleImg(Connection conn, int storeNum) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String titleImg = null;
		String query = prop.getProperty("searchStoreTitleImg");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNum);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				titleImg = rset.getString(1);
			} else {
				titleImg = "storeBaseImg.png";
			}
		} finally {
			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
		}
		
		return titleImg;
	}

	public List<StoreImg> searchStoreImg(Connection conn, List<Store> addrArr) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<StoreImg> imgList = null;
		String query = prop.getProperty("searchStoreImg");
		try {
			for (int i = 0; i < addrArr.size(); i++) {
				Store store = addrArr.get(i);

				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, store.getStoreNum());

				rset = pstmt.executeQuery();
				StoreImg storeImg = null;
				if(i == 0) imgList = new ArrayList<StoreImg>();
				while (rset.next()) {
					storeImg = new StoreImg(rset.getInt("S_IMG_NUM"), rset.getInt("S_STORE_NUM"), rset.getString("S_OIMG_NAME"),
							rset.getString("S_RIMG_NAME"), rset.getDate("S_ENROLL_DATE"));
					imgList.add(storeImg);
				}
			}
		} finally {
			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
		}

		return imgList;
	}

	/** 관리자 - 가게 정보 조회용 DAO
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Store> selectStoreList(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		Store store = null;
		List<Store> list = null;
		
		String query = prop.getProperty("selectStoreList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Store>();
			while(rset.next()) {
				store = new Store(rset.getInt("STORE_NUM"),rset.getString("S_NAME"), rset.getString("ID"), rset.getString("USE_YN").charAt(0));
				list.add(store);
			}
			
		} finally {
			rset.close();
			stmt.close();
		}
		
		return list;
	}

	/** 관리자 - 가게 승인
	 * @param no
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int adminStore(String no, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("adminStore");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, no);
			
			result = pstmt.executeUpdate();
			
		} finally {
			pstmt.close();
		}
		
		
		return result;
	}

	/** 관리자 - 가게 승인 내역 확인
	 * @param id
	 * @param storeNum 
	 * @param conn
	 * @return selectStore
	 * @throws Exception
	 */
	public StoreInfoMenu selectStore(String id, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		StoreInfoMenu selectStore = null;
		
		String query = prop.getProperty("selectStore");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				selectStore = new StoreInfoMenu(
						rset.getString("NAME"), 
						rset.getString("STORE_CONTENT"), 
						rset.getString("STORE_TEL"), 
						rset.getString("STORE_ADDR"),
						rset.getString("MENU_NAME"), 
						rset.getString("MENU_MAKE_TIME"), 
						rset.getString("MENU_CONTENT"), 
						rset.getInt("PRICE"), 
						rset.getDate("ENROLL_DATE"), 
						id
						);
			}
		} finally {
			rset.close();
			pstmt.close();
		}
		
		return selectStore;
	}

	/** 가게 전체 조회
	 * @param conn
	 * @param addr
	 * @return sList
	 * @throws Exception
	 */
	public List<Store> searchStore(Connection conn, String addr) throws Exception{
		PreparedStatement pstmt = null;
		List<Store> sList = null;
		ResultSet rset = null;
		Store store = null;
		
		String query = prop.getProperty("searchStore");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, addr);
			
			rset = pstmt.executeQuery();
			sList = new ArrayList<Store>();
			while(rset.next()) {
				store = new Store(
						rset.getInt("STORE_NUM"), 
						rset.getString("NAME"),
						rset.getString("GROUP_NAME"));
				sList.add(store);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		
		
		return sList;
	}

	/** 메뉴 조회
	 * @param conn
	 * @param storeNo
	 * @return menu
	 * @throws Exception
	 */
	public List<StoreInfoMenu> searchMenu(Connection conn, int storeNo) throws Exception{
		PreparedStatement pstmt = null;
		List<StoreInfoMenu> mList = null;
		StoreInfoMenu menu = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("searchMenu");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNo);
			
			rset = pstmt.executeQuery();
			
			mList = new ArrayList<StoreInfoMenu>();
			while(rset.next()) {
				menu = new StoreInfoMenu(rset.getInt("MENU_NUM"), rset.getString("MENU_NAME"), rset.getString("MENU_MAKE_TIME"), rset.getString("MENU_CONTENT"), rset.getInt("PRICE"));
				mList.add(menu);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		
		
		
		return mList;
	}

	/** 관리자 - 가게 이미지 사용 승인
	 * @param no
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int adminStoreImg(String no, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("adminStoreImg");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, no);
			
			result = pstmt.executeUpdate();
			
		} finally {
		
			pstmt.close();
		}
		
		
		return result;
	}

	/** 관리자 - 가게 이미지 존재 확인
	 * @param no
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int selectStoreImg(String no, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("selectStoreImg");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		
		}
		
		
		return result;
	}
}

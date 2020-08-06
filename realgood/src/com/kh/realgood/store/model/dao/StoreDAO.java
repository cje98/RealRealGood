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
import com.kh.realgood.store.model.dto.StoreMenu;
import com.kh.realgood.store.model.vo.PageInfo;

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
	
	/** 가게 정보 등록 dao
	 * @param conn
	 * @param storeInfo
	 * @return result
	 * @throws Exception
	 */
	public int storeInsert(Connection conn, Store storeInfo, String id) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
			
		String query = prop.getProperty("insertInfo");
		

		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, storeInfo.getCorNum());
			pstmt.setString(2, storeInfo.getStoreName());
			pstmt.setString(3, storeInfo.getStoreContent());
			pstmt.setString(4, storeInfo.getStoreTel());
			pstmt.setString(5, storeInfo.getStoreGroupName());
			pstmt.setString(6, storeInfo.getStoreAddress());
			pstmt.setString(7, storeInfo.getStoreZip());
			pstmt.setString(8, id);
			
			
			
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		return result;
	}

	/** 이미지 삽입용 dao
	 * @param conn
	 * @param image1
	 * @return result2
	 * @throws Exception
	 */
	public int imgInsert(Connection conn, StoreImg st, int result) throws Exception{
		PreparedStatement pstmt = null;
		int result2 = 0;
		
		String query = prop.getProperty("imgInsert");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, result);
			pstmt.setString(2, st.getOriginImgName());
			pstmt.setString(3, st.getRealImgName());
			pstmt.setString(4, st.getFilePath());
			pstmt.setInt(5, st.getFileLevel());
			pstmt.setString(6, st.getFileStatus());
			
			result2 = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		return result2;
	}

	

	/** 메뉴 삽입 DAO
	 * @param conn
	 * @param storeMenu
	 * @return result3
	 * @throws Exception
	 */
	public int menuInsert(Connection conn, StoreMenu storeMenu) throws Exception{
		PreparedStatement pstmt = null;
		int result3 = 0;
		
		String query = prop.getProperty("menuInsert");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, storeMenu.getMenuName());
			pstmt.setInt(2, storeMenu.getPrice());
			pstmt.setInt(3, storeMenu.getStoreNo());
			pstmt.setString(4, storeMenu.getMenuContents());
			pstmt.setString(5, storeMenu.getMenuMakeTime());
			
			result3 = pstmt.executeUpdate();
		
			
			
			
		}finally {
			pstmt.close();
		}
		return result3;
	}

	/** 현재 시퀀스값 가져오기 dao
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int storeSelectNo(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		
		int result = 0;
		String query = prop.getProperty("storeSelectNo");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
			
		} finally {
			rset.close();
			stmt.close();
		}
		return result;
	}

	/** 가게 번호에 따라 이미지 불러오는 DAO
	 * @param conn
	 * @param storeNo
	 * @return storeImg
	 * @throws Exception
	 */
	public List<StoreImg> selectImg(Connection conn, int storeNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<StoreImg> storeImg = null;
		
		String query = prop.getProperty("selectImg");
			
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNo);
			
			rset = pstmt.executeQuery();
			
			storeImg = new ArrayList<StoreImg>();
			StoreImg img = null;
			
			while(rset.next()) {
				img = new StoreImg(rset.getString("S_RIMG_NAME"),
								   rset.getInt("S_FILEP_LEVEL"));
				storeImg.add(img);
			}
			
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		
		return storeImg;
	}

	/** 가게 번호로 메뉴 불러오기 DAO
	 * @param conn
	 * @param storeNo
	 * @return storeMenu
	 */
	public List<StoreMenu> selectMenu(Connection conn, int storeNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<StoreMenu> storeMenu = null;
		
		String query = prop.getProperty("selectMenu");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNo);
			
			rset = pstmt.executeQuery();
			
			storeMenu = new ArrayList<StoreMenu>();
			StoreMenu menu = null;
			while(rset.next()) {
				menu = new StoreMenu(rset.getInt("MENU_NUM"),
						rset.getString("MENU_NAME"),
						rset.getInt("PRICE"),
						rset.getString("MENU_CONTENT"),
						rset.getString("MENU_MAKE_TIME"));
				
				storeMenu.add(menu);
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return storeMenu;
	}

	/** 가게 기본정보 수정 DAO
	 * @param storeNo
	 * @param store
	 * @return result
	 * @throws Exception
	 */
	public int updateStore(int storeNo, Store store, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateStoreInfo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, store.getStoreContent());
			pstmt.setString(2, store.getStoreTel());
			pstmt.setString(3, store.getStoreGroupName());
			pstmt.setString(4, store.getStoreZip());
			pstmt.setString(5, store.getStoreAddress());
			pstmt.setInt(6, storeNo);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			pstmt.close();
		}
		return result;
	}

	/** 가게 이미지 수정 DAO
	 * @param storeNo
	 * @param fList
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateImg(int storeNo, List<StoreImg> fList, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateStoreImg");
		
		try {
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}

	/** 기존 이미지 파일들 조회 DAO
	 * @param conn
	 * @param storeNo
	 * @return oldImg
	 * @throws Exception
	 */
	public List<StoreImg> selectOldFiles(Connection conn, int storeNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<StoreImg> oldImg = null;
		StoreImg img = null;
		
		String query = prop.getProperty("selectFiles");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNo);
			
			rset = pstmt.executeQuery();
			
			oldImg = new ArrayList<StoreImg>();
			while(rset.next()) {
				img = new StoreImg(rset.getInt("S_IMG_NUM"),
						rset.getString("S_RIMG_NAME"),
						rset.getString("S_FILE_PATH"),
						rset.getInt("S_FILEP_LEVEL"));
				
				oldImg.add(img);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		return oldImg;
	}

	/** 가게 이미지 수정(update) DAO
	 * @param conn
	 * @param newFiles
	 * @return return
	 * @throws Exception
	 */
	public int updateStoreImg(Connection conn, StoreImg newFiles) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateStoreImg");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newFiles.getOriginImgName());
			pstmt.setString(2, newFiles.getRealImgName());
			pstmt.setString(3, newFiles.getFilePath());
			pstmt.setInt(4, newFiles.getFileLevel());
			pstmt.setInt(5, newFiles.getStoreImgNum());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		return result;
	}
	
	/** 가게 메뉴 중복 체크 DAO
	 * @param menuName
	 * @param storeNo
	 * @return result
	 * @throws Exception
	 */
	public int checkMenu(String menuName, int storeNo, Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("checkMenu");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, storeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		}finally {
			rset.close();
			pstmt.close();
			
		}
		return result;
	}

	

	/** 특정 가게에 메뉴추가
	 * @param conn
	 * @param storeMenu
	 * @param storeNo
	 * @return
	 * @throws Exception
	 */
	public int menuInsert(Connection conn, StoreMenu storeMenu, int storeNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("menuInsert");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, storeMenu.getMenuName());
			pstmt.setInt(2, storeMenu.getPrice());
			pstmt.setInt(3, storeNo);
			pstmt.setString(4, storeMenu.getMenuContents());
			pstmt.setString(5, storeMenu.getMenuMakeTime());
			
			result = pstmt.executeUpdate();
		
			
			
			
		}finally {
			pstmt.close();
		}
		return result;
	}

	/** 특정 번호 메뉴 삭제용 DAO
	 * @param conn
	 * @param menuNum
	 * @return result
	 * @throws Exception
	 */
	public int deleteMenu(Connection conn, int menuNum) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMenu");
			
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, menuNum);
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		return result;
	}
	
	/** 게시글 목록 조회
	 * @param conn
	 * @param pInfo
	 * @return
	 * @throws Exception
	 */
	public List<Store> selectList(Connection conn, PageInfo pInfo)throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Store> storeList = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			
			int startRow = (pInfo.getCurrentPage()-1) * pInfo.getLimit()+1;
			
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pInfo.getAddress());
			pstmt.setString(2, pInfo.getGroup());
			pstmt.setInt(3, startRow );
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();
			Store store = null;
			storeList = new ArrayList<Store>();
			
			
			while(rset.next()) {
				store = new Store(rset.getInt("STORE_NUM"),
						rset.getString("COR_NUM"),
						rset.getString("NAME"),
						rset.getString("STORE_CONTENT"),
						rset.getString("STORE_TEL"),
						rset.getString("GROUP_NAME"),
						rset.getString("STORE_ADDR"),
						rset.getString("STORE_ZIP"),
						rset.getDate("ENROLL_DATE"));
					
				storeList.add(store);
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return storeList;
	}
	
	/** 전체 게시글 수 조회
	 * @param conn
	 * @param group
	 * @param addr
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, String group, String addr) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int listCount = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, addr);
			pstmt.setString(2, group);
			rset = pstmt.executeQuery();
			
			if(rset.next()) listCount = rset.getInt(1);
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return listCount;
	}

	public List<Store> selectListImg(Connection conn, List<Store> storeList) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Store> newStoreList = null;
		
		String query = prop.getProperty("searchStoreTitleImg");
		for (int i = 0; i < storeList.size(); i++) {
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, storeList.get(i).getStoreNum());
	
				rset = pstmt.executeQuery();
				if (rset.next()) {
					storeList.get(i).setStoreTitleImg(rset.getString(1));
				} else {
					storeList.get(i).setStoreTitleImg("storeBaseImg.png");
				}
			} finally {
				rset.close();
				pstmt.close();
			}
		}
		return storeList;
	}
	
	
	/** 가게 이미지 수정 중 추가 DAO (쿼리문 file-status default -> 'Y'로 변경)
	 * @param conn
	 * @param newFiles
	 * @param storeNo
	 * @return 
	 * @throws Exception
	 */
	public int imgInsert2(Connection conn, StoreImg newFiles, int storeNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("imgInsert2");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNo);
			pstmt.setString(2, newFiles.getOriginImgName());
			pstmt.setString(3, newFiles.getRealImgName());
			pstmt.setString(4, newFiles.getFilePath());
			pstmt.setInt(5, newFiles.getFileLevel());
			
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		return result;
	}

	/** 즐겨찾기 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param memberNum 
	 * @return 
	 * @throws Exception
	 */
	public List<Store> myBookmark(Connection conn, PageInfo pInfo, int memberNum) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Store> storeList = null;
		
		String query = prop.getProperty("myBookmark");
		
		try {
			
			int startRow = (pInfo.getCurrentPage()-1) * pInfo.getLimit()+1;
			
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNum);
			rset = pstmt.executeQuery();
			Store store = null;
			storeList = new ArrayList<Store>();
			
			
			while(rset.next()) {
				store = new Store(rset.getInt("STORE_NUM"),
						rset.getString("COR_NUM"),
						rset.getString("NAME"),
						rset.getString("STORE_CONTENT"),
						rset.getString("STORE_TEL"),
						rset.getString("GROUP_NAME"),
						rset.getString("STORE_ADDR"),
						rset.getString("STORE_ZIP"),
						rset.getDate("ENROLL_DATE"));
					
				storeList.add(store);
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return storeList;
	}
	
	/** 조회 결과 가게 평점 출력용 메소드
	    * @param conn
	    * @param storeList
	    * @return
	    */
	   public List<Store> storeGpaScore(Connection conn, List<Store> storeList) throws Exception {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      
	      List<Store> newStoreList = null;
	      
	      String query = prop.getProperty("storeGpaScore");
	      for (int i = 0; i < storeList.size(); i++) {
	         try {
	            pstmt = conn.prepareStatement(query);
	            pstmt.setInt(1, storeList.get(i).getStoreNum());
	   
	            rset = pstmt.executeQuery();
	            if (rset.next()) {
	               storeList.get(i).setStoreGpaScore(rset.getDouble(1));
	            } else {
	               storeList.get(i).setStoreGpaScore(0);
	            }
	            
	         } finally {
	            rset.close();
	            pstmt.close();
	         }
	      }
	      return storeList;
	   }
	   
	   
	/** 즐겨찾기 여부 확인 DAO
	 * @param conn
	 * @param storeNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int checkStar(Connection conn, int storeNo, int memberNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("checkStar");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNo);
			pstmt.setInt(2, memberNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		return result;
	}
	
	
	/** 상세페이지 가게 정보
	 * @param conn
	 * @param storeNum
	 * @return
	 * @throws Exception
	 */
	public StoreInfoMenu storeInfoList(Connection conn, int storeNum) throws Exception {

		PreparedStatement pstmt = null;
		StoreInfoMenu storeInfo = null;
		ResultSet rset = null;
		
		
		String query = prop.getProperty("storeInfoList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				storeInfo = new StoreInfoMenu(rset.getString("STORE_ADDR"),
						rset.getString("STORE_TEL"),
						rset.getString("GROUP_NAME"),
						rset.getInt("MIN_PRICE"),
						rset.getInt("MAX_PRICE"),
						rset.getString("NAME"),
						rset.getString("STORE_CONTENT"),
						rset.getInt("S_IMG_NUM"));
						
			}

		} finally {
			rset.close();
			pstmt.close();
		}
			
		
		
		
		return storeInfo;
	}

	/** 상세페이지 사진
	 * @param conn
	 * @param storeNo
	 * @return
	 * @throws Exception
	 */
	public List<StoreImg> storeImgList(Connection conn, int storeNo) throws Exception {

		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
	    List<StoreImg> storeImgList = null;
	      
	    String query = prop.getProperty("storeImgList");
	    
	    try {
	    	
	    	pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storeNo);
			rset = pstmt.executeQuery();
			StoreImg storeImg = null;
			storeImgList = new ArrayList<StoreImg>();
			
			
			while(rset.next()) {
				storeImg = new StoreImg(rset.getInt("S_IMG_NUM"),
								rset.getString("S_RIMG_NAME"),
								rset.getInt("S_FILEP_LEVEL"),
								rset.getString("S_FILE_PATH"));
					
				storeImgList.add(storeImg);
			}
	    	
	    	
	    }finally {
			rset.close();
			pstmt.close();
		}
	    
		
		return storeImgList;
	}

}

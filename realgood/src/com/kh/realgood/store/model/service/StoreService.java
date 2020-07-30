package com.kh.realgood.store.model.service;

import static com.kh.realgood.common.DBCP.getConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.realgood.store.model.dao.StoreDAO;
import com.kh.realgood.store.model.dto.Store;
import com.kh.realgood.store.model.dto.StoreImg;
import com.kh.realgood.store.model.dto.StoreInfoMenu;
import com.kh.realgood.store.model.dto.StoreMenu;

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
	 * @param idArr
	 * @return result
	 * @throws Exception
	 */
	public int adminStore(String[] idArr) throws Exception{
		Connection conn = getConnection();
		
		int result = 0;
		
		for(String id : idArr) {
			
			result = dao.adminStore(id, conn);
			
			if(result == 0) break;
			
		}
		
		
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
	
	//-----영인
	
	/** 가게 정보 등록하기 service
	 * @param storeInfo
	 * @return result
	 * @throws Exception
	 */
	public int storeInsert(Store storeInfo, String id) throws Exception{
		Connection conn = getConnection();
		
		
		storeInfo.setStoreContent(replaceParameter(storeInfo.getStoreContent()));// 크로스 사이트 스크립팅 방지
		storeInfo.setStoreContent(storeInfo.getStoreContent().replace("\r\n", "<br>")); // 개행문자 처리
		
		
		int result = dao.storeInsert(conn, storeInfo, id);
	
		
		if(result > 0) {
			result = dao.storeSelectNo(conn);
			conn.commit();
		}else {
			conn.rollback();
		}
	
		
		conn.close();
		return result;
	}
	
	private String replaceParameter(String param) {
	    String result = param;
	    if(param != null) {
	       result = result.replaceAll("&", "&amp;");
	       result = result.replaceAll("<", "&lt;");
	       result = result.replaceAll(">", "&gt;");
	       result = result.replaceAll("\"", "&quot;");
	    }
	    
	    return result;
	 }
	
	/** 이미지 삽입용 service
	 * @param image1
	 * @return result2
	 * @throws Exception
	 */
	public int imgInsert(List<StoreImg> fList, int result) throws Exception{
		Connection conn = getConnection();
		
		int result2 = 0;
		for(StoreImg st : fList) {
			st.setStoreNum(result);
			result2 = dao.imgInsert(conn, st, result);
		}
		
		
		if(result2 > 0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		
		conn.close();
		
		return result2;
	}
	
	
	/** 메뉴 추가용 service
	 * @param list
	 * @return result3
	 * @throws Exception
	 */
	public int menuInsert(List<StoreMenu> list2) throws Exception{
		Connection conn = getConnection();
		int result3 = 0;
		for(int i=0; i<list2.size(); i++) {
			result3 = dao.menuInsert(conn, list2.get(i));
			
			if(result3 == 0) {
				break;
			}
		}
		
		if(result3 > 0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		
		conn.close();
		
		return result3;
	}
	
	
	/** 가게 번호에 따라 이미지 불러오는 Service
	 * @param storeNo
	 * @return storeImg
	 * @throws Exception
	 */
	public List<StoreImg> selectImg(int storeNo) throws Exception{
		Connection conn = getConnection();
		
		List<StoreImg> storeImg = dao.selectImg(conn, storeNo);
		
		
		conn.close();
		
		return storeImg;
	}
	
	
	/** 가게 번호로 메뉴 불러오기 Service
	 * @param storeNo
	 * @return storeMenu
	 * @throws Exception
	 */
	public List<StoreMenu> selectMenu(int storeNo) throws Exception{
		Connection conn = getConnection();
		
		List<StoreMenu> storeMenu = dao.selectMenu(conn, storeNo);
		
		conn.close();
		
		return storeMenu;
		
		
	
		
	}
	
	/** 기본정보, 이미지, 메뉴 수정 Service
	 * @param store
	 * @param fList
	 * @param menu
	 * @return result
	 * @throws Exception
	 */
	public int updateStore(Store store, List<StoreImg> fList, List<StoreMenu> menu, int storeNo) throws Exception {
		Connection conn = getConnection();
		
		store.setStoreContent(replaceParameter(store.getStoreContent()));
		store.setStoreContent(store.getStoreContent().replace("\r\n", "<br>"));
		
		// 가게 기본정보 수정
		int result = dao.updateStore(storeNo, store, conn);
		
		// 가게 이미지 수정
		// 서버에서 삭제되어야할 파일을 모아둘 변수 선언
		List<StoreImg> deleteImg = new ArrayList<StoreImg>();
		if(result > 0) {
			result = 0; // 재사용
			// 기존 이미지파일들 가져오기
			List<StoreImg> oldImg = dao.selectOldFiles(conn, storeNo);
			boolean flag = false;
			for(StoreImg newFiles : fList) {
				// 새로운 파일 목록의 요소에 순차적으로 접근
				flag = false;
				for(StoreImg oldFiles : oldImg) {
					//기존 파일 목록의 요소에 순차적으로 접근
					if(newFiles.getFileLevel() == oldFiles.getFileLevel()) {
						// 새로운 파일의 레발과 기존 파일의 레벨이 같다면(중복)
						flag = true;
						deleteImg.add(oldFiles); // 중복되는 기존 파일을 삭제 리스트에 추가
						newFiles.setStoreImgNum(oldFiles.getStoreImgNum());
						
					}
				}
				
				// flag 상태에 따라 알맞은 dao 호출
				if(flag) { // 같은 레벨에 기존 파일이 있는 경우 update
					result = dao.updateStoreImg(conn, newFiles);
				}else {
					result = dao.imgInsert(conn, newFiles, storeNo);
				}
				
				if(result == 0) break;
			}
		}
		List<StoreImg> tempList = null;
		
		if(result > 0) {
			conn.commit();
			tempList = deleteImg;
			
		}else {
			conn.rollback();
			tempList = fList;
		}
		
		// 서버에 저장된 파일 삭제
		for(StoreImg st : tempList) {
			String filePath = st.getFilePath();
			String fileName = st.getRealImgName();
			
			File deleteFile = new File(filePath + fileName);
			deleteFile.delete();
		}
		
		// 새로운 메뉴 추가하기
		for(StoreMenu storeMenu : menu) {
			
			result = dao.checkMenu(storeMenu.getMenuName(), storeNo, conn);
			
			// 메뉴가 중복이아니라면
			if(result == 0) {
				result = dao.menuInsert(conn, storeMenu, storeNo);
			}else {
				continue;
			}
			
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}
		
		conn.close();
		
		return result;
	}
	
	
	/** 특정번호 이미지 삭제용 Service
	 * @param menuNum
	 * @return result
	 * @throws Exception
	 */
	public int deleteMenu(int menuNum) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteMenu(conn, menuNum);
		
		if(result > 0) conn.commit();
		else		   conn.rollback();
		
		conn.close();
		return result;
	}
	
	
}

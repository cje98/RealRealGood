package com.kh.realgood.store.model.vo;

public class PageInfo {

	private int currentPage; 	//현재 페이지 번호
	private int listCount;   	//전체 게시글의 
	private int limit = 10;       	//한 페이지에 보여질 게시글의 수
	private int pagingBarSize = 10;  // 화면에 표시될 페이징바의 페이지 개수
	
	private int maxPage;		//전체 페이지 중 제일 마지막 페이지
	private int startPage;		//페이징 시작 페이지 번호
	private int endPage;		//페이징 끝 페이지 번호
	
	
	private String group;
	private String address;
	
	
	
	
	
	
	
	


	
	public PageInfo(int currentPage, int listCount, String address,String group ) {
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.group = group;
		this.address = address;
		
		makePageInfo();
		
	}

	public PageInfo(int currentPage, int listCount, int limit, int pagingBarSize, String address,String group) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.limit = limit;
		this.pagingBarSize = pagingBarSize;
		this.group = group;
		this.address = address;
		
		
		makePageInfo();
	}
	
	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		makePageInfo();
	}

	/**
	 * @return the listCount
	 */
	public int getListCount() {
		return listCount;
	}

	/**
	 * @param listCount the listCount to set
	 */
	public void setListCount(int listCount) {
		this.listCount = listCount;
		
		makePageInfo();
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
		
		makePageInfo();
	}

	/**
	 * @return the pagingBarSize
	 */
	public int getPagingBarSize() {
		return pagingBarSize;
	}

	/**
	 * @param pagingBarSize the pagingBarSize to set
	 */
	public void setPagingBarSize(int pagingBarSize) {
		this.pagingBarSize = pagingBarSize;
		makePageInfo();
	}

	/**
	 * @return the maxPage
	 */
	public int getMaxPage() {
		return maxPage;
	}

	/**
	 * @param maxPage the maxPage to set
	 */
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
		
		makePageInfo();
	}

	/**
	 * @return the startPage
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * @return the endPage
	 */
	public int getEndPage() {
		return endPage;
	}
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
		makePageInfo();

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		makePageInfo();

	}

	
	
	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pagingBarSize=" + pagingBarSize + ", maxPage=" + maxPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", group=" + group + ", address=" + address + "]";
	}
	

	// 페이징 처리에 필요한 값을 계산하는 메소드
	private void makePageInfo() {
		
		// maxpage : 가장 마지막 == 총 페이지 수
		// limit가 10일 때
		// 게시글이 100개 일 경우 필요한 페이지의 수? 10p
		// 게시글이 101개 일 경우 필요한 페이지의 수? 11p
		// 전체 게시글 수 / 한 페이지에 보여질 게시글 수 --> 올림처리
		maxPage = (int)Math.ceil((double)listCount / limit);
		
		// startPage : 페이징바 시작 페이지 번호
		// 페이징바의 크기가 10인경우 시작 페이지 번호는
		// 1, 11, 21, 31, ...
		startPage = (currentPage-1) / pagingBarSize * pagingBarSize +1;
		
		//endPage : 페이징바 끝 페이지 번호
		// 페이징바의 크기가 10인경우 끝 페이지 번호는
		//10, 20, 30, 40 ...
		endPage = startPage + pagingBarSize-1 ;
		
		//endPage가 maxPage보다 클경우
		if(endPage > maxPage) {
			endPage = maxPage;
		}else {
		endPage = startPage + pagingBarSize - 1;
	}
	

  }
}
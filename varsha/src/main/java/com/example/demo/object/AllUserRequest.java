package com.example.demo.object;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AllUserRequest {
	private Integer pageNumber;
	private Integer pageSize;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String[] sortByCol;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String sortByDirection;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String filterBy;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String filterValue;
	
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	public String getFilterBy() {
		return filterBy;
	}
	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String[] getSortByCol() {
		return sortByCol;
	}
	public void setSortByCol(String[] sort) {
		this.sortByCol = sort;
	}
	public String getSortByDirection() {
		return sortByDirection;
	}
	public void setSortByDirection(String sortByDirection) {
		this.sortByDirection = sortByDirection;
	}
	
}
